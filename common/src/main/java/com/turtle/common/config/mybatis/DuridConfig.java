package com.turtle.common.config.mybatis;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.turtle.common.enums.DataSourceKey;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lijiayu
 * @date 2019/12/27
 * @description
 */
@Configuration
public class DuridConfig {


    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    @Primary
    @Bean(name = "master")
    public DataSource master(){
        return DruidDataSourceBuilder.create().build();
    }


    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    @Bean(name = "slave")
    public DataSource slave(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource(){
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<Object, Object>(2);
        dataSourceMap.put(DataSourceKey.MASTER.getName(),master());
        dataSourceMap.put(DataSourceKey.SLAVE.getName(),slave());

        dynamicRoutingDataSource.setDefaultTargetDataSource(master());
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);

        DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSourceMap.keySet());

        return dynamicRoutingDataSource;
    }

    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        //使用MybatisSqlSessionFactoryBean  不然无法使用mybatis-plus baseMapper的默认方法
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();

        MybatisConfiguration configuration = new MybatisConfiguration();
        //下划线转骆驼
        configuration.setMapUnderscoreToCamelCase(true);

        mybatisSqlSessionFactoryBean.setDataSource(dynamicDataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        mybatisSqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));
        mybatisSqlSessionFactoryBean.setConfiguration(configuration);

        return mybatisSqlSessionFactoryBean;
    }

    /**
     * 事务
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dynamicDataSource());
    }

    /**
     * 配置Druid监控
     *
     * @return StatViewServlet
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String, String> map = new HashMap<>();
        //访问的用户名密码
        map.put(StatViewServlet.PARAM_NAME_USERNAME, "admin");
        map.put(StatViewServlet.PARAM_NAME_PASSWORD, "123456");
        //允许访问的ip，默认是所有ip
        map.put(StatViewServlet.PARAM_NAME_ALLOW, "");
        //禁止访问的ip
        map.put(StatViewServlet.PARAM_NAME_DENY, "192.168.1.1");
        bean.setInitParameters(map);
        return bean;
    }

    /**
     * 配置一个监控的filter
     *
     * @return WebStatFilter
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        Map<String, String> map = new HashMap<>();
        //移除这些监听
        map.put(WebStatFilter.PARAM_NAME_EXCLUSIONS, "*.js,*.css,/druid/*,*.gif,*.jpg,*.png");
        bean.setInitParameters(map);
        //拦截所有请求，全部都要走druid监听
        bean.setUrlPatterns(Collections.singletonList("/*"));
        return bean;
    }
}
