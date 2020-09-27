package com.progressoft.induction.atm;

import com.progressoft.induction.atm.exceptions.AccountNotFoundException;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class BankingSystemImpl implements BankingSystem {
    private Set<Account> bankAccounts;

    public BankingSystemImpl(){
        bankAccounts = new HashSet<>();
        bankAccounts.add(new Account("123456789", new BigDecimal(1000)));
        bankAccounts.add(new Account("111111111", new BigDecimal(1000)));
        bankAccounts.add(new Account("222222222", new BigDecimal(1000)));
        bankAccounts.add(new Account("333333333", new BigDecimal(1000)));
        bankAccounts.add(new Account("444444444", new BigDecimal(1000)));

    }
    @Override
    public BigDecimal getAccountBalance(String accountNumber) {
        Account account = getAccount(accountNumber);
        return account.getBalance();
    }

    @Override
    public void debitAccount(String accountNumber, BigDecimal amount) {
        Account account = getAccount(accountNumber);
        if (account.getBalance().compareTo(amount) < 0)
            throw new InsufficientFundsException();
        account.setBalance(account.getBalance().subtract(amount));
        bankAccounts.remove(account);
        bankAccounts.add(account);
    }

    public Account getAccount(String accountNumber){
        Account account = null;
        for(Account bankAccount : bankAccounts){
            if(bankAccount.getAccNo().equals(accountNumber)){
                account = bankAccount;
                break;
            }
        }
        if(account == null)
            throw new AccountNotFoundException();
        return account;
    }


}
