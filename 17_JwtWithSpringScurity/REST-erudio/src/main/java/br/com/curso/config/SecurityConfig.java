package br.com.curso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

import br.com.curso.security.Jwt.JwtTokenFilter;
import br.com.curso.security.Jwt.JwtTokenProvider;

@EnableWebSecurity // Habilita a segurança na aplicação
@Configuration // Indica que esta classe é uma configuração do Spring
public class SecurityConfig {

    @Autowired
    private JwtTokenProvider tokenProvider; // Injeção do JwtTokenProvider

    // Bean que configura o PasswordEncoder para codificar senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Mapa que armazena diferentes implementações de PasswordEncoder
        Map<String, PasswordEncoder> encoders = new HashMap<>();

        // Configura o encoder PBKDF2 com parâmetros específicos
        Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 0, 185000, SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
        encoders.put("pbkdf2", pbkdf2Encoder);
        
        // Configura um DelegatingPasswordEncoder que usa o PBKDF2
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder); // Define o encoder padrão para as correspondências
        return passwordEncoder; // Retorna o PasswordEncoder configurado
    }

    // Bean que fornece o AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); // Retorna o AuthenticationManager configurado
    }

    // Configuração do SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Cria um filtro JWT personalizado
        JwtTokenFilter customFilter = new JwtTokenFilter(tokenProvider);
        
        // Configura a segurança HTTP
        return http
                .httpBasic(basic -> basic.disable()) // Desativa a autenticação básica
                .csrf(csrf -> csrf.disable()) // Desativa a proteção CSRF
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class) // Adiciona o filtro JWT antes do filtro de autenticação padrão
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configura para não usar sessões
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(
                            "/auth/signin", // Permite o acesso a esta URL sem autenticação
                            "/auth/refresh/**", // Permite o acesso a refresh tokens
                            "/swagger-ui/**",// Permite acesso à interface do Swagger
                            "/v3/api-docs/**" // Permite acesso à documentação da API
                        ).permitAll()
                        .requestMatchers("/api/**").authenticated() // Exige autenticação para qualquer endpoint começando com /api/
                        .requestMatchers("/users").denyAll() // Negar acesso ao endpoint /users
                )
                .cors(cors -> {}) // Permite configuração de CORS (a configuração real deve ser adicionada aqui)
                .build(); // Constrói o SecurityFilterChain
    }
}
