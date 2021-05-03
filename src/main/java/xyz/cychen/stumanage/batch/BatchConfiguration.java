package xyz.cychen.stumanage.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import xyz.cychen.stumanage.model.Student;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public PoiItemReader<Student> getReader() {
        PoiItemReader<Student> reader = new PoiItemReader();
        reader.setName("studentReader");
        reader.setResource(new FileSystemResource("data/test.xlsx"));
        reader.setLinesToSkip(1);
        reader.setRowMapper(new StudentRowMapper());
        return reader;
    }

    @Bean
    public ItemProcessor<Student, Student> getProcessor() {
        return new StudentProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Student> getWriter(DataSource dataSource) {
        JdbcBatchItemWriter<Student> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO students VALUES (:realId, :name, :phone, :department, :studentId)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Step getStep1(JdbcBatchItemWriter<Student> writer) {
        StepBuilder stepBuilder = stepBuilderFactory.get("step1");
        stepBuilder.allowStartIfComplete(true);
        return stepBuilder.<Student, Student>chunk(1).reader(getReader()).processor(getProcessor()).writer(writer).build();
    }

    @Bean
    public Job getStudentJob(Step step1) {
        JobBuilder jobBuilder = jobBuilderFactory.get("studentJob");
        return jobBuilder.incrementer(new RunIdIncrementer()).flow(step1).end().build();
    }
}
