package com.example.PortfolioMaker.filter;

import com.example.PortfolioMaker.repository.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class LoginFilter implements Filter{
    @Autowired
    HttpSession session;

    private static Log log = LogFactory.getLog(LoginFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException{
        ModelAndView mav = new ModelAndView();
        log.debug("LoginFilter started.");

        session = ((HttpServletRequest)request).getSession();
        User user = (User) session.getAttribute("loginUser");

        if(user != null){
            chain.doFilter(request, response);
        } else {
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add("ログインしてください");
            session.setAttribute("errorMessage", errorMessages);
            ((HttpServletResponse)response).sendRedirect("/login");
        }
    }
}
