package com.qiqi.rs.admin.core.annotation;

import com.qiqi.rs.admin.core.model.PermissionEnum;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

    @AliasFor("value")
    PermissionEnum[] permission() default {};

    @AliasFor("permission")
    PermissionEnum[] value() default {};

}