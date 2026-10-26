
package oit.is.z3680.kaizi.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class JankenAuthConfiguration {

  /**
   * 認可処理に関する設定
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .formLogin(login -> login
            .permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/"))
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/janken", "/janken/**").authenticated()
            .anyRequest().permitAll());

    return http.build();
  }

  /**
   * 認証処理に関する設定
   */
  @Bean
  public InMemoryUserDetailsManager userDetailsService() {

    UserDetails user1 = User.withUsername("ほんだ")
        .password("{bcrypt}$2y$05$QmaxjsXgmQ4VVYNTpME8me8JFVrIDVVT64MZS/zc9rBFE46TJewiS")
        .roles("USER")
        .build();

    UserDetails user2 = User.withUsername("user2")
        .password("{bcrypt}$2y$05$o50gjimdawQjQLUoZu7FludQ/5gWSxfsquZuNoH6ZJIRCSvbgscna")
        .roles("USER")
        .build();

    return new InMemoryUserDetailsManager(user1, user2);
  }
}
