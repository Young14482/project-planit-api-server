package com.metacoding.planitapiserver._core.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("CORS 필터 작동");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String origin = request.getHeader("Origin"); // 요청한 사람의 주소가 들어옴
        System.out.println("Origin : "+origin);

        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Expose-Headers", "Authorization"); // 브라우저에 응답이 될 때 Authorization값에 접근 가능 유무 >> 생략가능
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, PATCH, GET, DELETE, OPTIONS"); // 해당 메서드 허용 >> OPTIONS 중요
        response.setHeader("Access-Control-Max-Age", "3600"); // 60초 단위 수명
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Key, Content-Type, Authorization"); // 해당 헤더 허용 >> Content-Type, Authorization 중요 | X-Key >> 커스텀 헤더
        response.setHeader("Access-Control-Allow-Credentials", "true"); // true를 줘야함 >> Authorization 전송 가능 >> * 사용 불가

        // Preflight 요청을 허용하고 바로 응답하는 코드
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        }else {
            chain.doFilter(req, res);
        }
    }
}