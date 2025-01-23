package contacts;
import java.util.Scanner;

public class ContactsApp {
    private Scanner scanner = new Scanner(System.in);
    private ContactService contactService;

    public ContactsApp(ContactService contactService){
//this.scanner=scanner;
        this.contactService=contactService;
    }

    public void run(){
        System.out.println("Enter the name of the person:");
        String name = scanner.nextLine();

        System.out.println("Enter the surname of the person:");
        String surname = scanner.nextLine();

        System.out.println("Enter the number:");
        String phoneNumber = scanner.nextLine();

        Contact contact = new Contact(name,surname,phoneNumber);

        System.out.println("A record created!");
        System.out.println("A Phone Book with a single record created!");
    }
}
