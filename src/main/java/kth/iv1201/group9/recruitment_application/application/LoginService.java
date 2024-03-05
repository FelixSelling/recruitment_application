package kth.iv1201.group9.recruitment_application.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kth.iv1201.group9.recruitment_application.domain.entity.Person;
import kth.iv1201.group9.recruitment_application.repository.PersonRepository;

/**
 * This class is responsible for authenticating users during the login process.
 * It implements the UserDetailsService interface provided by Spring Security.
 */
@Transactional
@Service
public class LoginService implements UserDetailsService {
    @Autowired
    private PersonRepository personRepo;

    /**
     * Loads the user details by username.
     * 
     * @param username the username of the user to load
     * @return the UserDetails object representing the user
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepo.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("error.login.username.notfound");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(person.getUsername())
                .password(person.getPassword())
                .authorities(person.getAuthorities())
                .build();
    }
}
