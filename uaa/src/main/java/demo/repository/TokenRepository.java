package demo.repository;

import demo.domain.Token;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface TokenRepository extends CrudRepository<Token, Integer>, JpaSpecificationExecutor<Token> {
    List<Token> findByUsername(String username);

    @Query(value="select distinct t.token from Token t where t.username = ?1")
    List<String> findTokenByUserName(String username);

    @Query(value="select distinct t from Token t where t.username in ?1")
    List<Token> findByUserNames(Collection<String> usernames);

    Token findByToken(String token);

    @Query(value="select t from Token t where t.token in ?1")
    List<Token> findByTokens(Collection<String> tokens);

    @Query(value="select distinct t.token from Token t")
    List<String> getTokens();

    @Query(value="select distinct t.username from Token t")
    List<String> getUserNames();

}


