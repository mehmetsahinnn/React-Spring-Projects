package com.atm.atm.controllers;

import com.atm.atm.models.Transactions;
import com.atm.atm.models.User;
import com.atm.atm.services.TransactionService;
import com.atm.atm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final UserService userService;

    @Autowired
    public TransactionController(TransactionService transactionService, UserService userService) {
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @PostMapping("/{username}/deposit/{amount}")
    public ResponseEntity<User> deposit(@PathVariable String username, @PathVariable Double amount) {
        User user = userService.findByUsername(username);
        if (amount > 0) {
            user.setBalance(user.getBalance() + amount);
            userService.save(user);

            Transactions transaction = new Transactions();
            transaction.setType("DEPOSIT");
            transaction.setAmount(amount);
            transaction.setUser(user);
            transaction.setTransactionDate(new java.util.Date());
            transactionService.save(transaction);

            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.ok(user);
        }
    }


    @PostMapping("/{username}/withdraw/{amount}")
    public ResponseEntity<User> withdraw(@PathVariable String username, @PathVariable Double amount) {
        User user = userService.findByUsername(username);
        if (user.getBalance() >= amount && amount > 0) {
            user.setBalance(user.getBalance() - amount);
            userService.save(user);

            Transactions transaction = new Transactions();
            transaction.setType("WITHDRAW");
            transaction.setAmount(amount);
            transaction.setUser(user);
            transaction.setTransactionDate(new java.util.Date());
            transactionService.save(transaction);

            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.ok(user);
        }
    }
}

