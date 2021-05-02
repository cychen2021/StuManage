package xyz.cychen.stumanage.model;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.cychen.stumanage.model.Student;

import java.util.Collection;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    @Query("SELECT student FROM Student student WHERE student.studentId =:id")
    @Transactional(readOnly = true)
    Collection<Student> findById(@Param("id") String id);

    @Query("SELECT student FROM Student student WHERE student.realId =:real_id")
    @Transactional(readOnly = true)
    Student findByRealId(@Param("real_id") Integer realId);

    @Query("SELECT student FROM Student student WHERE student.name =:name")
    @Transactional(readOnly = true)
    Collection<Student> findByName(@Param("name") String name);

    @Query("SELECT student FROM Student student WHERE student.studentId=:student_id OR student.name =:name")
    @Transactional(readOnly = true)
    Collection<Student> findByIdOrName(@Param("student_id") String studentId, @Param("name") String name);

    @Query("SELECT student FROM Student student WHERE student.studentId=:student_id AND student.realId <>:real_id")
    @Transactional(readOnly = true)
    Collection<Student> findByIdNotRealID(@Param("student_id") String studentId, @Param("real_id") Integer realID);

    @Query("DELETE FROM Student student WHERE student.realId =:realID")
    @Transactional
    @Modifying
    void deleteByRealId(@Param("realID") Integer realID);

    @Query(value = "SELECT IDENTITY()", nativeQuery = true)
    @Transactional(readOnly = true)
    Integer getNewestRealId();
}
