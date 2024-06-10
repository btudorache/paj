package com.luxoft.bankapp.cache;

import com.luxoft.bankapp.domain.AbstractAccount;
import com.luxoft.bankapp.factory.AccountFactory;

import java.util.HashMap;
import java.util.Map;

public class AccountCache {
    private final Map<String, AbstractAccount> accountsMap = new HashMap<>();
    public AccountCache() {
        accountsMap.put("CHECKING", AccountFactory.newAccount("CHECKING", 0, 0.0, 0.0));
        accountsMap.put("SAVING", AccountFactory.newAccount("SAVING", 0, 0.0, 0.0));
    }

    public void loadCache(String type, AbstractAccount account) {
        if (!accountsMap.containsKey(type)) {
            System.out.println("Invalid account type");
            return;
        }

        accountsMap.put(type, account);
    }

    public AbstractAccount cloneAccount(String type) {
        if (!accountsMap.containsKey(type)) {
            System.out.println("Invalid account type");
            return null;
        }

        return accountsMap.get(type).clone();
    }
}
