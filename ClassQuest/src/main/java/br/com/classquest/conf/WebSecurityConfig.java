package br.com.classquest.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
		http.csrf().disable().authorizeRequests()
				.antMatchers("/perfil/**").hasAnyAuthority("PRO", "ALU")
				.antMatchers("/questionario/**").hasAnyAuthority("PRO")
				.antMatchers("/turma/novaTurma").hasAnyAuthority("PRO")
				.antMatchers("/turma/minhaTurma").permitAll()
				.antMatchers("/turma/verTurma").permitAll()
				.antMatchers("/").hasAnyAuthority("PRO", "ALU")
				.antMatchers("/usuario/**").hasAnyAuthority("PRO", "ALU")
				.antMatchers("http://**").permitAll()
				.antMatchers("https://**").permitAll()
				.antMatchers("layout").permitAll()
				.antMatchers("/imgs/**").permitAll()
				.antMatchers("/static/**").permitAll()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/assets/**").permitAll()
				.antMatchers("/fonts/**").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/sass/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/webfonts/**").permitAll()
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/entrar").permitAll()
				.successForwardUrl("/home").and().logout().permitAll()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/entrar");
		
		http.csrf().disable();
        http.headers().frameOptions().disable();
	
	}

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/assets/**", "/css/**", "/fonts/**", "/imgs/**",
				"/js/**", "/sass/**", "/webfonts/**", "/h2/**");
		web.ignoring().antMatchers("/layout", "http::/**", "https::/**");
	}
}
