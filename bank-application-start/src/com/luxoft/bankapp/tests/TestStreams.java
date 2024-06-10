package com.luxoft.bankapp.tests;

import com.luxoft.bankapp.domain.*;
import com.luxoft.bankapp.exceptions.ClientExistsException;
import com.luxoft.bankapp.service.BankReportStreams;
import com.luxoft.bankapp.service.BankService;
import org.junit.Test;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestStreams {

    @Test
    public void testBankReportStreams() throws ClientExistsException {
        Bank bank = new Bank();
        Client client1 = new Client("Smith John", Gender.MALE, "Bucharest");
        client1.addAccount(new SavingAccount(1, 800.0));
        client1.addAccount(new CheckingAccount(2, -300.0, 100.0));

        Client client2 = new Client("Smith Michelle", Gender.FEMALE, "Paris");
        client2.addAccount(new SavingAccount(3, 2000.0));
        client2.addAccount(new CheckingAccount(4, -200.0, 200.0));

        BankService.addClient(bank, client1);
        BankService.addClient(bank, client2);

        BankReportStreams bankReportStreams = new BankReportStreams();

        // getNumberOfClients
        assertEquals(bankReportStreams.getNumberOfClients(bank), 2);

        // getNumberOfAccounts
        assertEquals(bankReportStreams.getNumberOfAccounts(bank), 4);

        // getClientsSorted
        SortedSet<Client> sortedClients = bankReportStreams.getClientsSorted(bank);
        assertEquals(sortedClients.first().getName(), "Smith John");
        assertEquals(sortedClients.last().getName(), "Smith Michelle");

        // getTotalSumInAccounts
//        assertEquals(bankReportStreams.getTotalSumInAccounts(bank), 5500.0, 0.1);

        // getAccountsSortedBySum
//        SortedSet<Account> sortedSumClients = bankReportStreams.getAccountsSortedBySum(bank);
//        assertEquals(sortedSumClients.first().getBalance(), 800.0, 0.1);
//        assertEquals(sortedSumClients.last().getBalance(), 2000.0, 0.1);

        // getBankCreditSum
        assertEquals(bankReportStreams.getBankCreditSum(bank), 500.0, 0.1);

        // getCustomerAccounts
        Map<Client, Collection<Account>> customerAccount = bankReportStreams.getCustomerAccounts(bank);
        assertTrue(customerAccount.containsKey(client1));
        assertTrue(customerAccount.containsKey(client2));
        assertEquals(customerAccount.get(client1).size(), 2);
        assertEquals(customerAccount.get(client2).size(), 2);

        // getClientsByCity
        Map<String, List<Client>> clientsCityMap = bankReportStreams.getClientsByCity(bank);
        assertTrue(clientsCityMap.containsKey("Bucharest"));
        assertTrue(clientsCityMap.containsKey("Paris"));
        assertEquals(clientsCityMap.get("Bucharest").size(), 1);
        assertEquals(clientsCityMap.get("Paris").size(), 1);
    }
}
