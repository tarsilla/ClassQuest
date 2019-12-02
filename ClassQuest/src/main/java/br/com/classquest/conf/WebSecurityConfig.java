package br.com.classquest.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/questionario/**").permitAll()
				.antMatchers("/entrar*","/login*", "/signin/**", "/signup/**", "/h2/**", "/usuario/**").permitAll()
				.antMatchers("/index/**").permitAll()
				.antMatchers("/usuario/**").permitAll()
				.antMatchers("/perfil/**").permitAll()
				.antMatchers("/imgs/**")
				.permitAll().antMatchers("/static/**")
				.permitAll().antMatchers("/resources/**").permitAll()
				.antMatchers("/assets/**").permitAll()
				.antMatchers("/fonts/**").permitAll()
				.antMatchers("/css/**")
				.permitAll().antMatchers("/sass/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/webfonts/**").permitAll()
				
				.anyRequest().authenticated().and().formLogin()
				.loginPage("/entrar").permitAll().successForwardUrl("/index").and().logout().permitAll()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/entrar");

		http.csrf().disable();
		http.headers().frameOptions().disable();

	}

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

	@Bean
	public BCryptPasswordEncoder passwordEnconder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
