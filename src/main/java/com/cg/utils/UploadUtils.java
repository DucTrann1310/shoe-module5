package com.cg.utils;

import com.cg.model.Product;
import com.cg.model.ProductAvatar;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UploadUtils {
    public static final String IMAGE_UPLOAD_FOLDER = "codegym/shoe_module4";

    public Map buildImageUploadParams(ProductAvatar productAvatar) {
        String publicId = String.format("%s/%s", IMAGE_UPLOAD_FOLDER, productAvatar.getId());
        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

    public Map buildImageDestroyParams(Product product, String publicId) {
        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }
}
