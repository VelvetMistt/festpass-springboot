package com.example.festpass.repository;

import com.example.festpass.models.FestEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FestEventRepository extends JpaRepository<FestEvent, Long> {
}