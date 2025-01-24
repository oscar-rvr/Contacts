package contacts;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsApp {
    private Scanner scanner = new Scanner(System.in);
    private ContactService contactService;
    private final List<Contact> contacts = new ArrayList<>();

    public ContactsApp(ContactService contactService){
//this.scanner=scanner;
        this.contactService=contactService;
    }

    public void run(){

        while(true) {
            System.out.println("Enter action (add, remove, edit, count, list, exit):");
            String action = scanner.nextLine().toLowerCase();

            switch (action) {
                case "add" -> addContact();
                case "remove" -> removeContact();
                case "edit" -> editContact();
                case "count" -> countContacts();
                case "list" -> listContacts();
                case "exit" -> {
                    return;
                }
                default -> System.out.println("Invalid action");
            }
        }


    }

    private void addContact(){
        System.out.println("Enter the name:");
        String name=scanner.nextLine();

        System.out.println("Enter the surname of the person:");
        String surname = scanner.nextLine();

        System.out.println("Enter the number:");
        String phoneNumber = scanner.nextLine();



        contacts.add(new Contact(name,surname,phoneNumber));
        System.out.println("A record created!");
        System.out.println("A Phone Book with a single record created!");
    }

    private void removeContact(){
        if (contacts.isEmpty()){
            System.out.println("No records to remove");
            return;
        }else {
            listContacts();
            System.out.println("Select a record:");
            int record = scanner.nextInt();
            contacts.remove(record-1);

        }
    }

    private void listContacts(){
        if(contacts.isEmpty()){
            System.out.println("No records to list!");
            return;
        }else{
            for(int i = 0; i< contacts.size(); i++){
                System.out.println(( i+1)+". "+contacts.get(i).getName()+" "+contacts.get(i).getSurname()+", "+contacts.get(i).getPhoneNumber());
            }
        }

    }

    private void countContacts(){
        System.out.println("The Phone Book has "+contacts.size()+" records.");
    }
    private void editContact(){
        if(contacts.isEmpty()){
            System.out.println("No records to edit!");
        }else{
            listContacts();
            System.out.println("Select a record:");
            int select = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Select a field (name, surname, number):");
            String field = scanner.nextLine();
            switch (field){
                case "name" -> {
                    System.out.println("Enter name:");
                    contacts.get(select-1).setName(scanner.nextLine());
                }
                case "surname" -> {
                    System.out.println("Enter surname:");
                    contacts.get(select-1).setSurname(scanner.nextLine());
                }
                case "number" -> {
                    System.out.println("Enter number");
                    contacts.get(select-1).setPhoneNumber(scanner.nextLine());
                }
            }

        }
    }

}
