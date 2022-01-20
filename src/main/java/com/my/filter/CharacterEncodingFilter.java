package com.my.filter;

import javax.servlet.*;
import java.io.IOException;
/**
 * @Desc 字符编码过滤器
 */
public class CharacterEncodingFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CharacterEncodingFilter过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //设置utf-8
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("CharacterEncodingFilter过滤器销毁");
    }
}