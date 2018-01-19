package demo.service;

import com.hazelcast.core.MapStore;
import demo.domain.Token;
import demo.repository.TokenRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TokenMapStore implements MapStore<String, Token>, ApplicationContextAware
{
    TokenRepository tokenRepo;
    ApplicationContext applicationContext;

    public TokenMapStore() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;

    }

    private TokenRepository getTokenRepository() {
        if (tokenRepo == null) {
            tokenRepo = applicationContext.getBean(TokenRepository.class);
        }
        return tokenRepo;
    }

    @Override
    public Token load(String key)
    {
        return getTokenRepository().findByToken(key);
    }

    @Override
    public Map<String, Token> loadAll(Collection<String> keys)
    {
        List<Token> tokens = getTokenRepository().findByTokens(keys);
        Map<String, Token> results = new HashMap<>();
        for (Token token : tokens) {
            results.put(token.getToken(), token);
        }

        return results;
    }

    @Override
    public Iterable<String> loadAllKeys() {
        return getTokenRepository().getTokens();
    }

    @Override
    public void store(String key, Token value)
    {
        getTokenRepository().save(value);
    }

    @Override
    public void storeAll(Map<String, Token> map)
    {
        map.forEach((s, token) -> getTokenRepository().save(token));
    }

    @Override
    public void delete(String key) {
        getTokenRepository().delete(this.load(key));
    }

    @Override
    public void deleteAll(Collection<String> keys)
    {
        keys.forEach(s -> delete(s));
    }
}