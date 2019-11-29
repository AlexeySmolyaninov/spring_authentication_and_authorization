package onlyfortheselected;

import java.util.Arrays;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alexey Smolyaninov
 */

@Service
public class CreateDefaultUsers {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostConstruct
    private void createUsers(){
        if(accountRepository.findByUsername("admin") == null){
            Account account = new Account(
                    "admin",
                    passwordEncoder.encode("admin"),
                    Arrays.asList(SecurityConfiguration.ADMIN, SecurityConfiguration.USER));
            accountRepository.save(account);
        }
        
        if(accountRepository.findByUsername("user") == null){
            Account account = new Account(
                    "user",
                    passwordEncoder.encode("user"),
                    Arrays.asList(SecurityConfiguration.USER));
            accountRepository.save(account);
        }
    }
}
