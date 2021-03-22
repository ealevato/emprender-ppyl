package unnoba.poo2020.hotel.service;

import unnoba.poo2020.hotel.model.Booking;
import unnoba.poo2020.hotel.model.Room;
import unnoba.poo2020.hotel.repository.BookingRepository;
import unnoba.poo2020.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class BookingServiceImp implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomRepository roomRepository;


    @Override
    @Transactional
    public Booking newBooking(Booking booking) throws Exception {
        // Chequeos para ver si crea o no la nueva reserva
        Room room = roomRepository.isRoomAvailable((booking.getCheckIn()), booking.getCheckOut(), booking.getRoom().getId());
        if (booking.getCheckIn().before(new Date())
                || booking.getCheckIn().after(booking.getCheckOut())){
            throw new Exception("");
        }
        if (room != null) {
            booking.setRoom(room);
            booking.setCost(booking.getRoom().getPrice());
            booking.setCreatedAt(new Date());
            booking = bookingRepository.save(booking);
        } else {
            throw new Exception("");
        }
        return booking;
    }
}