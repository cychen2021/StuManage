package xyz.cychen.stumanage.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.cychen.stumanage.model.Student;
import xyz.cychen.stumanage.model.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@ComponentScan(basePackageClasses = {xyz.cychen.stumanage.model.StudentRepository.class})
public class MainController {
    private final StudentRepository studentRepository;

    public MainController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/student")
    ResponseEntity<EntityModel<Student>> findOne(
            @RequestParam(name = "realId") Integer realId) {
        Student queryResult = studentRepository.findByRealId(realId);
        if (realId == null || queryResult == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(EntityModel.of(queryResult));
    }

    @GetMapping("/students")
    ResponseEntity<CollectionModel<EntityModel<Student>>> find(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "id", required = false) String id) {
        Iterable<Student> queryResult;
        if ((name == null || name.isBlank()) && (id == null || id.isBlank())) {
            queryResult = studentRepository.findAll();
        }
        else if ((name == null || name.isBlank()) && (id != null && !id.isBlank())) {
            queryResult = studentRepository.findById(id.strip());
        }
        else if ((id == null || id.isBlank()) && (name != null && !name.isBlank())) {
            queryResult = studentRepository.findByName(name.strip());
        }
        else {
            queryResult = studentRepository.findByIdOrName(id.strip(), name.strip());
        }
        List<EntityModel<Student>> students = StreamSupport.stream(queryResult.spliterator(), true)
                .map(EntityModel::of)
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(students));
    }

    static Integer newestRealId = 1001;

    public static Integer getNewestRealId() {
        Integer record = newestRealId;
        newestRealId++;
        return record;
    }

    @PostMapping("/student")
    ResponseEntity<?> add(@RequestBody Student student) {
        Collection<Student> idConflicts = studentRepository.findById(student.getStudentId());
        if (!idConflicts.isEmpty()) {
            return ResponseEntity.status(409).build();
        }
        Integer realId = getNewestRealId();
        student.setRealId(realId);
        studentRepository.save(student);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/student")
    ResponseEntity<?> update(@RequestBody Student student) {
        Collection<Student> idConflicts = studentRepository.findByIdNotRealID(student.getStudentId(), student.getRealId());
        if (!idConflicts.isEmpty()) {
            return ResponseEntity.status(409).build();
        }

        Student alreadyIn = studentRepository.findByRealId(student.getRealId());
        if (alreadyIn == null) {
            return ResponseEntity.status(404).build();
        }
        studentRepository.deleteByRealId(student.getRealId());
        student.setRealId(getNewestRealId());
        studentRepository.save(student);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/student")
    ResponseEntity<?> delete(@RequestParam(name = "real_id") Integer realId) {
        Student alreadyIn = studentRepository.findByRealId(realId);
        if (alreadyIn == null) {
            return ResponseEntity.status(404).build();
        }
        studentRepository.deleteByRealId(realId);
        return ResponseEntity.ok().build();
    }
}
