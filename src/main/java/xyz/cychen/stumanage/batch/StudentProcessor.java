package xyz.cychen.stumanage.batch;

import org.springframework.batch.item.ItemProcessor;
import xyz.cychen.stumanage.controller.MainController;
import xyz.cychen.stumanage.model.Student;

public class StudentProcessor implements ItemProcessor<Student, Student> {
    @Override
    public Student process(Student item) throws Exception {
        item.setRealId(MainController.getNewestRealId());
        return item;
    }
}
