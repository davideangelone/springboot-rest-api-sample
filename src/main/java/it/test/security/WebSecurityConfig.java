package it.test.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.security.web.firewall.RequestRejectedHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		    .headers().frameOptions().sameOrigin().and()
		
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
			.antMatchers("/payment/**").permitAll()
			.antMatchers("/user/**").permitAll()
			
			/** H2 console */
			.antMatchers("/h2-ui/**").permitAll()
			
			/** OpenApi 3.0 */
			.antMatchers("/v3/**").permitAll()
			.antMatchers("/swagger-ui/**").permitAll()
			.antMatchers("/swagger-ui.html").permitAll()
			.antMatchers("/favicon.ico").permitAll()
			
			.anyRequest().authenticated();
	}
	

	@Bean
	RequestRejectedHandler requestRejectedHandler() {
	   return new RequestRejectedHandler() {
		   @Override
		public void handle(HttpServletRequest request, HttpServletResponse response,
				RequestRejectedException requestRejectedException) throws IOException, ServletException {
			response.sendError(HttpStatus.BAD_REQUEST.value());
		}
	   };
	}

}