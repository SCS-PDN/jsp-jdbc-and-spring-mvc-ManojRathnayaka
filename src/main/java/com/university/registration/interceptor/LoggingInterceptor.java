package com.university.registration.interceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class LoggingInterceptor implements HandlerInterceptor {
    private static final Logger logger = LogManager.getLogger(LoggingInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        if (requestUri.equals("/login") && request.getMethod().equals("POST")) {
            String email = request.getParameter("email");
            logger.info("Login attempt for email: {}", email);
        }
        if (requestUri.startsWith("/register/") && request.getMethod().equals("POST")) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Integer studentId = (Integer) session.getAttribute("studentId");
                String studentName = (String) session.getAttribute("studentName");
                String courseId = requestUri.substring("/register/".length());
                logger.info("Course registration attempt - Student ID: {}, Name: {}, Course ID: {}", 
                            studentId, studentName, courseId);
            }
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String requestUri = request.getRequestURI();
        if (requestUri.equals("/login") && request.getMethod().equals("POST")) {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("studentId") != null) {
                Integer studentId = (Integer) session.getAttribute("studentId");
                String studentName = (String) session.getAttribute("studentName");
                
                logger.info("Successful login - Student ID: {}, Name: {}", studentId, studentName);
            }
        }
    }
}