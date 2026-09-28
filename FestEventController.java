package com.example.festpass.controller;

import com.example.festpass.models.FestEvent;
import com.example.festpass.service.FestEventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class FestEventController {

    private final FestEventService festEventService;

    public FestEventController(FestEventService festEventService) {
        this.festEventService = festEventService;
    }

    @PostMapping
    public ResponseEntity<FestEvent> createEvent(
            @Valid @RequestBody FestEvent event) {

        FestEvent savedEvent = festEventService.createEvent(event);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }

    @GetMapping
    public ResponseEntity<List<FestEvent>> getAllEvents() {

        List<FestEvent> events = festEventService.getAllEvents();

        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id) {

        Optional<FestEvent> event = festEventService.getEventById(id);

        if (event.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Event with ID " + id + " not found");
        }

        return ResponseEntity.ok(event.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody FestEvent updatedEvent) {

        FestEvent event = festEventService.updateEvent(id, updatedEvent);

        if (event == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Event with ID " + id + " not found");
        }

        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {

        boolean deleted = festEventService.deleteEvent(id);

        if (!deleted) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Event with ID " + id + " not found");
        }

        return ResponseEntity.noContent().build();
    }
}