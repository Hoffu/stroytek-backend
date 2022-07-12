package com.backend.stroytek.repository;

import com.backend.stroytek.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Component
public class UserRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @SuppressWarnings("unused")
    public Optional<User> findByEmail(String email) {
        String queryText = "SELECT * FROM users WHERE email = :email";
        Query query = entityManager.createNativeQuery(queryText, User.class);
        query.setParameter("email", email);
        return query.getResultList().stream().findFirst();
    }
}
