package br.com.curso.security.Jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider tokenProvider;

    // Construtor com injeção de dependência
    @Autowired
    public JwtTokenFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        // Realiza o cast do request para HttpServletRequest
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = tokenProvider.resolveToken(httpRequest);
        
        // Verifica se o token é válido e se o token não é nulo
        if (token != null && tokenProvider.validateToken(token)) {
            // Obtém a autenticação do token
            Authentication auth = tokenProvider.getAuthentication(token);
            if (auth != null) {
                // Armazena a autenticação no contexto de segurança
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // Continua a execução do filtro na cadeia
        chain.doFilter(request, response);
    }
}
