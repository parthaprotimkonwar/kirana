package com.generic.core.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generic.core.model.entities.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, String>{

}
