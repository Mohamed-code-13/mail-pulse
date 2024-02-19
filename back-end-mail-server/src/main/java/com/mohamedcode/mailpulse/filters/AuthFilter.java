package com.mohamedcode.mailpulse.filters;

import com.mohamedcode.mailpulse.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class AuthFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String authHeader = httpResponse.getHeader("Authorization");
        if (authHeader != null) {
            String[] authHeaders = authHeader.split("Beared ");
            if (authHeaders.length > 1 && authHeaders[1] != null) {
                String token = authHeaders[1];
                try {
                    Claims claims = Jwts.parser()
                            .setSigningKey(Constants.SECRET_API_KEY)
                            .parseClaimsJws(token)
                            .getBody();

                    httpRequest.setAttribute("user_id", Integer.parseInt(claims.get("user_id").toString()));
                } catch (Exception e) {
                    httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "invalid/expired token");
                    return;
                }
            } else {
                httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be Bearer [token]");
                return;
            }
        } else {
            httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be provided");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
