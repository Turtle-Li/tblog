package com.turtle.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lijiayu
 * @date 2020/5/14
 * @description
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedissonLock {
    /**
     * 要锁哪个参数
     */
    String spELString() default "";

    /**
     * 锁多久后自动释放（单位：秒）
     */
    int leaseTime() default 10;

    /**
     * 最多等待时间（单位：秒），没取到走失败
     * @return
     */
    int waitTime() default 5;
}