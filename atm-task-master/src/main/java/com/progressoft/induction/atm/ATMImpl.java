package com.progressoft.induction.atm;

import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ATMImpl implements ATM {

    private BankingSystem bankingSystem;

    private Map<Banknote, Integer> notes;

    public ATMImpl(){
        bankingSystem = new BankingSystemImpl();
        notes = new HashMap<>();
        notes.put(Banknote.FIFTY_JOD, 10);
        notes.put(Banknote.TWENTY_JOD, 20);
        notes.put(Banknote.TEN_JOD, 100);
        notes.put(Banknote.FIVE_JOD, 100);
    }

    @Override
    public List<Banknote> withdraw(String accountNumber, BigDecimal amount) {
        if(bankingSystem.getAccountBalance(accountNumber).compareTo(amount) < 0)
            throw new InsufficientFundsException();

        if(getTotalMoneyInAtm().compareTo(amount) < 0)
            throw new NotEnoughMoneyInATMException();

        BigDecimal amountToWithdraw = amount;

        System.out.println("Amount to be withdrawn: " + amount);
        List<Banknote> banknotes = new ArrayList<>();

      
        while(amount.compareTo(new BigDecimal(0)) != 0){

            //If amount is greater than or equal to 50 and if ATM has FIFTY_JOD available
            if(amount.compareTo(Banknote.FIFTY_JOD.getValue()) >= 0 && notes.get(Banknote.FIFTY_JOD) != 0){
                amount = getAmountAfterWithdrawSingleNote(banknotes, Banknote.FIFTY_JOD, amount);
            }

            //If amount is greater than or equal to 20 and if ATM has TWENTY_JOD available
            else if(amount.compareTo(Banknote.TWENTY_JOD.getValue()) >= 0 && notes.get(Banknote.TWENTY_JOD) != 0){
                amount = getAmountAfterWithdrawSingleNote(banknotes, Banknote.TWENTY_JOD, amount);
            }

            //If amount is greater than or equal to 10 and if ATM has TEN_JOD available
            else if(amount.compareTo(Banknote.TEN_JOD.getValue()) >= 0 && notes.get(Banknote.TEN_JOD) != 0){
                amount = getAmountAfterWithdrawSingleNote(banknotes, Banknote.TEN_JOD, amount);
            }

            //If amount is greater than or equal to 5
            else{
                // If ATM has FIVE_JOD available
                if(notes.get(Banknote.FIVE_JOD) != 0){
                    amount = getAmountAfterWithdrawSingleNote(banknotes, Banknote.FIVE_JOD, amount);
                }else
                    throw new NotEnoughMoneyInATMException();
            }
        }

        bankingSystem.debitAccount(accountNumber, amountToWithdraw);

        return banknotes;
    }

    private BigDecimal getTotalMoneyInAtm(){
        BigDecimal total = new BigDecimal(0);
        for(Map.Entry<Banknote, Integer> bankNote : notes.entrySet()){
            total = total.add(bankNote.getKey().getValue().multiply(new BigDecimal(bankNote.getValue())));
        }
        return total;
    }

    private BigDecimal getAmountAfterWithdrawSingleNote(List<Banknote> banknotes, Banknote banknote, BigDecimal amount){
        banknotes.add(banknote);
        notes.put(banknote, notes.get(banknote) - 1);
        amount = amount.subtract(banknote.getValue());
        System.out.println(banknote + " withdrawn: " + amount);
        return amount;
    }

}
