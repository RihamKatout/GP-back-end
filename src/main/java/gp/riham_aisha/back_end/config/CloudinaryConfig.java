package gp.riham_aisha.back_end.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        final Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dsrhpmlsn");
        config.put("api_key", "284426459359815");
        config.put("api_secret", "Do2xMJQiDGD9G4jATy4QK8hKwn4");
        return new Cloudinary(config);
    }
}
