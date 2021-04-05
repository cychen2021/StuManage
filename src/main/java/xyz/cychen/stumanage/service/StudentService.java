package xyz.cychen.stumanage.service;

//import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import xyz.cychen.stumanage.model.Student;
import xyz.cychen.stumanage.model.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {
//    private CacheManager cacheManager;
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
//        this.cacheManager = cacheManager;
        this.repository = repository;
    }

    @Cacheable(value = "students")
    public Iterable<Student> findAll() {
        return repository.findAll();
    }

    @Cacheable(value = "students")
    public Collection<Student> testQuery() {
        return repository.testQuery();
    }

    @Cacheable(value = "students", key = "#id")
    public Collection<Student> findById(String id) {
        return repository.findById(id);
    }

    @Cacheable(value = "students", key = "#realId")
    public Student findByRealId(Integer realId) {
        return repository.findByRealId(realId);
    }

    @Cacheable(value = "students", key = "#name")
    public Collection<Student> findByName(String name) {
        return repository.findByName(name);
    }

    @Cacheable(value = "students")
    public Collection<Student> findByIdOrName(String id, String name) {
        return repository.findByIdOrName(id, name);
    }

    @Cacheable(value = "students")
    public Collection<Student> findByIdNotRealID(String id, Integer realId) {
        return repository.findByIdNotRealID(id, realId);
    }

    @CacheEvict(value = "students", allEntries = true)
    public void save(Student student) {
        repository.save(student);
    }

    @CacheEvict(value = "students", allEntries = true)
    public void deleteByRealId(Integer realId) {
        repository.deleteByRealId(realId);
    }
}
