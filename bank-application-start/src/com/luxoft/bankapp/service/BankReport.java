package com.luxoft.bankapp.service;

import com.luxoft.bankapp.domain.Account;
import com.luxoft.bankapp.domain.Bank;
import com.luxoft.bankapp.domain.Client;

import java.util.*;

public class BankReport implements Report {
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
        SortedSet<Client> sortedSet = new TreeSet<>(Comparator.comparing(Client::getName));
        sortedSet.addAll(bank.getClients());
        return sortedSet;
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
        SortedSet<Account> sortedSet = new TreeSet<>((acc1, acc2) -> (int) (acc1.getBalance() - acc2.getBalance()));
        for (Client client : bank.getClients()) {
            sortedSet.addAll(client.getAccounts());
        }
        return sortedSet;
    }

    @Override
    public double getBankCreditSum(Bank bank) {
        return bank.getClients().stream()
                .map(client -> client.getAccounts().stream()
                        .filter(account -> account.maximumAmountToWithdraw() > account.getBalance())
                        .map(account -> account.maximumAmountToWithdraw() - account.getBalance())
                        .reduce(0.0, Double::sum))
                .reduce(0.0, Double::sum);

    }

    @Override
    public Map<Client, Collection<Account>> getCustomerAccounts(Bank bank) {
        Map<Client, Collection<Account>> accountsMap = new HashMap<>();
        bank.getClients().forEach(client -> {
            accountsMap.put(client, new ArrayList<>(client.getAccounts()));
        });

        return accountsMap;
    }

    @Override
    public Map<String, List<Client>> getClientsByCity(Bank bank) {
        Map<String, List<Client>> cityClientsMap = new TreeMap<>();
        bank.getClients().forEach(client -> {
            if (client.getCity() != null && !cityClientsMap.containsKey(client.getCity())) {
                List<Client> clientsArray = new ArrayList<>();
                clientsArray.add(client);
                cityClientsMap.put(client.getCity(), clientsArray);
            }
        });

        System.out.println("Client city statistics: ");
        cityClientsMap.forEach((city, clientArray) -> {
            System.out.println("City :" + city + " has clients: " + clientArray.toString());
        });

        return cityClientsMap;
    }
}
