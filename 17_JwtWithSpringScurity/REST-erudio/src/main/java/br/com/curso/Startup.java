package br.com.curso;

// import java.util.HashMap;
// import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
// import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;

@SpringBootApplication
public class Startup {

    public static void main(String[] args) {
        // Inicia a aplicação Spring Boot
        SpringApplication.run(Startup.class, args);
        
        // // Cria um codificador de senha PBKDF2
        // Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder(
        //     "",                 // Senha base (geralmente uma string vazia, se não estiver usando um salt específico)
        //     8,                  // Tamanho do salt em bytes
        //     185000,             // Número de iterações de hash
        //     SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256 // Algoritmo de hash
        // );

        // // Cria um mapa de codificadores de senha
        // Map<String, PasswordEncoder> encoders = new HashMap<>();
        // encoders.put("pbkdf2", pbkdf2Encoder); // Adiciona o codificador PBKDF2 ao mapa

        // // Cria um codificador de senhas que pode delegar a vários codificadores
        // DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        
        // // Define o codificador padrão para correspondência
        // passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
        
        // // Codifica uma senha de exemplo
        // String result = passwordEncoder.encode("coffee123");
        // System.out.println("My hash: " + result); // Exibe o hash da senha
    }
}

