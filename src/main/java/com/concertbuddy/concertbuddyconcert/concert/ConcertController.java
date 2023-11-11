package com.concertbuddy.concertbuddyconcert.concert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/concerts")
public class ConcertController {

    private final ConcertService concertService;

    @Autowired
    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }

    @GetMapping(path="test")
    public String getConcertById() { return "Hello World. Ping success."; }

    @GetMapping
    public List<Concert> getConcerts() {
        return concertService.getConcerts();
    }

    @GetMapping(path="{concertId}")
    public Concert getConcertById(@PathVariable("concertId") UUID concertId) {
        return concertService.getConcertById(concertId);
    }

    @PostMapping
    public void registerNewConcert(@RequestBody Concert concert) {
        concertService.addNewConcert(concert);
    }

    @PutMapping
    public void updateConcert(@RequestBody Concert concert) {
        concertService.addNewConcert(concert);
    }

    @DeleteMapping(path="{concertId}")
    public void deleteConcert(@PathVariable("concertId") UUID concertId) {
        concertService.deleteConcert(concertId);
    }

    @PutMapping(path="TicketmasterSync")
    public void TicketmasterSync() {
        concertService.TicketmasterSync();
    }
}
