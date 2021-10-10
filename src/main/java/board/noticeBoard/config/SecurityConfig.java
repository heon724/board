package board.noticeBoard.config;

import board.noticeBoard.component.JWTTokenComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public SecurityConfig(JWTTokenComponent jWTTokenComponent) {
        this.jWTTokenComponent = jWTTokenComponent;
    }

    //TODO 임시용 나중에 다시 설정
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final JWTTokenComponent jWTTokenComponent;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and() // 세션말고 jwt로 수정
                .authorizeRequests()
               // .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers("/**").authenticated()
                .antMatchers("/swagger*/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/member/dupcheck-id/**").permitAll()
                .antMatchers("/member/dupcheck-phone/**").permitAll()
               .antMatchers("/member/signUp").permitAll()
                .antMatchers("/member/login").permitAll()
               .antMatchers("/member/findId/**").permitAll()
               .antMatchers("/member/findPw").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jWTTokenComponent), UsernamePasswordAuthenticationFilter.class)
                .formLogin().disable()
        ;
    }
}


