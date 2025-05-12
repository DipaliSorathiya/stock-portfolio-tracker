package com.tech.stockmarket.stock_market_backend.services;

import com.tech.stockmarket.stock_market_backend.entity.User;
import com.tech.stockmarket.stock_market_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.get().getUsername())
                .password(user.get().getPassword()) // Encrypted password
                .roles("USER") // You can customize roles here
                .build();
    }
}
