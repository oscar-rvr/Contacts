package contacts;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {



        Scanner scanner = new Scanner(System.in);
        ContactService contactService = new ContactService();
        ContactsApp app = new ContactsApp(contactService);
        app.run();
    }
}
