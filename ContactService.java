package contacts;

import java.time.LocalDate;

public class ContactService {



    public Person createRecordPerson(String name, String surname, String phoneNumber,String gender, LocalDate birthDate, boolean isPerson){
        return new Person(name,surname,phoneNumber,gender,birthDate, isPerson);
    }

    public Organization createRecordOrganization(String organizationName,String address,String phoneNumber, boolean isPerson){
        return new Organization(organizationName,address,phoneNumber, isPerson);
    }

}
