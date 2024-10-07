package com.example.incomesystem.Controller;

import com.example.incomesystem.Model.Transaction;
import com.example.incomesystem.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@Validated
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping("/add")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction){
        return ResponseEntity.ok(service.addTransaction(transaction));
    }

    @GetMapping("/data/{date}")
    public ResponseEntity<List<Transaction>> getTransactionByDate(@PathVariable String date){
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(service.getTransactionByDate(localDate));
    }

    @GetMapping("/month/{month}/{year}")
    public ResponseEntity<List<Transaction>> getTransactionsByMonth(@PathVariable int month, @PathVariable int year){
        return ResponseEntity.ok(service.getTransactionsByMonth(month, year));
    }

    @GetMapping("/balance/current")
    public ResponseEntity<Double> getCurrentBalance(){
        return ResponseEntity.ok(service.getCurrentBalance());
    }
}
