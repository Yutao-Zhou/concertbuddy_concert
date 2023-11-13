package com.concertbuddy.concertbuddyconcert.concert;
import org.javatuples.Pair;
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

    public void TicketmasterSync() {
        // Ticketmaster API call goes here
    }

    public void addNewUserInfo(UUID concertId, UUID userId, Status status) {
        Optional<Concert> optionalConcertById = concertRepository.findById(concertId);
        if (optionalConcertById.isEmpty()) {
            throw new IllegalStateException(
                    "Concert with id " + concertId + " does not exist"
            );
        }
        Concert concertById =  optionalConcertById.get();

        Pair<UUID, Status> userInfoToAdd = Pair.with(userId, status);
        List<Pair<UUID, Status>> UsersInfo = concertById.getUsersInfo();

        int index = 0;
        boolean userInfoToAddIsNew = true;
        for (Pair<UUID, Status> UserInfo : UsersInfo) {
            if (UserInfo.getValue0().equals(userId)) {
                UsersInfo.set(index, userInfoToAdd);
                userInfoToAddIsNew = false;
                break;
            }
            index ++;
        }
        if (userInfoToAddIsNew) {
            UsersInfo.add(userInfoToAdd);
        }

        concertById.setUsersInfo(UsersInfo);
        concertRepository.save(concertById);
    }

    public void deleteUserInfo(UUID concertId, UUID userId, Status status) {
        Optional<Concert> optionalConcertById = concertRepository.findById(concertId);
        if (optionalConcertById.isEmpty()) {
            throw new IllegalStateException(
                    "Concert with id " + concertId + " does not exist"
            );
        }
        Concert concertById =  optionalConcertById.get();

        List<Pair<UUID, Status>> UsersInfo = concertById.getUsersInfo();

        int index = 0;
        for (Pair<UUID, Status> UserInfo : UsersInfo) {
            if (UserInfo.getValue0().equals(userId)) {
                UsersInfo.remove(index);
                break;
            }
            index ++;
        }

        concertById.setUsersInfo(UsersInfo);
        concertRepository.save(concertById);
    }
}
