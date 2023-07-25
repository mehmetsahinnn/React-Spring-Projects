package com.atm.atm.repositories;

import com.atm.atm.models.Transactions;
import com.atm.atm.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Integer> {

}
