package contacts;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsApp {
    private Scanner scanner = new Scanner(System.in);
    private ContactService contactService;
    //private final List<Contact> contacts = new ArrayList<>();
    private final List<Record>  contacts = new ArrayList<>();



    public ContactsApp(ContactService contactService){

        this.contactService=contactService;
    }

    public void run(){

        while(true) {
            System.out.println("\nEnter action (add, remove, edit, count, info, exit):");
            String action = scanner.nextLine().toLowerCase();

            switch (action) {
                case "add" -> addContact();
                case "remove" -> removeContact();
                case "edit" -> editContact();
                case "count" -> countContacts();
                case "info" -> info();
                case "exit" -> {
                    return;
                }
                default -> System.out.println("Invalid action");
            }
        }


    }

    private void addContact(){
        System.out.println("Enter the type (person, organization):");
        String type =scanner.nextLine();
        if(type.equals("person")){

            System.out.println("Enter the name:");
            String name=scanner.nextLine();

            System.out.println("Enter the surname of the person:");
            String surname = scanner.nextLine();

            System.out.println("Enter the birth date:");
            String birthDateString = scanner.nextLine();
            LocalDate birthDate = verifybirthDate(birthDateString);

           // LocalDate birthDate = LocalDate.parse(birthDateString);
            System.out.println("Enter the gender (M, F):");
            String gender = scanner.nextLine();
            if(!verifyGender(gender)){
                System.out.println("Bad gender!");
                gender = "";
            }
            System.out.println("Enter the number:");
            String phoneNumber = scanner.nextLine();



            contacts.add(new Person(name,surname, phoneNumber,gender, birthDate,true));
            System.out.println("The record added.");
        }else{
            System.out.println("Enter the organization name:");
            String name=scanner.nextLine();
            System.out.println("Enter the address:");
            String address = scanner.nextLine();
            System.out.println("Enter the number:");
            String phoneNumber = scanner.nextLine();
            contacts.add(new Organization(name,address,phoneNumber,false));
        }

    }

    private LocalDate verifybirthDate(String birthDateString) {

        try{

             return LocalDate.parse(birthDateString);
        }catch (Exception e){
            System.out.println("Bad birth date!");
            return null;
        }


    }

    private boolean verifyGender(String gender) {
        if(gender != "M" || gender != "m" || gender != "H" || gender != "h"){
            return false;
        }
        return true;
    }


        private void removeContact(){
            if (contacts.isEmpty()){
                System.out.println("No records to remove");
                return;
            }else {
                listContacts();
                System.out.println("Select a record:");
                int record = scanner.nextInt();
                //scanner.nextLine();
                contacts.remove(record-1);

            }
        }

    private void info(){
        listContacts();

            System.out.println("Enter index to show info:");
            String input = scanner.nextLine();
            int index = Integer.parseInt(input);
            index=index-1;
            if(contacts.get(index).isPerson() && index<contacts.size()){
                Person personObj = (Person) (contacts.get(index));
                System.out.println("Name: "+personObj.getName());
                System.out.println("Surname: "+personObj.getSurname());
                if(personObj.getBirthDate()==null){
                    System.out.println("Birth date: [no data]");
                }else{
                    System.out.println("Birth date: "+personObj.getBirthDate());
                }

                if(personObj.getGender()==""){
                    System.out.println("Gender: [no data]");
                }else{
                    System.out.println("Gender: "+personObj.getGender());

                }
                System.out.println("Number: "+personObj.getPhoneNumber());
                System.out.println("Time created: "+personObj.getCreatedDate());
                System.out.println("Time last edit: "+personObj.getLastEditedDate());

            }else{
                Organization organizationObj = (Organization) contacts.get(index);
                System.out.println("Organization name: "+organizationObj.getOrganizationName());
                System.out.println("Address: "+organizationObj.getAddress());
                System.out.println("Number: "+organizationObj.getPhoneNumber());
                System.out.println("Time created: "+organizationObj.getCreatedDate());
                System.out.println("Time last edit: "+organizationObj.getLastEditedDate());


            }

    }


private void listContacts() {
    if (contacts.isEmpty()) {
        System.out.println("No records to list!");
        return;
    } else {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).isPerson()) {
                Person personObj = (Person) contacts.get(i);
                System.out.println((i + 1) + ". " + personObj.getName() + " " + personObj.getSurname());
            } else {
                Organization organizationObj = (Organization) contacts.get(i);
                System.out.println((i + 1) + ". " + organizationObj.getOrganizationName());

            }

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
            int select = scanner.nextInt()-1;
            scanner.nextLine();

            if(contacts.get(select).isPerson()){
                Person personObj = (Person) contacts.get(select);
                System.out.println("Select a field (name, surname, birth, gender, number):");
                String field = scanner.nextLine();
                switch (field){
                    case "name" ->{
                        System.out.println("Enter the name: ");
                        personObj.setName(scanner.nextLine());
                        personObj.setLastEditedDate(LocalDate.now());
                    }
                    case "surname" ->{
                        System.out.println("Enter the surname: ");
                        personObj.setSurname(scanner.nextLine());
                        personObj.setLastEditedDate(LocalDate.now());
                    }
                    case "birth" ->{
                        System.out.println("Enter the birth date: ");
                        personObj.setBirthDate(verifybirthDate(scanner.nextLine()));
                        personObj.setLastEditedDate(LocalDate.now());
                    }
                    case "gender" ->{
                        System.out.println("Enter the gender (M, F):");
                        personObj.setGender(scanner.nextLine());
                        personObj.setLastEditedDate(LocalDate.now());
                    }
                    case "number" ->{
                        System.out.println("Enter the number:");
                        personObj.setPhoneNumber(scanner.nextLine());

                        personObj.setLastEditedDate(LocalDate.now());
                    }
                    default -> System.out.println("Invalid field");





                }
            }else{
                Organization organizationObj = (Organization) contacts.get(select);
                System.out.println("Select a field (address, number):");
                String field = scanner.nextLine();
                switch (field){
                    case "address" ->{
                        System.out.println("Enter the address: ");
                        organizationObj.setAddress(scanner.nextLine());
                        organizationObj.setLastEditedDate(LocalDate.now());
                    }
                    case "number" ->{
                        System.out.println("Enter the number: ");
                        organizationObj.setPhoneNumber(scanner.nextLine());
                        organizationObj.setLastEditedDate(LocalDate.now());
                    }
                    default -> System.out.println("Invalid field");





                }
            }



        }
    }


}

