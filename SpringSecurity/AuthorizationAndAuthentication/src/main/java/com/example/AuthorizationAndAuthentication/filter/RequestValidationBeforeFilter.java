package com.example.AuthorizationAndAuthentication.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Slf4j
public class RequestValidationBeforeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Validate the request
        // If the request is not valid, throw an exception
        // If the request is valid, pass the request
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (req.getHeader(HttpHeaders.AUTHORIZATION) != null) {
            String headerValue = req.getHeader(HttpHeaders.AUTHORIZATION);

            if (StringUtils.startsWithIgnoreCase(headerValue,"Basic ")) {
                byte[] byte64HeaderValue = headerValue.substring(6).getBytes(StandardCharsets.UTF_8);
                byte[] decodedHeaderValue = Base64.getDecoder().decode(byte64HeaderValue);
                String token = new String(decodedHeaderValue, StandardCharsets.UTF_8);
                String[] tokenArray = token.split(":");

                for (String tokn : tokenArray) {
                    log.info("Token: " + tokn);
                }
            } else {
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is not Basic");
                return;
            }
        } else {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is missing");
            return;
        }
        chain.doFilter(request, response);
    }
}
