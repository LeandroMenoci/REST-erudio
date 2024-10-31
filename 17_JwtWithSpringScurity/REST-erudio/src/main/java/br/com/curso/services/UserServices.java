package br.com.curso.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.curso.repositories.UserRepository;

@Service
public class UserServices implements UserDetailsService {
    // Logger for logging user search actions
    private Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    private UserRepository repository;

    // Constructor-based dependency injection for UserRepository
    public UserServices(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Load user details by username.
     *
     * @param username the username to search for
     * @return UserDetails if the user is found
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Finding one user by name: " + username + "!");
        
        // Retrieve user from the repository
        var user = repository.findByUserName(username);
        
        // Check if user exists and return it
        if (user != null) {
            return user;
        } else {
            // Log and throw exception if user not found
            logger.warning("Username " + username + " not found!");
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
    }
}
