package com.chiz.todo.todoList.security.filter;

import com.chiz.todo.todoList.appuser.AppUserService;
import com.chiz.todo.todoList.security.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final AppUserService appUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getRequestURI().contains("/api/registration") || request.getRequestURI().contains("/api/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null && !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        String username = jwtTokenService.extractUsername(token);
        log.info("username: {}",username);


        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = appUserService.loadUserByUsername(username);
            if(jwtTokenService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                log.info("Context: {}",SecurityContextHolder.getContext().getAuthentication().toString());
            }
        }
        filterChain.doFilter(request, response);
    }
}
