package bo.com.cognos.proyecto.security.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Order(2)
public class RestConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	RestAuthenticationEntryPoint entryPoint;
	
	@Autowired
	JwtTokenFilter jwtTokenFilter;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http.antMatcher("/rest/**").csrf().disable()
	    .sessionManagement()		  
	    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	    .and().authorizeRequests().antMatchers("/rest/login")
	    .permitAll().anyRequest().hasAnyRole("USER", "ADMIN")
	    .and().addFilterBefore(jwtTokenFilter, 
	              UsernamePasswordAuthenticationFilter.class)
		.exceptionHandling().authenticationEntryPoint(entryPoint);
	}

}
