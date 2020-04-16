package com.devmobil.algamoney.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.devmobil.algamoney.api.service.MyUserDetailsService;

@Configuration
public class OAuth2ServerConfiguration {

    private static final String RESOURCE_ID = "restservice";

	@EnableWebSecurity
	@EnableResourceServer
	protected static  class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		@Autowired
		private MyUserDetailsService userDetailsService;
		
		@Autowired
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
//			System.out.println(passwordEncoder().encode("123456"));
			auth.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());
		}
		
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.antMatchers("/categories").permitAll()
					.antMatchers("/","/users/otp", "swagger-ui.html",  "/**.html","/resources/**","/images/**").permitAll()
					.anyRequest().authenticated()
					.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.csrf().disable();
		}
		
		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			resources.stateless(true).resourceId(RESOURCE_ID);
		}
		
		@Bean
		public MethodSecurityExpressionHandler createdExpressionHandler() {
			return new OAuth2MethodSecurityExpressionHandler();
		}

		
	}

	@Configuration
	@EnableAuthorizationServer
	public static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
		
		@Autowired
	    @Qualifier("authenticationManagerBean")
	    private AuthenticationManager authenticationManager;

		@Autowired
		private PasswordEncoder passwordEncoder;
	
		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints
				.tokenStore(this.tokenStore())
				.accessTokenConverter(accessTokenConverter())
				.reuseRefreshTokens(false) // caso não esteja falso ele não irá renovar e só terá o valor de tempo inicial
				.authenticationManager(this.authenticationManager);
		}
		
		@Bean
		public JwtAccessTokenConverter accessTokenConverter() {
			JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
			accessTokenConverter.setSigningKey("algaworks");;
			return accessTokenConverter;
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory()
				.withClient("angular")
				.secret(passwordEncoder.encode("123456"))
				.scopes("read", "write")
				.authorizedGrantTypes("password", "refresh_token")
				.accessTokenValiditySeconds(8000)
				.refreshTokenValiditySeconds(3600 * 24);
		}
	
		
		public TokenStore tokenStore() {
			return new JwtTokenStore(accessTokenConverter());
		}
	}
	
}
