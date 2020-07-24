package bo.com.cognos.proyecto.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) 
	throws Exception {
		http.authorizeRequests().anyRequest()
	      	.hasAnyRole("USER", "ADMIN")
	      	.and()
	      		.formLogin()
	      			.loginPage("/login")
	      			.usernameParameter("user").passwordParameter("pass")
	      			.permitAll()
	      	.and()
	      		.logout()
	      			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	      			.permitAll();

	}

	/*
	@Bean
	public PasswordEncoder passwordEncoder() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("noop", org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
		return new DelegatingPasswordEncoder("noop", encoders);
	}*/

	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getAuthenticationProvider());
	}
	
	@Bean
	public AuthenticationProvider getAuthenticationProvider() {
		return new CustomAuthenticationProvider();
	}

}
