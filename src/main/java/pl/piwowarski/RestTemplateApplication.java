package pl.piwowarski;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class RestTemplateApplication {

    public static void main(String[] args) throws JsonProcessingException {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(
                RestTemplateApplication.class
        );
        MainApplicationRunner runner = annotationConfigApplicationContext.getBean(MainApplicationRunner.class);
        runner.run();
    }
}
