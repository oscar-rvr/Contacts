package contacts.backUp;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

abstract class AbstractRecord  implements Serializable {
    private String phoneNumber;
    private LocalDate createdDate;
    private LocalDate lastEditedDate;
    //private boolean isPerson;

    public AbstractRecord(String phoneNumber){
        this.phoneNumber = phoneNumber;
        this.createdDate = LocalDate.now();
        this.lastEditedDate=LocalDate.now();

    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public LocalDate getCreatedDate(){
        return createdDate;
    }

    public LocalDate getLastEditedDate() {
        return lastEditedDate;
    }

    public void setLastEditedDate(LocalDate lastEditedDate) {
        this.lastEditedDate = lastEditedDate;
    }


    public abstract void printDetails();

    public void setPhoneNumber(String phoneNumber){
        if(isValidNumber(phoneNumber)){
            this.phoneNumber=phoneNumber;

        }else{
            System.out.println("Wrong number format!");
            this.phoneNumber="[no number]";
        }
    }
    private boolean isValidNumber(String phoneNumber) {
         long countParentheses = phoneNumber.chars().filter(ch -> ch == '(' || ch == ')').count();
        if (countParentheses > 2) {
            return false;
        }
         if (phoneNumber.matches(".*\\(.*\\s.*\\).*")) {
            return false;
        }
         return phoneNumber.matches(
                "\\+?(\\(?[A-Za-z0-9]{1,}\\)?)([ -]?[A-Za-z0-9]{2,}|[ -]?\\(?[A-Za-z0-9]{2,}\\)?)*"
        );
    }


    
}
