package com.devmobil.algamoney.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Nataniel Paiva <nataniel.paiva@gmail.com>
 */



/*
 * Vamos para um dos arquivos mais importantes!
 * Esse é o arquivo que gerencia as rotas, mesmo que vc não veja o mesmo nome da classe,
 * mas sempre que vermos a Herança dele em WebSecurityConfigurerAdapter vai ser a classe
 * responsável pelas rotas.
 * 
 * A classe WebSecurityConfigurerAdapter implementa 3 método configure:
 *  - Um fará a autorização do usuário;
 *  - Outro tratará das permissões das rotas, como os verbos HTTP;
 *  - E o último liberar quais aquivos estáticos vão ser liberados para acesso externo.
 *  
 *  Como estamos usando o OAuth2 aqui para trabalhar com as autorizações o segundo método configure está implementado 
 *  dentro da classe Auth2ServerConfiguration.
 * */


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private MyUserDetailsService userDetailsService;
////
//    // método de autorização do usuário
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // liberação de arquivos estáticos (imagens, css, html, xml, json e outros)
    @Override 
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/h2-console/**")
        .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
