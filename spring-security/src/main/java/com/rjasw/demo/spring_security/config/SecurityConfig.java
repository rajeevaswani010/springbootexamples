package com.rjasw.demo.spring_security.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@ComponentScan({"com.rjasw.demo.spring_security.security"})
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SecurityConfig {

    private static String wwwPath = "/www";
    private static String wwwLogin = wwwPath+"/login.html";
    private static String wwwMain = wwwPath+"/index.html";

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(wwwPath+"/resources/**",wwwPath+"/classic/resources/**" );
    }

    @Bean
    public SecurityFilterChain filterChainApi(HttpSecurity http) throws Exception {
    	
    	//do something here.. 
    	
    	return http.build();
    }
	
    @Bean
    public PasswordEncoder encoder() {
    	String idForEncode = "bcrypt";
        Map<String,PasswordEncoder> encoders = new HashMap<>();
        encoders.put(null, org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
        encoders.put(idForEncode, new BCryptPasswordEncoder());

        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);
        return passwordEncoder;
    }
}
