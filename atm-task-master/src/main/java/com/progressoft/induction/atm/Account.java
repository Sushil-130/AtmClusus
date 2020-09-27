package com.progressoft.induction.atm;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private String accNo;

    private BigDecimal balance;

    public Account(){}

    public Account(String accNo, BigDecimal balance) {
        this.accNo = accNo;
        this.balance = balance;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

   

    @Override
	public String toString() {
		return "Account [accNo=" + accNo + ", balance=" + balance + "]";
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accNo, account.accNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accNo);
    }
}
