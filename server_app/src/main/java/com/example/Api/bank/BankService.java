package com.example.Api.bank;

import com.example.Api.exception.ApiNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class BankService {

    private final BankRepository bankRepository;

    @Autowired
    public BankService(BankRepository bankRepository){
        this.bankRepository = bankRepository;
    }

    public Bank getBank(String bic){
        Optional<Bank> bankOptional = bankRepository.findBankByBic(bic);

        if(!bankOptional.isPresent()){
            throw new ApiNotFoundException("This bank : " + bic+ " doesn't exist");

        }
        return bankOptional.get();
    }

    public List<Bank> getBanks(){
        return bankRepository.findAll();
    }
}