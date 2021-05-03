package xyz.cychen.stumanage.batch;

import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.support.rowset.RowSet;
import xyz.cychen.stumanage.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(RowSet rowSet) throws Exception {
        String[] cells = rowSet.getCurrentRow();
        return new Student(cells[1], cells[3], cells[2], cells[0]);
    }
}
