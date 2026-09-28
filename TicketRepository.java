package com.example.festpass.repository;

import com.example.festpass.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    long countByEventId(Long eventId);

    Optional<Ticket> findByQrCode(String qrCode);

    List<Ticket> findByEventIdAndCheckedInTrue(Long eventId);
}