package gp.riham_aisha.back_end;

import gp.riham_aisha.back_end.dto.RegistrationRequest;
import gp.riham_aisha.back_end.service.AdminService;
import gp.riham_aisha.back_end.service.StoreCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class BackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
    }

    @Bean
    CommandLineRunner initDB(AdminService adminService, StoreCategoryService storeCategoryService) {
        return args -> {
            log.info("---------- The application has started on port 1218 ----------");
            adminService.addNewAdmin(new RegistrationRequest("rihamkatout", "Riham", "Katout",
                    "rihamkatm@gmail.com", "pass123456", "0599119482"));

            storeCategoryService.addNewStoreCategory("General");
        };
    }
}
