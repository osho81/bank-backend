package com.yaelev.bank.controller;

import com.yaelev.bank.model.TransactionAccount;
import com.yaelev.bank.repository.TransactionAccountRepository;
import com.yaelev.bank.service.TransactionAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/api/v1/t-account")
public class TransactionAccountController {
    private final TransactionAccountRepository transactionAccountRepository;
    private final TransactionAccountService transactionAccountService;

    @Autowired
    public TransactionAccountController(TransactionAccountRepository transactionAccountRepository, TransactionAccountService transactionAccountService) {
        this.transactionAccountRepository = transactionAccountRepository;
        this.transactionAccountService = transactionAccountService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TransactionAccount>> getTransactionAccounts() {
        return ResponseEntity.ok().body(transactionAccountService.getTransactionAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionAccount> getTransactionAccountById(@PathVariable("id") long id) {
        return transactionAccountService.getTransactionAccountById(id);
    }

    // Customized mapping, find accounts by customer id
    @GetMapping("/by/{id}") // note: by customer-id
    public List<TransactionAccount> getTransactionAccountByCustomer(@PathVariable("id") long id) {
        return transactionAccountService.getTransactionAccountsByCustomer(id);
    }

    @PostMapping()
    public void registerNewTransactionAccount(@RequestBody TransactionAccount transactionAccount) {
        transactionAccountService.registerTransactionAccount(transactionAccount);
    }

    @PutMapping("/{id}")
    public void updateTransactionAccount(@PathVariable("id") long id,
                                         @RequestBody TransactionAccount transactionAccount) {
        transactionAccountService.updateTransactionAccount(id, transactionAccount);
    }

    @DeleteMapping("/{id}")
    public void deleteTransactionAccount(@PathVariable("id") long id) {
        transactionAccountService.deleteTransactionAccount(id);
    }



}
