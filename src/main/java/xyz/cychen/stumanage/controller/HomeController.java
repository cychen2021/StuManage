package xyz.cychen.stumanage.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.cychen.stumanage.model.Student;
import xyz.cychen.stumanage.model.StudentRepository;

import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;

@Controller
@ComponentScan(basePackageClasses = {xyz.cychen.stumanage.model.StudentRepository.class})
public class HomeController {
    @GetMapping("/")
    public String home(ModelMap model) {
        model.put("students", students.findAll());
        model.put("idle", true);
        return "home";
    }

    private final StudentRepository students;

    public HomeController(StudentRepository students) {
        this.students = students;
    }

    @GetMapping("/add")
    public String getAddPage() {
        return "add";
    }

    @GetMapping("/search")
    public String getSearchPage() {
        return "search";
    }

    @RequestMapping("/search_student")
    public String searchStudent(String name, String id, ModelMap model) {
        if (name.isBlank() && id.isBlank()) {
            return "oups_search";
        }
        else if (name.isBlank() && !id.isBlank()) {
            model.put("students", students.findById(id.strip()));
            return "search_result";
        }
        else if (id.isBlank() && !name.isBlank()) {
            model.put("students", students.findByName(name.strip()));
            return "search_result";
        }
        else {
            model.put("students", students.findByIdOrName(id.strip(), name.strip()));
            return "search_result";
        }
    }

    @RequestMapping("/del_student")
    public String delStudent(Integer realID) {
        students.deleteByRealId(realID);
        return "redirect:/";
    }

    @RequestMapping("/edit_student")
    public String editStudent(Integer realID, ModelMap model) {
        model.put("student", students.findByRealId(realID));
        return "edit";
    }

    @RequestMapping("/mod_student")
    public String modStudent(String name, String gender, String birth_date, String native_place,
                             String id, String department, Integer real_id, ModelMap model) throws ParseException {
        boolean name_missing = name.isBlank();
        boolean gender_missing = gender == null || gender.isBlank();
        boolean birth_date_missing = birth_date.isBlank();
        boolean native_place_missing = native_place.isBlank();
        boolean id_missing = id.isBlank();
        boolean department_missing = department.isBlank();
        model.put("name_missing", name_missing);
        model.put("gender_missing", gender_missing);
        model.put("birth_date_missing", birth_date_missing);
        model.put("native_place_missing", native_place_missing);
        model.put("id_missing", id_missing);
        model.put("department_missing", department_missing);
        if (name_missing || gender_missing || birth_date_missing || native_place_missing || id_missing
                || department_missing) {
            return "oups_edit";
        }

        Collection<Student> alreadyin = students.findByIdNotRealID(id.strip(), real_id);
        if (!alreadyin.isEmpty()) {
            model.put("id", id.strip());
            return "oups_edit1";
        }

        Student student = students.findByRealId(real_id);
        student.setID(id);
        student.setDepartment(department);
        student.setNativePlace(native_place);
        student.setName(name);
        student.setBirthDate(birth_date);
        student.setGender(gender);

        students.save(student);
        model.put("students", students.findAll());
        return "redirect:/";
    }

    @RequestMapping("/add_student")
    public String addStudent(String name, String gender, String birth_date, String native_place,
                             String id, String department, ModelMap model) throws ParseException {
        boolean name_missing = name.isBlank();
        boolean gender_missing = gender == null || gender.isBlank();
        boolean birth_date_missing = birth_date.isBlank();
        boolean native_place_missing = native_place.isBlank();
        boolean id_missing = id.isBlank();
        boolean department_missing = department.isBlank();
        model.put("name_missing", name_missing);
        model.put("gender_missing", gender_missing);
        model.put("birth_date_missing", birth_date_missing);
        model.put("native_place_missing", native_place_missing);
        model.put("id_missing", id_missing);
        model.put("department_missing", department_missing);
        if (name_missing || gender_missing || birth_date_missing || native_place_missing || id_missing
                || department_missing) {
            return "oups_add";
        }

        Collection<Student> alreadyin = students.findById(id.strip());
        if (!alreadyin.isEmpty()) {
            model.put("id", id.strip());
            return "oups_add1";
        }

        Student student = new Student(name.strip(), gender, birth_date, native_place.strip(), department.strip(), id.strip());
        students.save(student);
        model.put("students", students.findAll());
        return "redirect:/";
    }
}
