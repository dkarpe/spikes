package auth.accounts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class AccountConfiguration {

    @Bean
    UserDetailsService userDetailsService(AccountRepository accountRepository) {
        return new AccountService(accountRepository);
    }
}
