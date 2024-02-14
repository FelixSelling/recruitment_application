package kth.iv1201.group9.recruitment_application.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kth.iv1201.group9.recruitment_application.domain.entity.Person;
import kth.iv1201.group9.recruitment_application.repository.PersonRepository;

@Service
public class LoginService implements UserDetailsService {
    @Autowired
    private PersonRepository personRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepo.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(person.getUsername())
                .password(person.getPassword())
                .authorities(person.getAuthorities())
                .build();
    }
}
