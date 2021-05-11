package com.example.board.repository;

import com.example.board.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Authority, Long> {
}
