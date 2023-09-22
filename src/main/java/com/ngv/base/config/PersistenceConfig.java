package com.ngv.base.config;

import com.ngv.base.data.UserPrincipal;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;


@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class PersistenceConfig {

  @Bean
  AuditorAware<Long> auditorProvider() {
    return new AuditorAwareImpl();
  }

  /**
   * Class implement for components that are aware of the application's current auditor. This will
   * be some kind of user mostly.
   *
   * @author dungnv
   * @version 1.0
   */
  public static class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
      try {
//        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userPrincipal != null) {
          return Optional.of(userPrincipal.getUserId());
        }
        return Optional.of(0L);
      } catch (Exception e) {
        return Optional.of(0L);
      }
    }
  }
}
