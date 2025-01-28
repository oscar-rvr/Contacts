package contacts;

public class Organization extends Record {
    private String organizationName;
    private String address;



    public Organization(String organizationName,String address,String phoneNumber, boolean isPerson){
        super(phoneNumber,isPerson);

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

