package contacts.backUp;

import java.io.*;
import java.lang.Record;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Phonebook {
    private List<AbstractRecord> contacts = new ArrayList<>();
    private File file;

    public Phonebook(String filepath) {

        if (filepath == null) {
            file = new File("phonebook.db");
        } else {
            file = new File(filepath);
            load();
        }
    }

    private void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            contacts = (List<AbstractRecord>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void addContact(AbstractRecord record) {
        contacts.add(record);
        save();
    }



    public int count(){
        return contacts.size();
    }

    public void listContacts() {
        if (count()<1) {
            System.out.println("No records to list!");
            return;
        } else {

            for (int i = 0; i < contacts.size(); i++) {

                System.out.println((i + 1) + ". " + contacts.get(i).printName());
            }

        }
    }

    public void listActions(String action) {

        if (action.equals("back")){
            return;
        }

        int index = Integer.valueOf(action);
         if(index >0 && index <= contacts.size() ){

            contacts.get(index-1).printInfo();


            recordsActions(index -1);


        }


    }

    public void recordsActions(int index) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n[record] Enter action (edit, delete, menu):");
         String recordAction = scanner.nextLine();
         switch (recordAction){
        case "edit" ->{editContact(index);}
        case "delete" ->{deleteContact(index);}
        case "menu" ->{
            return;
        }
        default -> {
            System.out.println("Invalid Action");
        }


    }
    }
    public void deleteContact(int index){
        contacts.remove(index);

    }


    private void editContact(int index) {
        if (contacts.isEmpty()) {
            System.out.println("No records to edit!");
        }

        Scanner scanner = new Scanner(System.in);
        if (contacts.get(index) instanceof Person) {
            Person personObj = (Person) contacts.get(index);
            System.out.println("Select a field (name, surname, birth, gender, number):");
            String field = scanner.nextLine();
            switch (field) {
                case "name" -> {
                    System.out.println("Enter the name: ");
                    personObj.setName(scanner.nextLine());
                    personObj.setLastEditedDate(LocalDate.now());
                }
                case "surname" -> {
                    System.out.println("Enter the surname: ");
                    personObj.setSurname(scanner.nextLine());
                    personObj.setLastEditedDate(LocalDate.now());
                }
                case "birth" -> {
                    System.out.println("Enter the birth date: ");
                    personObj.setBirthDate(verifybirthDate(scanner.nextLine()));
                    personObj.setLastEditedDate(LocalDate.now());
                }
                case "gender" -> {
                    System.out.println("Enter the gender (M, F):");
                    personObj.setGender(scanner.nextLine());
                    personObj.setLastEditedDate(LocalDate.now());
                }
                case "number" -> {
                    System.out.println("Enter the number:");
                    personObj.setPhoneNumber(scanner.nextLine());

                    personObj.setLastEditedDate(LocalDate.now());
                }
                default -> System.out.println("Invalid field");


            }
        } else {
            Organization organizationObj = (Organization) contacts.get(index);
            System.out.println("Select a field (address, number):");
            String field = scanner.nextLine();
            switch (field) {
                case "address" -> {
                    System.out.println("Enter the address: ");
                    organizationObj.setAddress(scanner.nextLine());
                    organizationObj.setLastEditedDate(LocalDate.now());
                }
                case "number" -> {
                    System.out.println("Enter the number: ");
                    organizationObj.setPhoneNumber(scanner.nextLine());
                    organizationObj.setLastEditedDate(LocalDate.now());
                }
                default -> System.out.println("Invalid field");


            }
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

    public List<AbstractRecord> searchContacts(String query) {
        List<AbstractRecord> results = new ArrayList<>();
results.clear();

        for (int i = 0; i < contacts.size(); i++) {
            AbstractRecord contact = contacts.get(i);


            String contactData = "";


            List<String> fields = contact.getFields();


            for (int j = 0; j < fields.size(); j++) {
                String fieldName = fields.get(j);

                String fieldValue = contact.getField(fieldName);

                contactData += fieldValue + " ";
            }

            contactData = contactData.trim();


            if (contactData.toLowerCase().contains(query.toLowerCase())) {
                results.add(contact);
            }
        }

        return results;
    }



    public void listSearchResults(List<AbstractRecord> results){
        if(results.isEmpty()){
            System.out.println("No results found");
            return;
        }

        System.out.println("Found " + results.size() + " result(s):");
        for (AbstractRecord resu : results) {
            System.out.println(resu);
            System.out.println(resu.printName());
        }
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i).printName());
        }
    }

    public void searchAction(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter search query: ");
        String query = scanner.nextLine();

        List<AbstractRecord> searchResults = searchContacts(query);
        listSearchResults(searchResults);


        System.out.println("[search] Enter action ([number], back, again): ");
        String action = scanner.nextLine();
        switch (action) {
            case "back" -> {
                return;
            }
            case "again" -> {
                searchAction();
            }
            default -> {
                try {
                    int index = Integer.parseInt(action) - 1;
                    if (index >= 0 && index < searchResults.size()) {
                        searchResults.get(index).printInfo();
                        recordsActions(index);
                    } else {
                        System.out.println("Invalid action");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid action");
                }
            }
        }



    }

}//end class
