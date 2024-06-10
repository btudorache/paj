package email;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Queue implements Serializable {
	private final List<Email> emails = Collections.synchronizedList(new LinkedList<Email>());
	
    public void add(Email email) {
        emails.add(email);
    }

    public Email get() {
        if (emails.size() > 0) {
            return emails.remove(emails.size() - 1);
        } 
        
        return null;
    }
    
}
