package com.dsm.boot9.security;

import lombok.extern.java.Log;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log
public class LoginSuccesHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    // HttpSession에 dest 값이 존재하는 경우 redirect 경로를 dest 값으로 지정
    // 파라미터 이름에 dest가 졵ㅐ하면 이를 HttpSession에 저장
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response){
        log.info("-----------------determineTargetUrl------------------");

        Object dest = request.getSession().getAttribute("dest");

        String nextURL = null;

        if(dest != null){
            request.getSession().removeAttribute("dest");
            nextURL = (String) dest;
        } else{
            nextURL = super.determineTargetUrl(request, response);
        }

        log.info("--------------"+nextURL+"---------------");
        return nextURL;
    }
}
