package com.saih.playfy.repository;

import com.saih.playfy.entity.LinkedAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkedAccountsRepository extends MongoRepository<LinkedAccount, String> {

    public List<LinkedAccount> findByUserId(String userId);
}
