package com.qiqi.rs.admin.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.datasource.druid", value = "stat-view.enabled", havingValue = "true")
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //IP白名单
        //servletRegistrationBean.addInitParameter("allow","192.168.1.12,127.0.0.1");
        //IP黑名单
        //servletRegistrationBean.addInitParameter("deny","192.168.4.23");
        //控制台用户
        servletRegistrationBean.addInitParameter("loginUsername", "lcyg");
        servletRegistrationBean.addInitParameter("loginPassword", "7113618");
        //是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "true");
        return servletRegistrationBean;
    }
}
