package gp.riham_aisha.back_end;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class BackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
    }

}
