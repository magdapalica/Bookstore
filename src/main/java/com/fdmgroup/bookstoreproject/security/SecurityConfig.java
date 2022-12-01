package com.fdmgroup.bookstoreproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(encoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()



				.antMatchers("/", "/mock", "/productList", "/css/**", "/js/**", "/h2/**", "WEB-INF/jsps/**", "/", "/**/*.png", "/register", "/passwordreset", "/images/**", "/message", "/images/**").permitAll()
				.antMatchers("/admin/**", "/add").hasRole("Admin")
				.anyRequest().authenticated()
				.and()
				.rememberMe().userDetailsService(userDetailsService)
				.and()
			.formLogin().loginPage("/login").permitAll()  // Spring won't inject it's own login page
				.defaultSuccessUrl("/", true)
				.failureUrl("/login?error=true")
				.and()
			.logout()
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.and()
			.csrf()
				.disable()	// So we don't have to add csrf to every form
			.httpBasic()
				.and()
			.headers().frameOptions().disable();	// Allows H2 to render correctly
	}
	
}
