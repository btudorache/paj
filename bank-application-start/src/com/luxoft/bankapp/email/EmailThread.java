package com.luxoft.bankapp.email;

public class EmailThread extends Thread {
    private EmailQueue queue;
    private boolean running = true;
    private final Object monitor;

    public EmailThread(EmailQueue queue, Object monitor) {
        this.queue = queue;
        this.monitor = monitor;
    }

    public void stopRunning() {
        this.running = false;
    }

    public void run() {
        while (running) {
            synchronized (monitor) {
                while (queue.getSize() > 0) {
                    Email email = queue.extractEmail();
                    System.out.println("Sending mail from: " + email.getFrom() + " to " + email.getTo() + " with message: " + email.getContent());
                }
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
