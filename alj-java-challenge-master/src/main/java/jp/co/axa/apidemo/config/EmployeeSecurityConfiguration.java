package jp.co.axa.apidemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class EmployeeSecurityConfiguration extends WebSecurityConfigurerAdapter  {
	
	private static final String[] SWAGGERLIST = { //
            "/v2/api-docs", //
            "/configuration/ui", //
            "/swagger-resources", //
            "/configuration/security", //
            "/swagger-ui.html", //
            "/webjars/**" //
     };
	//Authenitcation : User --> Roles
	

	 @Override
	 protected void configure(AuthenticationManagerBuilder 	auth) throws Exception{
		 
		 auth.inMemoryAuthentication().withUser("wsadmin").password("{noop}wsadmin").roles("USER");
		 
	 }
	
	 //Authorization
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
		
			 http    
		         .httpBasic()
	             .and()
	             .authorizeRequests().antMatchers("/").permitAll().and()
	             .authorizeRequests().antMatchers("/console/*").permitAll().and()
	             .authorizeRequests().antMatchers("/api/v1/employees").hasRole("USER").and()
	             .authorizeRequests().antMatchers(SWAGGERLIST).authenticated().and().httpBasic().and()
	             .csrf().disable()
	             .headers().frameOptions().disable();		
	       
	    }

	 
	
}
