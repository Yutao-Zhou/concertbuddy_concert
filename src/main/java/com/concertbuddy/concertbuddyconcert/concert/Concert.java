package com.concertbuddy.concertbuddyconcert.concert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

@Entity
@Table(name="`Concert`")
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String usersInfo;
    @Column(nullable = false)
    private String venue;
    @Column(nullable = false)
    private String performingArtist;
    @Column(nullable = false)
    private LocalDate dateTime;
    @Column(nullable = false)
    private String genre;
    @Column(nullable = false)
    private String subGenre;
    @Column(nullable = false)
    private Double cost;


    public Concert() {
    }


    public Concert(UUID id, String name, String usersInfo, String venue, String performingArtist, LocalDate dateTime, String genre, String subGenre, Double cost) {
        this.id = id;
        this.name = name;
        this.usersInfo = usersInfo;
        this.venue = venue;
        this.performingArtist = performingArtist;
        this.dateTime = dateTime;
        this.genre = genre;
        this.subGenre = subGenre;
        this.cost = cost;
    }

    public Concert(String name, String usersInfo, String venue, String performingArtist, LocalDate dateTime, String genre, String subGenre, Double cost) {
        this.name = name;
        this.usersInfo = usersInfo;
        this.venue = venue;
        this.performingArtist = performingArtist;
        this.dateTime = dateTime;
        this.genre = genre;
        this.subGenre = subGenre;
        this.cost = cost;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsersInfo() {
        return usersInfo;
    }

    public void setUsersInfo(String usersInfo) {
        this.usersInfo = usersInfo;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getPerformingArtist() {
        return performingArtist;
    }

    public void setPerformingArtist(String performingArtist) {
        this.performingArtist = performingArtist;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSubGenre() {
        return subGenre;
    }

    public void setSubGenre(String subGenre) {
        this.subGenre = subGenre;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Concert{" +
                "id=" + id +
                ", name='" + usersInfo + '\'' +
                ", artist='" + performingArtist + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
