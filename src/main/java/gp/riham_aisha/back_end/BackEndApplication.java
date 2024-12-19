package gp.riham_aisha.back_end;

import gp.riham_aisha.back_end.dto.RegistrationRequest;
import gp.riham_aisha.back_end.service.AdminService;
import gp.riham_aisha.back_end.service.CategoryService;
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
    CommandLineRunner initDB(AdminService adminService, CategoryService storeCategoryService) {
        return args -> {
            log.info("---------- The application has started on port 1218 ----------");
            adminService.addNewAdmin(new RegistrationRequest("rihamkatout", "Riham", "Katout",
                    "rihamkatm@gmail.com", "pass123456", "0599119482"));

            storeCategoryService.addNewStoreCategory("General", null);
            storeCategoryService.addNewStoreCategory("Sweets", "https://drive.google.com/thumbnail?id=1eUBDdHDLWdXbU7mTpF0Y7VvBcsnEEDL8");
            storeCategoryService.addNewStoreCategory("Jewelry", "https://drive.google.com/thumbnail?id=1NHs5LnU9AINY08IPaVAPBO2Exb8cHqrc");
            storeCategoryService.addNewStoreCategory("Toys", "https://drive.google.com/thumbnail?id=1e6WlsCPcCctzMkpPqU6lZxL5ZZIirTzx");
            storeCategoryService.addNewStoreCategory("Home Decor", "https://drive.google.com/thumbnail?id=1KA8EF6JMU9ft42r6akR6DTZTgWPjUDnS");
        };
    }
}
