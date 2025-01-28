package contacts;

import java.time.LocalDate;

public class Person extends Record {
    private String name;
    private String surname;
    private String gender;
    private LocalDate birthDate;

    public Person(String name, String surname, String phoneNumber, String gender, LocalDate birthDate,boolean isPerson){
        super(phoneNumber,isPerson);
        //super(isPerson);
        this.name=name;
        this.surname=surname;
        this.gender=gender;
        this.birthDate=birthDate;

    }

    @Override
    public void printDetails(){
        System.out.println(name + " " + surname + ", " + gender + ", " + birthDate + ", " + getPhoneNumber());

    }


    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }






    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }





}
