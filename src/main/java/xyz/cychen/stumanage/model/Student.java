package xyz.cychen.stumanage.model;

import java.io.Serializable;
import java.text.ParseException;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "students")
public class Student implements Serializable {
    public Student() {

    }
    public Student(String name, String phone, String department,
                   String studentId) throws ParseException {
        this.name = name;
        this.phone = phone;
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

    @Column(name = "phone")
    @NotBlank
    private String phone;

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

    public String getPhone() {
        return phone;
    }

    public String getDepartment() {
        return department;
    }

    public String getStudentId() {
        return studentId;
    }
}
