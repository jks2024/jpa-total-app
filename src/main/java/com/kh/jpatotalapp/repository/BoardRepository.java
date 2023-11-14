package com.kh.jpatotalapp.repository;
import com.kh.jpatotalapp.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
