package com.mifinity.demo.config;

import com.mifinity.demo.domain.service.CustomUserDetailsService;
import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Created by andrea.schembri on 01/09/2017.
 */
@EnableWebSecurity
@EnableConfigurationProperties(CorsPropertiesConfiguration.class)
@ComponentScan(basePackages = "com.andrea.auth")
public class SecurityConfigurationDev extends WebSecurityConfigurerAdapter {

    @Autowired
    private CorsPropertiesConfiguration corsPropertiesConfiguration;

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    public void configureAuthentication(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()//.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            //.and()
                .cors()
            .and()
                .authorizeRequests()
                // allow anonymous resource requests
                .antMatchers(
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/v2/api-docs",
                        "/diagnostics/**",
                        "/h2-console/**",
                        "/",
                        "/login",
                        "/users/signup").permitAll() // permitting urls for testing purposes
                .anyRequest().authenticated()
            .and()
                .formLogin().failureHandler(new AuthFailureHandler())
            .and()
                .exceptionHandling()
            .and()
                .logout().permitAll();

        // disable page caching
        http.headers().cacheControl();

        // add this line to use H2 web console
        http.headers().frameOptions().disable();
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(corsPropertiesConfiguration.getAllowedOrigins());
        configuration.setAllowedHeaders(corsPropertiesConfiguration.getAllowedHeaders());
        configuration.setAllowedMethods(corsPropertiesConfiguration.getAllowedMethods());
        configuration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
        return new Http401AuthenticationEntryPoint("Not authenticated");
    }
}
