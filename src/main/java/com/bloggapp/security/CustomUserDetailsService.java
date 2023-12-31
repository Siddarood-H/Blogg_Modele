package com.bloggapp.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bloggapp.entities.Role;
import com.bloggapp.entities.User;
import com.bloggapp.repositories.UserRepo;
@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepo userRepo; 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//load user from database
		User user =this. userRepo.findByEmail(username).orElseThrow(()->
		 new  UsernameNotFoundException("User not found with  username or email:" + username));
		 return  user; 
		 
	

	}
	
	
}
