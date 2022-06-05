//package com.example.podcastserver.confige;
//
//import com.example.podcastserver.security.APIKeyAuthFilter;
//import com.example.podcastserver.security.UsersFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
//public class APISecurityConfig extends WebSecurityConfigurerAdapter {
//
//    final UsersFilter usersFilter;
//
//    public APISecurityConfig(UsersFilter usersFilter) {
//        this.usersFilter = usersFilter;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        APIKeyAuthFilter apiKeyAuthFilter = new APIKeyAuthFilter();
//        apiKeyAuthFilter.setAuthenticationManager(authentication -> {
//            Object principal = authentication.getPrincipal();
//            if (principal instanceof HttpServletRequest) if (!usersFilter.checkAuth((HttpServletRequest) principal))
//                throw new BadCredentialsException("The API key was not found or not the expected value.");
//            authentication.setAuthenticated(true);
//            return authentication;
//        });
//        http
//                .cors().and()
//                .csrf().disable()
//                .exceptionHandling()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilter(apiKeyAuthFilter)
//                .authorizeRequests()
//                .antMatchers(
//                        "/",
//                        "/api/auth/*",
//                        "/favicon.ico",
//                        "/**/*.png",
//                        "/**/*.gif",
//                        "/**/*.svg",
//                        "/**/*.jpg",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js",
//                        "/swagger-ui.html",
//                        "/resources/**",
//                        "/swagger-resources/**",
//                        "/v2/**",
//                        "/csrf",
//                        "/webjars/**")
//                .permitAll()
//                .antMatchers("/api/**")
//                .authenticated();
//    }
//}
