package com.luxoft.bankapp.tests;

import com.luxoft.bankapp.domain.CheckingAccount;
import com.luxoft.bankapp.domain.SavingAccount;
import com.luxoft.bankapp.exceptions.NotEnoughFundsException;
import com.luxoft.bankapp.exceptions.OverdraftLimitExceededException;
import com.luxoft.bankapp.factory.AccountFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestFactory {

    @Test
    public void testSavingAccount() throws NotEnoughFundsException {
        SavingAccount savingAccount = (SavingAccount) AccountFactory.newAccount("SAVING", 1, 1000.0, 0.0);
        assertEquals(savingAccount.getClass().getSimpleName(), SavingAccount.class.getSimpleName());

        savingAccount.deposit(100.0);
        savingAccount.withdraw(50.0);
        assertEquals(1, savingAccount.getId());
        assertEquals(1050, savingAccount.getBalance(), 0);
        assertEquals(1050, savingAccount.maximumAmountToWithdraw(), 0);
    }

    @Test
    public void testCheckingAccount() throws OverdraftLimitExceededException {
        CheckingAccount checkingAccount = (CheckingAccount) AccountFactory.newAccount("CHECKING", 2, 1000.0, 100.0);
        assertEquals(checkingAccount.getClass().getSimpleName(), CheckingAccount.class.getSimpleName());

        checkingAccount.deposit(100.0);
        checkingAccount.withdraw(1150.0);
        assertEquals(2, checkingAccount.getId());
        assertEquals(-50, checkingAccount.getBalance(), 0);
        assertEquals(100, checkingAccount.getOverdraft(), 0);
        assertEquals(50, checkingAccount.maximumAmountToWithdraw(), 0);
    }
}
