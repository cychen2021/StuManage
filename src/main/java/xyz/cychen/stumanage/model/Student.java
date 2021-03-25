package xyz.cychen.stumanage.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "students")
public class Student implements Serializable {
    public Student() {

    }

    public enum Gender {
        Male,
        Female,
        Other
    }


    public Student(String name, String gender, String birthDate, String nativePlace, String department,
                   String ID) throws ParseException {
        this.name = name;
        if (gender.equals("male")) {
            this.gender = Gender.Male;
        }
        else if (gender.equals("female")) {
            this.gender = Gender.Female;
        }
        else if (gender.equals("other")) {
            this.gender = Gender.Other;
        }
        else {
            throw new RuntimeException("Illegal gender string `"+gender+"`");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        this.birthDate = formatter.parse(birthDate);
        this.nativePlace = nativePlace;
        this.department = department;
        this.ID = ID;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        if (gender.equals("male")) {
            this.gender = Gender.Male;
        }
        else if (gender.equals("female")) {
            this.gender = Gender.Female;
        }
        else if (gender.equals("other")) {
            this.gender = Gender.Other;
        }
        else {
            throw new RuntimeException("Illegal gender string `"+gender+"`");
        }
    }

    public void setBirthDate(String birthDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        this.birthDate = formatter.parse(birthDate);
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer realId;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "gender")
//    @NotBlank
    private Gender gender;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "native_place")
    @NotBlank
    private String nativePlace;

    @Column(name = "department")
    @NotBlank
    private String department;

    @Column(name = "id")
    @NotBlank
    private String ID;

    public Integer getRealId() {return realId;}

    public String getName() {
        return name;
    }

    public boolean isMaleGender() { return gender==Gender.Male;}
    public boolean isFemaleGender() { return gender==Gender.Female;}
    public boolean isOtherGender() { return gender==Gender.Other;}
    public String getGender() {
        if (gender==Gender.Male) {
            return "male";
        }
        else if (gender==Gender.Female) {
            return "female";
        }
        else {
            return"other";
        }
    }

    public String getBirthDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        return formatter.format(birthDate);
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public String getDepartment() {
        return department;
    }

    public String getID() {
        return ID;
    }
}
