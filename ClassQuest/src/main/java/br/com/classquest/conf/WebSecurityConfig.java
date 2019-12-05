package br.com.classquest.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.classquest.repository.UserDetailsRepository;


@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	//@Autowired
	//private UserDetailsService userDetailsService;
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/signin/**", "/signup/**", "/h2/**", "/usuario/**").permitAll()
				.antMatchers("/usuario/**").permitAll()
				.antMatchers("/questionario/**").permitAll()
				.antMatchers("/perfil/**").permitAll()
				.antMatchers("/imgs/**")
				.permitAll().antMatchers("/static/**")
				.permitAll().antMatchers("/resources/**").permitAll()
				.antMatchers("/assets/**").permitAll()
				.antMatchers("/vendors/**").permitAll()
				.antMatchers("/fonts/**").permitAll()
				.antMatchers("/css/**")
				.permitAll().antMatchers("/sass/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/webfonts/**").permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
					.loginPage("/entrar").permitAll().successForwardUrl("/index")
			.and()
				.logout().permitAll()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/entrar")
			.and()
	            .rememberMe()
	            .userDetailsService(userDetailsRepository);
		
		http
			.sessionManagement()
				.sessionFixation().migrateSession()
				.invalidSessionUrl("/entrar")
				.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
		        .maximumSessions(1)
		        .maxSessionsPreventsLogin(true)
		        .expiredUrl("/entrar")
		        .sessionRegistry(sessionRegistry());
		
		http.csrf().disable();
		http.headers().frameOptions().disable();

	}
	
	
	@Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsRepository).passwordEncoder(passwordEncoder());
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
	
	@Bean
	SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

}
