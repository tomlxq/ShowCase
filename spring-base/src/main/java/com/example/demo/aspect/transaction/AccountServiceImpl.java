package com.example.demo.aspect.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class AccountServiceImpl implements AccountServiceInterface {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account find(long id) {
        Query query = entityManager.createQuery("from Account a where a.id=:id").setParameter("id", id);
        return (Account) query.getSingleResult();
    }

    @Override
    @Transactional
    public void create(Account account) {
        entityManager.persist(account);
    }

}