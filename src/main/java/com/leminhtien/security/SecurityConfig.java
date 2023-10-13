package com.leminhtien.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.leminhtien.service.impl.CustomUserdetailsService;

//	@Configuration
//	@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//		private final CustomUserdetailsService customUserdetailsService;
//		private final BCryptPasswordEncoder bCryptPasswordEncoder;
//		private final CustomSuccessHandler customSuccessHandler;
//		private final JwtAuthenticationFilter jwtAuthenticationFilter;
//		private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
//
//		public SecurityConfig(CustomUserdetailsService customUserdetailsService,
//				BCryptPasswordEncoder bCryptPasswordEncoder, CustomSuccessHandler customSuccessHandler,
//				JwtAuthenticationFilter jwtAuthenticationFilter,
//				RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
//			this.customUserdetailsService = customUserdetailsService;
//			this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//			this.customSuccessHandler = customSuccessHandler;
//			this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//			this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
//		}
//
//		@Override
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			auth.userDetailsService(customUserdetailsService).passwordEncoder(bCryptPasswordEncoder);
//		}

//	@Bean
//	public JwtAuthenticationFilter jwtAuthenticationFilter() {
//		return new JwtAuthenticationFilter();
//	}
//
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		// Get AuthenticationManager bean
//		return super.authenticationManagerBean();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		// Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
//		return new BCryptPasswordEncoder();
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
//	}
//
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(customUserDetailsService());
//		authProvider.setPasswordEncoder(bcryptEncoder());
//		return authProvider;
//	}
//
//	@Bean
//	public CustomUserdetailsService customUserDetailsService() {
//		// Trả về đối tượng CustomUserDetailsService đã được khởi tạo
//		return new CustomUserdetailsService();
//	}
//
//	@Bean
//	public PasswordEncoder bcryptEncoder() {
//		// Trả về đối tượng BCryptPasswordEncoder đã được khởi tạo
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean
//	public CustomSuccessHandler customSuccessHandler() {
//		return new CustomSuccessHandler();
//	}
//
//	@Bean
//	public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
//		return new RestAuthenticationEntryPoint();
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/home").permitAll().antMatchers("/login").permitAll()
//				.antMatchers("/admin").hasAnyRole("ADMIN").anyRequest().authenticated().and().formLogin()
//				.loginPage("/login").usernameParameter("j_username").passwordParameter("j_password")
//				.loginProcessingUrl("/j_spring_security_check").successHandler(customSuccessHandler())
//				.failureUrl("/login?incorrectAccount").and().sessionManagement()
//				.invalidSessionUrl("/login?sessiontimeout").and().logout().deleteCookies("JSESSIONID").and()
//				.exceptionHandling().accessDeniedPage("/access-denied").and().csrf().disable();
//		http.addFilterAfter(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//	}
}
