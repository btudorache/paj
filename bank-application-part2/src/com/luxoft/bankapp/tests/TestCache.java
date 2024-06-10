package com.luxoft.bankapp.tests;

import com.luxoft.bankapp.cache.AccountCache;
import com.luxoft.bankapp.domain.AbstractAccount;
import com.luxoft.bankapp.domain.CheckingAccount;
import com.luxoft.bankapp.domain.SavingAccount;
import com.luxoft.bankapp.exceptions.NotEnoughFundsException;
import com.luxoft.bankapp.exceptions.OverdraftLimitExceededException;
import com.luxoft.bankapp.factory.AccountFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCache {
    @Test
    public void testSavingAccount() throws NotEnoughFundsException {
        SavingAccount savingAccount = (SavingAccount) new AccountCache().cloneAccount("SAVING");
        assertEquals(savingAccount.getClass().getSimpleName(), SavingAccount.class.getSimpleName());

        savingAccount.deposit(100.0);
        savingAccount.withdraw(50.0);
        assertEquals(0, savingAccount.getId());
        assertEquals(50, savingAccount.getBalance(), 0);
        assertEquals(50, savingAccount.maximumAmountToWithdraw(), 0);
    }

    @Test
    public void testCheckingAccount() throws OverdraftLimitExceededException {
        CheckingAccount checkingAccount = (CheckingAccount) new AccountCache().cloneAccount("CHECKING");
        assertEquals(checkingAccount.getClass().getSimpleName(), CheckingAccount.class.getSimpleName());

        assertEquals(0, checkingAccount.getId());
        assertEquals(0, checkingAccount.getBalance(), 0);
        assertEquals(0, checkingAccount.getOverdraft(), 0);
        assertEquals(0, checkingAccount.maximumAmountToWithdraw(), 0);
    }
}
