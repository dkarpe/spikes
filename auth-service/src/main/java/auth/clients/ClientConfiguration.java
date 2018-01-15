package auth.clients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetailsService;

@Configuration
public class ClientConfiguration {

 @Bean
 ClientDetailsService clientDetailsService(ClientRepository clientRepository) {
     return new ClientService(clientRepository);
 }

}
