package com.dsm.board.filter;

import com.dsm.board.config.FilterConfig;
import com.mysql.cj.log.Log;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class FilterOne implements Filter {
/*
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
    }

 */

    private String header;
    private String secret;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        /*if(JwtUtil.verifyToken(req.getHeader(header), secret)){
            filterChain.doFilter(servletRequest, servletResponse);
        } else{
            throw new RuntimeException();
        }*/
        //filterChain.doFilter(servletRequest, servletResponse); 필터 실행
    }

    @Override
    public void destroy() {

    }

}
