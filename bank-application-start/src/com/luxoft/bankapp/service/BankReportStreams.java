package com.luxoft.bankapp.service;

import com.luxoft.bankapp.domain.Account;
import com.luxoft.bankapp.domain.Bank;
import com.luxoft.bankapp.domain.CheckingAccount;
import com.luxoft.bankapp.domain.Client;

import java.util.*;
import java.util.stream.Collectors;

public class BankReportStreams implements Report {
    @Override
    public int getNumberOfClients(Bank bank) {
        return bank.getClients().size();
    }

    @Override
    public int getNumberOfAccounts(Bank bank) {
        return bank.getClients().stream()
                .map(client -> client.getAccounts().size())
                .reduce(0, Integer::sum);
    }

    @Override
    public SortedSet<Client> getClientsSorted(Bank bank) {
        return bank.getClients().stream().collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Client::getName))));
    }

    @Override
    public double getTotalSumInAccounts(Bank bank) {
        return bank.getClients().stream()
                .map(client -> client.getAccounts().stream()
                        .map(Account::getBalance)
                        .reduce(0.0, Double::sum))
                .reduce(0.0, Double::sum);
    }

    @Override
    public SortedSet<Account> getAccountsSortedBySum(Bank bank) {
        return bank.getClients().stream()
                .flatMap(client -> client.getAccounts().stream())
                .collect(Collectors.toCollection(() -> new TreeSet<>((acc1, acc2) -> (int) (acc1.getBalance() - acc2.getBalance()))));
    }

    @Override
    public double getBankCreditSum(Bank bank) {
        return bank.getClients().stream()
                .map(client -> client.getAccounts().stream()
                        .filter(account -> account.getBalance() < 0 && account instanceof CheckingAccount)
                        .map(account -> -account.getBalance())
                        .reduce(0.0, Double::sum))
                .reduce(0.0, Double::sum);

    }

    @Override
    public Map<Client, Collection<Account>> getCustomerAccounts(Bank bank) {
        return bank.getClients().stream()
                .collect(Collectors.toMap(client -> client, Client::getAccounts));
    }

    @Override
    public Map<String, List<Client>> getClientsByCity(Bank bank) {
        Map<String, List<Client>> cityClientsMap = bank.getClients().stream()
                        .filter(client -> client.getCity() != null)
                        .collect(Collectors.groupingBy(Client::getCity));

        System.out.println("Client city statistics: ");
        cityClientsMap.forEach((city, clientArray) -> {
            System.out.println("City :" + city + " has clients: " + clientArray.toString());
        });

        return cityClientsMap;
    }
}
