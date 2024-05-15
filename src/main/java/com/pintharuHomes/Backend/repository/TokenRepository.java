package com.pintharuHomes.Backend.repository;

import com.pintharuHomes.Backend.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query("""
            Select t from Token t inner join User u 
            on t.user.id = u.id
            where t.user.id = :userID and t.loggedOut = false 
    """)
    List<Token> findAllTokenByUser(Integer userID);

    Optional<Token> findByToken(String token);

}
