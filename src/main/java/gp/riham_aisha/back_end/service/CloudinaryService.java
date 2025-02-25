package gp.riham_aisha.back_end.service;

import com.cloudinary.Cloudinary;
import gp.riham_aisha.back_end.dto.CloudinaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;

    @Transactional
    public CloudinaryResponse uploadFile(MultipartFile file, String fileName) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        try {
            final Map result = cloudinary.uploader().upload(file.getBytes(),
                    Map.of("public_id", "craftopia/products/" + fileName));
            final String url = (String) result.get("secure_url");
            final String publicId = (String) result.get("public_id");
            return CloudinaryResponse.builder().publicId(publicId).url(url).build();
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to upload file");
        }
    }
}
