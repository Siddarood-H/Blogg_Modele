package com.bloggapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bloggapp.security.CustomUserDetailsService;

import jakarta.websocket.Session;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableWebMvc

public class SecurityConfig {
	
	
	
	
	@Autowired
	
	private CustomUserDetailsService userDetailsService;

	
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new  BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
	{            
		httpSecurity.csrf(csrf->csrf.disable())	 
    	    	.cors(corse->corse.disable())
    	    	.authorizeHttpRequests(
    	    			auth->
    	    	          auth.requestMatchers(HttpMethod.GET, "/api**").permitAll()
    	    	         . requestMatchers("v3/api.docs").permitAll()
    	    	        .anyRequest() .authenticated())
                .httpBasic(withDefaults());
      
		httpSecurity.authenticationProvider(daoauthenticationProvider());
		
		DefaultSecurityFilterChain defaultSecurityFilterChain= httpSecurity.build();
		 return defaultSecurityFilterChain;
	}
	@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	return configuration.getAuthenticationManager();
}
	@Bean
	public DaoAuthenticationProvider daoauthenticationProvider()
	{
		DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
protected void configure (AuthenticationManagerBuilder auth) throws Exception
{
	auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
}
	

}
