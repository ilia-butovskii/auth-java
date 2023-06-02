package server.http.interceptors;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import server.http.HttpContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitContextMiddleware extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("context", new HttpContext());

        return true;
    }
}
