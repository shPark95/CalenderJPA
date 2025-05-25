package org.server.calendarjpa.common.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class SessionAuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String uri = httpServletRequest.getRequestURI();

        if (uri.startsWith("/api/login") || uri.startsWith("/api/signup")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        HttpSession session = httpServletRequest.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
