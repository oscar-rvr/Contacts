package contacts.backUp;

import java.lang.Record;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        String filePath = args.length > 0 ? args[0] : null;
        Phonebook phonebook = new Phonebook(filePath);


        ContactsApp app = new ContactsApp(phonebook);


        app.run();
    }
}
