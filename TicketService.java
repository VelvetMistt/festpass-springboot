package com.example.festpass.service;

import com.example.festpass.models.FestEvent;
import com.example.festpass.models.Ticket;
import com.example.festpass.repository.FestEventRepository;
import com.example.festpass.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final FestEventRepository festEventRepository;

    public TicketService(TicketRepository ticketRepository,
                         FestEventRepository festEventRepository) {
        this.ticketRepository = ticketRepository;
        this.festEventRepository = festEventRepository;
    }

    public Ticket createTicket(Ticket ticket) {

        Optional<FestEvent> event =
                festEventRepository.findById(ticket.getEventId());

        if (event.isEmpty()) {
            throw new RuntimeException(
                    "Event with ID " + ticket.getEventId() + " not found"
            );
        }

        long ticketCount =
                ticketRepository.countByEventId(ticket.getEventId());

        if (ticketCount >= event.get().getCapacity()) {
            throw new RuntimeException(
                    "Event capacity is full"
            );
        }

        ticket.setQrCode(UUID.randomUUID().toString());

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    public Ticket updateTicket(Long id, Ticket updatedTicket) {

        Optional<Ticket> existingTicket =
                ticketRepository.findById(id);

        if (existingTicket.isEmpty()) {
            return null;
        }

        Ticket ticket = existingTicket.get();

        ticket.setAttendeeName(updatedTicket.getAttendeeName());
        ticket.setAttendeeEmail(updatedTicket.getAttendeeEmail());
        ticket.setEventId(updatedTicket.getEventId());
        ticket.setTicketNumber(updatedTicket.getTicketNumber());

        return ticketRepository.save(ticket);
    }
    public Ticket checkInByQrCode(String qrCode) {

        Optional<Ticket> ticketOptional =
                ticketRepository.findByQrCode(qrCode);

        if (ticketOptional.isEmpty()) {
            throw new RuntimeException("Invalid QR code");
        }

        Ticket ticket = ticketOptional.get();

        if (ticket.isCheckedIn()) {
            throw new RuntimeException("Ticket already checked in");
        }

        ticket.setCheckedIn(true);

        return ticketRepository.save(ticket);
    }
    public List<Ticket> getAttendance(Long eventId) {

        return ticketRepository.findByEventIdAndCheckedInTrue(eventId);
    }

    public boolean deleteTicket(Long id) {

        if (!ticketRepository.existsById(id)) {
            return false;
        }

        ticketRepository.deleteById(id);
        return true;
    }
}