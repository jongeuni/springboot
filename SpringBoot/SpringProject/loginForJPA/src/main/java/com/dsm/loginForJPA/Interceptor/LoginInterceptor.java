package com.dsm.loginForJPA.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override // 컨트롤러에 요청이 넘겨지기 이전에 호출  - return값이 true면 controller에 요청 넘기고 false면 안 넘김
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){


        HttpSession session = request.getSession(false);
        HttpSession httpSession = request.getSession(); // 세션 얻기
        //LoginVO loginVO = (LoginVO) httpSession.getAttribute("USER");
        System.out.println(httpSession+" httpSession");
        if(session==null){
            System.out.println("세선 빔");
            return false;
        }
        //int userNum = (int) session.getAttribute("user_num");


        //int sessionItem = (int) httpSession.getAttribute("USER");

        //System.out.println(userNum+"  세션확인");
/*
        if(userNum==0){
            System.out.println("로그인 필요");
            return false;
            /*
            try {
                response.getOutputStream().println("로그인 필요");
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("세션 오류"); // 없을시익셉션뜸
                return false;
            }
        }
        */
        return true;
    }

    @Override // 컨트롤러가 처리를 마친 후 호출
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    @Override // view까지 모든 요청처리가 완료되었을 때 호출
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws  Exception{

    }
}
