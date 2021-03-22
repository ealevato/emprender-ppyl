package unnoba.poo2020.hotel.service;

import unnoba.poo2020.hotel.model.Room;

import java.util.List;
import java.util.Date;
import java.util.Optional;


public interface RoomService {

    List<Room> getRoomsAvailable(Date checkIn, Date checkOut, int occupancy);
    Optional<Room> findById(Long roomId);
}
