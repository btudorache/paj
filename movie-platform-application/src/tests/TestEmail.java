package tests;

import domain.User;
import email.EmailService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.JUnit4;
import repository.DatabaseRepositoryLoader;
import repository.MovieRepository;

import java.util.Map;

public class TestEmail {

    @Test
    public void testSubscriptionRenewalEmailService() throws InterruptedException {
        MovieRepository repository = DatabaseRepositoryLoader.getSampleRepository();
        EmailService emailService = repository.getEmailService();

        repository.renewSubscription("user1_simple", 50);
        Thread.sleep(1000);

        repository.renewSubscription("user2_simple", 50);
        Thread.sleep(1000);

        // subscription not renewed for this user
        repository.renewSubscription("user1_premium", 40);
        Thread.sleep(1000);

        Assert.assertEquals(emailService.getSentEmails(), 2);
    }
}
