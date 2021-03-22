package unnoba.poo2020.hotel.service;

import unnoba.poo2020.hotel.model.Room;
import unnoba.poo2020.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by jpgm on 16/11/20.
 */
@Service
public class RoomServiceImp implements RoomService {
    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImp(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getRoomsAvailable(Date checkIn, Date checkOut, int occupancy) {
        return roomRepository.getRoomsAvailable(checkIn,checkOut, occupancy);
    }

    @Override
    public Optional<Room> findById(Long roomId) {
        return roomRepository.findById(roomId);
    }
}
