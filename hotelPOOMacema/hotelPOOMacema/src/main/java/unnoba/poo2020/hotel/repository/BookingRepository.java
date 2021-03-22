package unnoba.poo2020.hotel.repository;

import unnoba.poo2020.hotel.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
}
