package com.luxoft.bankapp.factory;

import com.luxoft.bankapp.domain.AbstractAccount;
import com.luxoft.bankapp.domain.CheckingAccount;
import com.luxoft.bankapp.domain.SavingAccount;

public class AccountFactory {
    public static AbstractAccount newAccount(String accountType, int id, double amount, double overdraft) {
        if (accountType == null) {
            return null;
        }
        return switch (accountType.toUpperCase()) {
            case "CHECKING" -> new CheckingAccount(id, amount, overdraft);
            case "SAVING" -> new SavingAccount(id, amount);
            default -> null;
        };
    }
}
