package contacts.backUp;

import java.time.LocalDate;

public class ContactService {



    public Person createRecordPerson(String name, String surname, String phoneNumber,String gender, LocalDate birthDate){
        return new Person(name,surname,phoneNumber,gender,birthDate);
    }

    public Organization createRecordOrganization(String organizationName,String address,String phoneNumber){
        return new Organization(organizationName,address,phoneNumber);
    }

}
