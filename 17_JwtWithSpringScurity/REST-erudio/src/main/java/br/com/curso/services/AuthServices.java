package br.com.curso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.curso.data.vo.v1.security.AccountCredentialsVO;
import br.com.curso.data.vo.v1.security.TokenVO;
import br.com.curso.repositories.UserRepository;
import br.com.curso.security.Jwt.JwtTokenProvider;

@Service
public class AuthServices {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;

    /**
     * Method to authenticate user and return a token.
     *
     * @param data Credentials provided by the user
     * @return ResponseEntity containing the token or an error message
     */
    public ResponseEntity<TokenVO> signin(AccountCredentialsVO data) {
        // Try to authenticate the user
        try {
            var username = data.getUsername();
            var password = data.getPassword();
            
            // Authenticate the user using the provided credentials
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            
            // Retrieve user roles from the repository
            var user = repository.findByUserName(username);
            TokenVO tokenResponse;

            // Check if the user exists and generate token
            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            } else {
                // Throw exception if user not found
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (UsernameNotFoundException e) {
            // Handle specific exception for user not found
            throw new BadCredentialsException("Invalid username/password supplied!", e);
        } catch (BadCredentialsException e) {
            // Handle invalid credentials
            throw new BadCredentialsException("Invalid username/password supplied!", e);
        } catch (Exception e) {
            // Handle other exceptions
            throw new BadCredentialsException("An error occurred during authentication!", e);
        }
    }
}
