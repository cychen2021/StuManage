package xyz.cychen.stumanage.model;

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

//    public enum Gender {
//        Male("male"),
//        Female("female"),
//        Other("other");
//
//        private String value;
//        Gender(String value) {
//            this.value = value;
//        }
//
//        @JsonValue
//        public String getValue() {
//            return this.value;
//        }
//    }


    public Student(String name, String gender, String birthDate, String nativePlace, String department,
                   String studentId) throws ParseException {
        this.name = name;
        if (gender.equals("male") || gender.equals("female") || gender.equals("other")) {
            this.gender = gender;
        }
        else {
            throw new RuntimeException("Illegal gender string `"+gender+"`");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        this.birthDate = formatter.parse(birthDate);
        this.nativePlace = nativePlace;
        this.department = department;
        this.studentId = studentId;
    }


    @Id
    @Column(name = "real_id", updatable = false)
    private Integer realId;

    public void setRealId(Integer realId) {
        this.realId = realId;
    }

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "native_place")
    @NotBlank
    private String nativePlace;

    @Column(name = "department")
    @NotBlank
    private String department;

    @Column(name = "student_id")
    @NotBlank
    private String studentId;

    public Integer getRealId() {return realId;}

    public String getName() {
        return name;
    }

    public String getGender() {
        if (gender.equals("male") || gender.equals("female") || gender.equals("other")) {
            return gender;
        }
        else {
            throw new RuntimeException("Illegal gender string `"+gender+"`");
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

    public String getStudentId() {
        return studentId;
    }
}
