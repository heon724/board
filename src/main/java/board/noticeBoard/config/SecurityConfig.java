package board.noticeBoard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //TODO 임시용 나중에 다시 설정
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers("/**").authenticated()
                //                .antMatchers("/member/dupcheck-id/**").permitAll()
//                .antMatchers("/member/dupcheck-phone/**").permitAll()
//                .antMatchers("/member/signUp").permitAll()
//                .antMatchers("/member/login").permitAll()
//                .antMatchers("/member/findId/**").permitAll()
//                .antMatchers("/member/findPw").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin().disable()
        ;
    }
}


