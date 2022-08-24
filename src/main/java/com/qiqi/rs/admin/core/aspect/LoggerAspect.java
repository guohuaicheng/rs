package com.qiqi.rs.admin.core.aspect;

import com.qiqi.core.runtime.threadlocal.ClientInfo;
import com.qiqi.core.runtime.threadlocal.ClientInfoThreadLocalHolder;
import com.qiqi.rs.admin.core.runtime.threadlocal.UserContextThreadLocalHolder;
import com.qiqi.rs.admin.platform.user.model.CachedUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * web请求日志切面类---专门针对控制层，如谁被请求了，花了多少时间，请求发送的参数，返回得值等
 */
@ConditionalOnProperty(prefix = "spring.aspect", value = "log.enabled", havingValue = "true")
@Component
@Aspect // 表示一个切面bean
public class LoggerAspect {

    //定义日志记录器--获取sl4j包下提供的logger
    Logger logger = LoggerFactory.getLogger(this.getClass());
    ThreadLocal<Long> startTime = new ThreadLocal<Long>();  //线程副本类去记录各个线程的开始时间
    ThreadLocal<String> token = new ThreadLocal<String>();  //线程副本类去记录各个线程的开始时间

    private final static String USERNAME = "username";
    private final static String UK = "uk";
    private final static String DEVICE = "device";

    @Autowired
    private HttpServletRequest request;

    public LoggerAspect(){

    }

    //定义切入点
	/*1、execution 表达式主体
	  2、第1个* 表示返回值类型  *表示所有类型
	  3、包名  com.*.*.controller下
	  4、第4个* 类名，com.*.*.controller包下所有类
	  5、第5个* 方法名，com.*.*.controller包下所有类所有方法
	  6、(..) 表示方法参数，..表示任何参数
	  */
    @Pointcut("execution(public * com.qiqi.rs.admin.*.*.controller.*.*(..))")
    public void log() {

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {        //方法里面注入连接点
                          //info ,debug ,warn ,erro四种级别，这里我们注入info级别
        try {
            startTime.set(System.currentTimeMillis());

            //获取servlet请求对象---因为这不是控制器，这里不能注入HttpServletRequest，但springMVC本身提供ServletRequestAttributes可以拿到
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();

            CachedUser cachedUser = UserContextThreadLocalHolder.get();

            if (cachedUser != null) {
                MDC.put(USERNAME, cachedUser.getUsername());
                MDC.put(DEVICE, cachedUser.getDevice());
            }
            ClientInfo clientInfo = ClientInfoThreadLocalHolder.get();
            if (clientInfo != null) {
                MDC.put(UK, clientInfo.getIp());
            }
//            MDC.put(UK, IDGenerator.generateID());
            logger.debug("BEFORE: ");
            logger.debug("URL: " + request.getRequestURL().toString());         // 想那个url发的请求
            logger.debug("METHOD: " + request.getMethod());
            logger.debug("CLASS_METHOD: " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());                     // 请求的是哪个类，哪种方法
            logger.debug("ARGS: " + Arrays.toString(joinPoint.getArgs()));     // 方法本传了哪些参数
        } catch (Exception e) {
          logger.error(e.getMessage(), e);
        }
    }


    //方法的返回值注入给ret
    @AfterReturning(returning = "ret", pointcut = "log()")
    public void doAfter(Object ret) {
        try {
            logger.debug("AFTER: ");
            logger.debug("URL: " + request.getRequestURL().toString());
            logger.debug("RESPONSE: " + ret);       // 响应的内容---方法的返回值responseEntity
            logger.debug("SPEND: " + (System.currentTimeMillis() - startTime.get()));
            MDC.remove(USERNAME);
            MDC.remove(UK);
            MDC.remove(DEVICE);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}

