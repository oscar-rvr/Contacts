package contacts.backUp;

import java.time.LocalDate;
import java.util.List;

public class Organization extends AbstractRecord {
    private String organizationName;
    private String address;



    public Organization(String organizationName,String address,String phoneNumber){
        super(phoneNumber);

        this.organizationName=organizationName;
        this.address=address;


    }

    @Override
    public void printDetails() {
        System.out.println(organizationName + ", " + address + ", " + getPhoneNumber());
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getAddress() {
        return address;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setAddress(String address) {
        this.address = address;
    }




}

