package com.concertbuddy.concertbuddyconcert.concert;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping()
    public List<Concert> getConcerts(@RequestParam("page") int page, @RequestParam("size") int size) {
        return concertService.getConcerts(page, size);
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

    @GetMapping(path="{concertId}/usersInfo")
    public List<Pair<UUID, Status>> getUsersInfo(@PathVariable("concertId") UUID concertId) {
        return concertService.getConcertById(concertId).getUsersInfo();
    }

    @PutMapping(path="{concertId}/usersInfo/{userId}")
    public void updateUserInfo(@PathVariable("concertId") UUID concertId, @PathVariable("userId") UUID userId, @RequestBody Status status) {
        concertService.addNewUserInfo(concertId, userId, status);
    }

    @DeleteMapping(path="{concertId}/usersInfo/{userId}")
    public void deleteUserInfo(@PathVariable("concertId") UUID concertId,@PathVariable("userId") UUID userId, @RequestBody Status status) {
        concertService.deleteUserInfo(concertId, userId, status);
    }

    @PutMapping(path="TicketmasterSync")
    public void TicketmasterSync() {
        concertService.TicketmasterSync();
    }
}
