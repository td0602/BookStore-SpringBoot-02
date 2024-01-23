package com.example.ebookapp.repository;

import com.example.ebookapp.model.BookDetails;
import com.example.ebookapp.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<BookDetails, Long> {
    // Custom lai de list sap xep theo ID tang dan
    @Query("SELECT b FROM BookDetails AS b ORDER BY b.id ASC")
    List<BookDetails> findAll();
    @Query("SELECT b FROM BookDetails AS b ORDER BY b.id ASC")
    Page<BookDetails> findAll(Pageable pageable);
    Page<BookDetails> findAllByOrderByPriceDesc(Pageable pageable);
    Page<BookDetails> findAllByOrderByPriceAsc(Pageable pageable);

    //Thay the tat ca cac ham filter books ben tren + pagination
    @Query("SELECT b FROM BookDetails b " +
            "WHERE (:category IS NULL OR b.category = :category) " +
            "AND (:keyword IS NULL OR b.bookName LIKE %:keyword%) " +
            "ORDER BY " +
            "CASE WHEN :orderBy = 'increasing' AND :orderBy != '' THEN b.price END ASC, " +
            "CASE WHEN :orderBy = 'decreasing' AND :orderBy != '' THEN b.price END DESC")
    //    Param mặc định có cả TH null
    Page<BookDetails> findBooksByFilter(@Param("category") Category category,
                                        @Param("orderBy") String orderBy,
                                        @Param("keyword") String keyword,
                                        Pageable pageable);

    @Query("SELECT b FROM BookDetails b WHERE b.bookName LIKE %?1% ORDER BY b.id")// ?1 la co 1 tham so truyen vao
    List<BookDetails> searchBook(String keyword);

    List<BookDetails> findBookDetailsByCategoryId(Long id);
}
