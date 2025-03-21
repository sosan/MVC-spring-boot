package com.creacionesbrunila.biblioteca;

import com.creacionesbrunila.biblioteca.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // En lugar de @EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadWeb extends WebSecurityConfigurerAdapter {

    @Autowired
    public UsuarioServicio usuarioServicio;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioServicio)//autenticarlo
                .passwordEncoder(new BCryptPasswordEncoder());//le pasamos el codificador de contraseñas.
    }

    // Sobrescribe configure permisos
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/admin/*").hasRole("ADMIN")
                .antMatchers("/css/*", "/js/*", "/img/*", "/**")
                .permitAll()
                .and().formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/logincheck")//no hace falta un controlador
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/inicio")//te da mas opciones
                .permitAll()
                .and().logout()
                .logoutUrl("/logout")//para cerrar sesion y no hace falta un controlador 
                .logoutSuccessUrl("/login")//si cierra te lleva a loign
                .permitAll()
                .and().csrf()
                .disable();

    }
}
