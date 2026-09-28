package com.example.festpass.service;

import com.example.festpass.models.FestEvent;
import com.example.festpass.repository.FestEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FestEventService {

    private final FestEventRepository festEventRepository;

    public FestEventService(FestEventRepository festEventRepository) {
        this.festEventRepository = festEventRepository;
    }

    public FestEvent createEvent(FestEvent event) {
        return festEventRepository.save(event);
    }

    public List<FestEvent> getAllEvents() {
        return festEventRepository.findAll();
    }

    public Optional<FestEvent> getEventById(Long id) {
        return festEventRepository.findById(id);
    }

    public FestEvent updateEvent(Long id, FestEvent updatedEvent) {

        Optional<FestEvent> existingEvent = festEventRepository.findById(id);

        if (existingEvent.isEmpty()) {
            return null;
        }

        FestEvent event = existingEvent.get();

        event.setName(updatedEvent.getName());
        event.setDate(updatedEvent.getDate());
        event.setVenue(updatedEvent.getVenue());
        event.setCapacity(updatedEvent.getCapacity());
        event.setTicketPrice(updatedEvent.getTicketPrice());

        return festEventRepository.save(event);
    }

    public boolean deleteEvent(Long id) {

        if (!festEventRepository.existsById(id)) {
            return false;
        }

        festEventRepository.deleteById(id);
        return true;
    }
}