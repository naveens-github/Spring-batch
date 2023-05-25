package com.myproject.spring.batch.config;

import com.myproject.spring.batch.entity.Organization;
import com.myproject.spring.batch.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.item.Chunk;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {

    private StepBuilderFactory stepBuilderFactory;
    private JobBuilderFactory jobBuilderFactory;
    private OrganizationRepository organizationRepository;

    @Bean
    public FlatFileItemReader<Organization> reader(){
        FlatFileItemReader<Organization> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/organizations-100.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());

        return itemReader;
    }

    private LineMapper<Organization> lineMapper() {
        DefaultLineMapper<Organization> defaultLineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("Index", "Organization Id", "Name", "Website", "Country", "Description", "Founded", "Industry", "Number of employees");

        BeanWrapperFieldSetMapper<Organization> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(Organization.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        return defaultLineMapper;

    }

    @Bean
    public OrganizationProcessor processor(){
        return new OrganizationProcessor();
    }

    @Bean
    public RepositoryItemWriter<Organization> writer(){
        RepositoryItemWriter<Organization> itemWriter = new RepositoryItemWriter<>();
        itemWriter.setRepository(organizationRepository);
        itemWriter.setMethodName("save");

        return itemWriter;
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("csv-step").<Organization, Organization>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job runJob(){
        return jobBuilderFactory.get("importOrganizationInfo")
                .flow(step1()).end().build();
    }


}
