package auth.accounts;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public class AccountService implements UserDetailsService {

    AccountRepository accountRepository;

    AccountService(AccountRepository repository) {
        this.accountRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByUsername(s);
        Account account = accountOptional.orElseThrow(EntityNotFoundException::new);
        boolean active = account.isActive();
        return new User(account.getUsername(), account.getPassword(), active,
                active, active, active, AuthorityUtils.createAuthorityList("ROLE_ADMIN",
                "ROLE_USER"));
    }
}
