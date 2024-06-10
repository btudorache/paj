package com.luxoft.bankapp.email;

public class EmailService {

    private final EmailQueue emailQueue;
    private final EmailThread emailThread;
    private final Object monitor = new Object();

    public EmailService() {
        emailQueue = new EmailQueue();
        emailThread = new EmailThread(emailQueue, monitor);
        emailThread.start();
    }

    public void sendNotificationEmail(Email email) {
        emailQueue.addEmail(email);
        synchronized (monitor) {
            monitor.notify();
        }
    }

    public void close() {
        emailQueue.clear();
        emailThread.stopRunning();
        synchronized (monitor) {
            monitor.notify();
        }
    }
}
