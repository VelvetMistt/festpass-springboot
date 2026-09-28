package com.example.festpass.controller;

import com.example.festpass.models.Ticket;
import com.example.festpass.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(
            @Valid @RequestBody Ticket ticket) {

        Ticket savedTicket = ticketService.createTicket(ticket);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedTicket);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {

        return ResponseEntity.ok(
                ticketService.getAllTickets()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTicketById(
            @PathVariable Long id) {

        Optional<Ticket> ticket =
                ticketService.getTicketById(id);

        if (ticket.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Ticket with ID " + id + " not found");
        }

        return ResponseEntity.ok(ticket.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTicket(
            @PathVariable Long id,
            @Valid @RequestBody Ticket updatedTicket) {

        Ticket ticket =
                ticketService.updateTicket(id, updatedTicket);

        if (ticket == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Ticket with ID " + id + " not found");
        }

        return ResponseEntity.ok(ticket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTicket(
            @PathVariable Long id) {

        boolean deleted =
                ticketService.deleteTicket(id);

        if (!deleted) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Ticket with ID " + id + " not found");
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/checkin")
    public ResponseEntity<?> checkInTicket(
            @RequestParam String qrCode) {

        Ticket ticket =
                ticketService.checkInByQrCode(qrCode);

        return ResponseEntity.ok(ticket);
    }
    @GetMapping("/attendance/{eventId}")
    public ResponseEntity<List<Ticket>> getAttendance(
            @PathVariable Long eventId) {

        return ResponseEntity.ok(
                ticketService.getAttendance(eventId)
        );
    }
}