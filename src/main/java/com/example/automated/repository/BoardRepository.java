package com.example.automated.repository;

import com.example.automated.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByCreatedById(Long userId);
}