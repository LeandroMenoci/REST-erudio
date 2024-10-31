package br.com.curso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.curso.data.vo.v1.security.AccountCredentialsVO; // Corrigido o nome da classe importada
import br.com.curso.services.AuthServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication Endpoint") // Tag para documentação OpenAPI
@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/auth") // Mapeia as requisições para o caminho /auth
public class AuthController {

    @Autowired
    AuthServices authServices; // Injeta o serviço de autenticação

    @SuppressWarnings("rawtypes") // Evita avisos de tipo não específico
    @Operation(summary = "Authenticates a user and returns a token") // Descrição da operação para documentação
    @PostMapping(value = "/signin") // Mapeia requisições POST para /auth/signin
    public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
        // Verifica se os dados da solicitação são válidos
        if (data == null || data.getUsername() == null || data.getUsername().isBlank()
            || data.getPassword() == null || data.getPassword().isBlank()) {
            // Retorna uma resposta de erro se a validação falhar
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }

        // Chama o serviço de autenticação para processar a solicitação
        var token = authServices.signin(data);
        if (token == null) {
            // Retorna uma resposta de erro se a autenticação falhar
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        }
        
        // Retorna o token de autenticação se a autenticação for bem-sucedida
        return token;
    }
}
