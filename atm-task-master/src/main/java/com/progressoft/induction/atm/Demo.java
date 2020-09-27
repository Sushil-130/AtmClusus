package com.progressoft.induction.atm;

import java.math.BigDecimal;

public class Demo {

	public static void main(String[] args) {
		ATMImpl atmlAtmImpl=new ATMImpl();
		System.out.println(atmlAtmImpl.withdraw("123456789", new BigDecimal(100)));

	}

}
