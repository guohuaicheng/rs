package com.qiqi.rs.admin.core.listener;

import com.qiqi.rs.admin.core.cache.DictCache;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CacheListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
//        DictService dictService = webApplicationContext.getBean(DictService.class);
//        dictService.initDict();

//        SystemParamService systemParamService = webApplicationContext.getBean(SystemParamService.class);
//        systemParamService.initSystemParam();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DictCache.getInstance().clear();
    }
}
