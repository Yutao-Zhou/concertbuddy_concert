package com.concertbuddy.concertbuddyconcert.concert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConcertService {

    private final ConcertRepository concertRepository;

    @Autowired
    public ConcertService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public List<Concert> getConcerts() {
        return concertRepository.findAll();
    }

    public Concert getConcertById(UUID concertId) {
        Optional<Concert> optionalConcertById = concertRepository.findById(concertId);
        if (optionalConcertById.isEmpty()) {
            throw new IllegalStateException(
                    "Concert with id " + concertId + " does not exist"
            );
        }
        return optionalConcertById.get();
    }

    public void addNewConcert(Concert concert) {
        List<Concert> concertsByName = concertRepository.findAllByName(concert.getName());
        if (concertsByName.stream().anyMatch(e -> e.getPerformingArtist().equals(concert.getPerformingArtist()))) {
            throw new IllegalStateException("This concert already exists");
        }
        concertRepository.save(concert);
    }

    public void deleteConcert(UUID concertId) {
        boolean exists = concertRepository.existsById(concertId);
        if (!exists) {
            throw new IllegalStateException(
                    "Concert with id " + concertId + " does not exist"
            );
        }
        concertRepository.deleteById(concertId);
    }

}
