package email;

import domain.User;

import java.io.Serializable;
import java.util.ArrayList;

public class Email implements Serializable {
    private ArrayList<User> to;
    private String title, body;

    public Email() {
        title = "Subscription expired";
        body = "Your subscription has expired. Please renew your subscription";
    }


    public ArrayList<User> getTo() {
        return to;
    }

    public Email setTo(ArrayList<User> to) {
        this.to = to;
        return this;
    }

    public Email setTo(User to) {
    	ArrayList<User> toList = new ArrayList<User>();
    	toList.add(to);
        setTo(toList);
        return this;
    }


    public String getTitle() {
        return title;
    }

    public Email setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBody() {
        return body;
    }

    public Email setBody(String body) {
        this.body = body;
        return this;
    }
    
    @Override
    public String toString() {
    	ArrayList<User> clients = getTo();
    	StringBuilder clientsTo = new StringBuilder();
    	for (User c: clients) {
    		clientsTo.append(c);
    	}
    	
    	return "SEND EMAIL:" + "\n" +
    			"To: " + clientsTo +
    			"Title: " + getTitle() + "\n" +
    			"Body: " + getBody() + "\n";
    }
}