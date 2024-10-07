package com.example.incomesystem.Service;

import com.example.incomesystem.Model.Transaction;
import com.example.incomesystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repo;

    public Transaction addTransaction(Transaction transaction){
        Double credited = transaction.getCredited() != null ? transaction.getCredited() : 0;
        Double debited = transaction.getDebited() != null ? transaction.getDebited() : 0;
        transaction.setTotalbalance(credited - debited);
        return repo.save(transaction);
    }

    public List<Transaction> getTransactionByDate(LocalDate date){
        return repo.findByDate(date);
    }

    public List<Transaction> getTransactionsByMonth(int month, int year){
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
        return repo.findByDateBetween(startDate, endDate);
    }

    public Double getCurrentBalance(){
        List<Transaction> transactions = repo.findAll();
        return transactions.stream()
                .mapToDouble(Transaction::getTotalbalance)
                .sum();
    }
}
