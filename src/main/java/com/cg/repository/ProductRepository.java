package com.cg.repository;

import com.cg.model.Product;
import com.cg.model.dto.FilterReqDTO;
import com.cg.model.dto.ProductResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT new com.cg.model.dto.ProductResDTO" +
            "(p.id,p.title,p.star,p.reviews,p.prevPrice,p.newPrice,p.company,p.color,p.category, p.avatar) " +
            "FROM Product as p " +
            "WHERE  p.deleted = false"
    )
    List<ProductResDTO> findAllProductResDTO();

/*    @Query(value =
            "SELECT " +
                    "p.id, " +
                    "p.img, " +
                    "p.title, " +
                    "p.star, " +
                    "p.reviews, " +
                    "p.prev_price as prevPrice, " +
                    "p.new_price as newPrice, " +
                    "com.name as company, " +
                    "col.name as color, " +
                    "cat.name as category " +
            "FROM products p " +
            "JOIN categories cat " +
            "ON p.category_id = cat.id " +
            "JOIN colors col " +
            "ON p.color_id = col.id " +
            "JOIN companies com " +
            "ON p.company_id = com.id " +
            "WHERE (" +
                "(:#{#filterReqDTO.input} LIKE '' OR p.title LIKE CONCAT('%', :#{#filterReqDTO.input},'%')) " +
                "AND ((:#{#filterReqDTO.minPrice} = :#{#filterReqDTO.maxPrice} and p.new_price >= :#{#filterReqDTO.minPrice} ) OR (p.new_price >= :#{#filterReqDTO.minPrice} and p.new_price <= :#{#filterReqDTO.maxPrice})" +
                "AND (:#{#filterReqDTO.company} = 'all products' OR lower(com.name) = lower(:#{#filterReqDTO.company})) " +
                "AND (:#{#filterReqDTO.color} = 'all' OR lower(col.name) = lower(:#{#filterReqDTO.color})) " +
                "AND (:#{#filterReqDTO.category} = 'all' OR lower(cat.name) = lower(:#{#filterReqDTO.category}))))"
            ,
            countQuery = "SELECT count(*) FROM ( " +
                    "SELECT " +
                        "p.id, " +
                        "p.img, " +
                        "p.title, " +
                        "p.star, " +
                        "p.reviews, " +
                        "p.prev_price as prevPrice, " +
                        "p.new_price as newPrice, " +
                        "com.name as company, " +
                        "col.name as color, " +
                        "cat.name as category " +
                    "FROM products p " +
                    "JOIN categories cat " +
                    "ON p.category_id = cat.id " +
                    "JOIN colors col " +
                    "ON p.color_id = col.id " +
                    "JOIN companies com " +
                    "ON p.company_id = com.id " +
                    "WHERE (" +
                        "(:#{#filterReqDTO.input} LIKE '' OR p.title LIKE CONCAT('%', :#{#filterReqDTO.input},'%')) " +
                        "AND ((:#{#filterReqDTO.minPrice} = :#{#filterReqDTO.maxPrice} and p.new_price >= :#{#filterReqDTO.minPrice} ) OR (p.new_price >= :#{#filterReqDTO.minPrice} and p.new_price <= :#{#filterReqDTO.maxPrice})" +
                        "AND (:#{#filterReqDTO.company} = 'all products' OR lower(com.name) = lower(:#{#filterReqDTO.company})) " +
                        "AND (:#{#filterReqDTO.color} = 'all' OR lower(col.name) = lower(:#{#filterReqDTO.color})) " +
                        "AND (:#{#filterReqDTO.category} = 'all' OR lower(cat.name) = lower(:#{#filterReqDTO.category}))))) t"
            , nativeQuery = true
    )
    Page<ProductResDTO> filter(@Param("filterReqDTO")FilterReqDTO filterReqDTO,
                               Pageable pageable);

*/
    @Query("SELECT new com.cg.model.dto.ProductResDTO(p.id,p.title,p.star,p.reviews,p.prevPrice,p.newPrice,p.company,p.color,p.category,p.avatar) " +
            "FROM Product as p " +
            "WHERE (p.title LIKE CONCAT('%', :#{#filterReqDTO.input},'%')) " +
                "AND ((:#{#filterReqDTO.minPrice} = :#{#filterReqDTO.maxPrice} and p.newPrice >= :#{#filterReqDTO.minPrice} ) OR (p.newPrice >= :#{#filterReqDTO.minPrice} and p.newPrice <= :#{#filterReqDTO.maxPrice})) " +
                "AND (:#{#filterReqDTO.company} = 'all products' OR lower(p.company.name) = lower(:#{#filterReqDTO.company})) " +
                "AND (:#{#filterReqDTO.color} = 'all' OR lower(p.color.name) = lower(:#{#filterReqDTO.color})) " +
                "AND (:#{#filterReqDTO.category} = 'all' OR lower(p.category.name) = lower(:#{#filterReqDTO.category}))" +
                "AND p.deleted = false "
    )
    Page<ProductResDTO> filter(@Param("filterReqDTO") FilterReqDTO filterReqDTO, Pageable pageable);
}
