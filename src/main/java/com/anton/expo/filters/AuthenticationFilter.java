package com.anton.expo.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if (req.getRequestURI().startsWith("/cart")) {
            if (req.getSession().getAttribute("user") == null) {
                res.sendRedirect("/");
            } else {
                filterChain.doFilter(req, res);
            }
        } else {
            filterChain.doFilter(req, res);
        }

    }

    @Override
    public void destroy() {

    }
}
