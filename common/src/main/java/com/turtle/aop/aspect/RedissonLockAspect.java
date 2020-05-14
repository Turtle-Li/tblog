package com.turtle.aop.aspect;

import com.turtle.aop.RedissonLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author lijiayu
 * @date 2020/5/14
 * @description
 */
@Slf4j
@Aspect
@Component
@Order(1) //该order必须设置，很关键
public class RedissonLockAspect {
    @Resource
    private Redisson redisson;

    private SpelExpressionParser parser = new SpelExpressionParser();

    @Around("@annotation(redissonLock)")
    public Object around(ProceedingJoinPoint joinPoint, RedissonLock redissonLock) throws Throwable {
        Object obj = null;

        //方法内的所有参数
        Object[] params = joinPoint.getArgs();

        String spELString = redissonLock.spELString();
        //取得方法名
        String key = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint
                .getSignature().getName();
        //如果spELString为空代表锁整个方法，而非具体锁哪条数据
        //带#则spEL表达式获取参数值，不带则意味锁具体值。
        if (StringUtils.isNotBlank(spELString)) {
            key += spELString.startsWith("#")?generateKeyBySpEL(spELString,joinPoint):spELString;
        }

        //多久会自动释放，默认10秒
        int leaseTime = redissonLock.leaseTime();
        int waitTime = redissonLock.waitTime();

        RLock rLock = redisson.getLock(key);
        boolean res = rLock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
        if (res) {
            log.info("取到锁");
            obj = joinPoint.proceed();
            rLock.unlock();
            log.info("释放锁");
        } else {
            log.info("----------nono----------");
            throw new RuntimeException("没有获得锁");
        }

        return obj;
    }

    private String generateKeyBySpEL(String spELString,ProceedingJoinPoint joinPoint){
        // 通过joinPoint获取被注解方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 使用spring的DefaultParameterNameDiscoverer获取方法形参名数组
        String[] paramNames = methodSignature.getParameterNames();
        // 解析过后的Spring表达式对象
        Expression expression = parser.parseExpression(spELString);
        // spring的表达式上下文对象
        EvaluationContext context = new StandardEvaluationContext();
        // 通过joinPoint获取被注解方法的形参
        Object[] args = joinPoint.getArgs();
        // 给上下文赋值
        for(int i = 0 ; i < args.length ; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        // 表达式从上下文中计算出实际参数值
        return expression.getValue(context).toString();
    }
}