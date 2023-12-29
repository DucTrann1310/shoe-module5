package com.cg.service.productService;

import com.cg.model.*;
import com.cg.model.dto.FilterReqDTO;
import com.cg.model.dto.ProductCreReqDTO;
import com.cg.model.dto.ProductResDTO;
import com.cg.repository.*;
import com.cg.service.UploadService.IUploadService;
import com.cg.utils.UploadUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductAvatarRepository productAvatarRepository;

    @Autowired
    private IUploadService uploadService;

    @Autowired
    private UploadUtils uploadUtils;

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void create(ProductCreReqDTO productCreReqDTO) {
        ProductAvatar productAvatar = new ProductAvatar();
        productAvatarRepository.save(productAvatar);

        Category category = categoryRepository.findCategoryByName(productCreReqDTO.getCategory());
        Color color = colorRepository.findColorByName(productCreReqDTO.getColor());
        Company company = companyRepository.findCompanyByName(productCreReqDTO.getCompany());
        Product product = productCreReqDTO.toProduct(company,color,category,productAvatar);
        productRepository.save(product);

        uploadAndSaveProductImage(productAvatar, productCreReqDTO.getFile());
    }

    private void uploadAndSaveProductImage(ProductAvatar productAvatar, MultipartFile file){
        try{
            Map uploadResult = uploadService.uploadImage(file, uploadUtils.buildImageUploadParams(productAvatar));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            productAvatar.setFileName(productAvatar.getId() + "." + fileFormat);
            productAvatar.setFileUrl(fileUrl);
            productAvatar.setFileFolder(UploadUtils.IMAGE_UPLOAD_FOLDER);
            productAvatar.setCloudId(productAvatar.getFileFolder() + "/" + productAvatar.getId());
            productAvatarRepository.save(productAvatar);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<ProductResDTO> findAllProductResDTO() {
        return productRepository.findAllProductResDTO();
    }



    @Override
    public Page<ProductResDTO> filter(FilterReqDTO filterReqDTO, Pageable pageable) {

        Page<ProductResDTO> productResDTOList = productRepository.filter(filterReqDTO, pageable);

        return productResDTOList;
    }
}
