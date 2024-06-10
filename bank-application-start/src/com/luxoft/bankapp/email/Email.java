package com.luxoft.bankapp.email;

import com.luxoft.bankapp.domain.Client;

public class Email {
    private Client client;
    private String from;
    private String to;
    private String content;

    public Email(Client client, String from, String to, String content) {
        this.client = client;
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
