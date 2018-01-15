package auth.clients;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.Optional;

public class ClientService implements ClientDetailsService {

    private ClientRepository clientRepository;

    ClientService(ClientRepository repository) {
        clientRepository = repository;
    }

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        Optional<Client> clientOptional = clientRepository.findByClientId(s);

        Client client = clientOptional.orElseThrow(() -> new ClientRegistrationException(String.format(
                "no client %s registered", s)));

        BaseClientDetails details = new BaseClientDetails(client.getClientId(),
                null, client.getScopes(), client.getAuthorizedGrantTypes(), client
                .getAuthorities());

        details.setClientSecret(client.getSecret());

        return details;
    }
}
