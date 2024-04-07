package com.trademarket.web.security;

import com.trademarket.model.dto.PersonDto;
import com.trademarket.web.producer.PersonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class WebUserDetailsService implements UserDetailsService {
    @Autowired
    private PersonProducer personProducer;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PersonDto person = personProducer.getByEmail(username);
        return new WebUserDetails(person.getEmail(), person.getPassword(), person.getRole(), person.getId());
    }
}
