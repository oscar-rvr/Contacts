package contacts;

public class Contact {
    private String name;
    private String surname;
    private String phoneNumber;


    public Contact(String name, String surname, String phoneNumber){
        this.name=name;
        this.surname=surname;
        setPhoneNumber(phoneNumber);
    }

    public String getName() {
        return name;
    }
    public String getSurname(){
        return surname;
    }

    public String getPhoneNumber(){
        return phoneNumber.isEmpty() ? "[no number]" : phoneNumber;
    }

    public void setName(String name){
        this.name=name;

    }

    public void setSurname(String surname){
        this.surname=surname;
    }

    public void setPhoneNumber(String phoneNumber){
        if(isValidNumber(phoneNumber)){
            this.phoneNumber=phoneNumber;

        }else{
            System.out.println("Wrong number format!");
            this.phoneNumber="[no number]";
        }
    }
    private boolean isValidNumber(String phoneNumber) {
        // Verificar si hay más de un grupo entre paréntesis
        long countParentheses = phoneNumber.chars().filter(ch -> ch == '(' || ch == ')').count();
        if (countParentheses > 2) {
            return false;
        }
        // Verificar que no haya espacios dentro de paréntesis
        if (phoneNumber.matches(".*\\(.*\\s.*\\).*")) {
            return false;
        }
        // Verificar el formato general del número
        return phoneNumber.matches(
                "\\+?(\\(?[A-Za-z0-9]{1,}\\)?)([ -]?[A-Za-z0-9]{2,}|[ -]?\\(?[A-Za-z0-9]{2,}\\)?)*"
        );
    }




}
