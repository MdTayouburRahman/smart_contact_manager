package com.droidrocks.smart_contact_manager.Security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService getUserDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
        return daoAuthenticationProvider;
    }

    //configur method

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] resources = new String[]{"/Img/*.png","/Img/*.jpg","/JS/*.js","/CSS/*.css"};
        String[] permittedPages = new String[]{"/home","/about","/signup"};
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers(permittedPages)
                .permitAll()
                .antMatchers(resources).permitAll()
                .antMatchers("/user/*.html")
                .hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin(form -> form
                        .loginPage("/signin")
                        .loginProcessingUrl("/signin")
                        .defaultSuccessUrl("/user/index", true)
                        .permitAll()
                )
                .logout(logout-> logout.logoutUrl("/user/logout"));
      /*  String[] resources = new String[]{"/Img/*.png","/Img/*.jpg","/JS/*.js","/CSS/*.css"};
        http.authorizeRequests()
                .antMatchers("/admin/*.html")
                .hasRole("ADMIN")
                .antMatchers("/user/*.html")
                .hasRole("USER")
                .antMatchers("/**").permitAll()
                .antMatchers(resources).permitAll()
                .and()
                .formLogin(form -> form
                        .loginPage("/signin")
                        .defaultSuccessUrl("/user/index", true)
                        .permitAll()
                )
                .logout(logout-> logout.logoutUrl("/logout"))
                .csrf().disable();*/
    }
}
