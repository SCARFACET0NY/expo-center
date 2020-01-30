package com.anton.expo.filters;

import com.anton.expo.enums.AccountStatus;
import com.anton.expo.repository.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Rejects unauthorized access or redirects to next filter in chain
 */
@WebFilter(urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        User user = (User) req.getSession().getAttribute("user");

        if (req.getRequestURI().startsWith("/cart")) {
            if (user == null) {
                res.sendRedirect("/");
            } else {
                filterChain.doFilter(req, res);
            }
        } else if (req.getRequestURI().startsWith("/admin")) {
            if (user != null && user.getAccountStatus().equals(AccountStatus.ADMIN)) {
                filterChain.doFilter(req, res);
            } else {
                res.sendRedirect("/");
            }
        } else  {
            filterChain.doFilter(req, res);
        }

    }

    @Override
    public void destroy() {

    }
}
