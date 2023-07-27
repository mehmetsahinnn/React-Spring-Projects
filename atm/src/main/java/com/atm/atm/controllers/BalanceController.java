package com.atm.atm.controllers;

import com.atm.atm.services.BalanceService;
import jakarta.persistence.SqlResultSetMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.security.Principal;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class BalanceController {

    private final BalanceService balanceService;
    private final SimpMessagingTemplate template;


    public BalanceController(BalanceService balanceService, SimpMessagingTemplate template) {
        this.balanceService = balanceService;
        this.template = template;
    }

    @MessageMapping("/getBalance")
    public void getBalance(Principal principal) {
        String username = principal.getName();
        double balance = balanceService.getBalanceForUser(username);
        template.convertAndSendToUser(username, "/topic/balanceUpdate", balance);
    }
}
