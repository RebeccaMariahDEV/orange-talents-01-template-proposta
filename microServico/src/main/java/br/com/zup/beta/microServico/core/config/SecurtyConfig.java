package br.com.zup.beta.microServico.core.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
public class SecurtyConfig extends  WebSecurityConfigurerAdapter {

    //        @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().permitAll();
//    }
    //configura os endpoints que vão ser autenticados
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("entrou aqui ");
        http.authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers(HttpMethod.GET, "api/propostas/**").hasAuthority("SCOPE_read")
                .antMatchers(HttpMethod.POST, "api/propostas").hasAuthority("SCOPE_write")
                .antMatchers(HttpMethod.GET, "api/cartoes/**").hasAuthority("SCOPE_read")
                .antMatchers(HttpMethod.POST, "biometrias/**").hasAuthority("SCOPE_write")
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                //.cors()
                .disable()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
//        ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
