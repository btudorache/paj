package com.luxoft.bankapp.email;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EmailQueue {
    private final Queue<Email> queue;

    public EmailQueue() {
        this.queue = new LinkedList<>();
    }
    public synchronized void addEmail(Email email) {
        queue.add(email);
    }

    public synchronized Email extractEmail() {
        return queue.remove();
    }

    public int getSize() {
        return queue.size();
    }

    public void clear() {
        queue.clear();
    }
}
