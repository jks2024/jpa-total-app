package com.kh.jpatotalapp.repository;
import com.kh.jpatotalapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
