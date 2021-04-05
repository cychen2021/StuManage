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
    @Query("SELECT student FROM Student student WHERE student.ID =:id")
    @Transactional(readOnly = true)
    Collection<Student> findById(@Param("id") String id);

    @Query("SELECT student FROM Student student WHERE student.realId =:real_id")
    @Transactional(readOnly = true)
    Student findByRealId(@Param("real_id") Integer realId);

    @Query("SELECT student FROM Student student WHERE student.name =:name")
    @Transactional(readOnly = true)
    Collection<Student> findByName(@Param("name") String name);

    @Query("SELECT student FROM Student student WHERE student.ID=:id OR student.name =:name")
    @Transactional(readOnly = true)
    Collection<Student> findByIdOrName(@Param("id") String id, @Param("name") String name);

    @Query("SELECT student FROM Student student WHERE student.ID=:id AND student.realId <>:real_id")
    @Transactional(readOnly = true)
    Collection<Student> findByIdNotRealID(@Param("id") String id, @Param("real_id") Integer realID);

    @Query("DELETE FROM Student student WHERE student.realId =:realID")
    @Transactional
    @Modifying
    void deleteByRealId(@Param("realID") Integer realID);

    @Query("SELECT student FROM Student student WHERE student.nativePlace LIKE '湖北省' OR student.department LIKE " +
            "'计算机科学与技术系' ORDER BY student.birthDate ASC, student.ID ASC")
    @Transactional(readOnly = true)
    Collection<Student> testQuery();

//    @Query("SELECT student FROM Student student")
//    @Transactional(readOnly = true)
//    Collection<Student> findAll();
}
