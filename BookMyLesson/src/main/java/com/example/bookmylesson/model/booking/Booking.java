package com.example.bookmylesson.model.booking;

import com.example.bookmylesson.model.offering.Offering;
import com.example.bookmylesson.model.user.Client;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "offering_id", nullable = false)
    private Offering offering;

    private LocalDateTime bookingDate;

    // Constructors
    public Booking() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Offering getOffering() {
        return offering;
    }

    public void setOffering(Offering offering) {
        this.offering = offering;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    @Override
    public String toString() {
        return "Booking {" +
               "id=" + id +
               ", client=" + (client != null ? client.getName() : "null") +
               ", offering=" + (offering != null ? offering.toString() : "null") +
               ", bookingDate=" + bookingDate +
               '}';
    }

}

