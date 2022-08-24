package com.qiqi.rs.admin.core.servlet;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/druid/*", initParams = {@WebInitParam(name = "loginUsername", value = "lcyg7113618"), @WebInitParam(name = "loginPassword", value = "7113618")})
public class A extends StatViewServlet {
}
