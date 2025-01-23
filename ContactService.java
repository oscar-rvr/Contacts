package contacts;

public class ContactService {



    public Contact createContact(String name, String surname, String phoneNumber){
        return new Contact(name,surname,phoneNumber);
    }

}
