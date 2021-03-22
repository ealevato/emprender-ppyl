package unnoba.poo2020.hotel.repository;

import unnoba.poo2020.hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import javax.persistence.LockModeType; // Para poder utilizar el PESSIMISTIC_WRITE

public interface RoomRepository extends JpaRepository<Room,Long> {
    @Query("Select r From Room r where r.occupancy >= :occupancy and r.availability > (" +
            "Select count(b) From Booking b where b.room = r and b.checkIn between :checkIn and :checkOut)")
    List<Room> getRoomsAvailable(@Param("checkIn")Date checkIn, @Param("checkOut") Date checkOut,
                                 @Param("occupancy") int occupancy);

    @Query("Select r From Room r where r.id = :roomId and r.availability > (" +
            "Select count(b) From Booking b where b.room = r and b.checkIn between :checkIn and :checkOut)")
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    // Bloquea la escritura para que 2 personas no reserven al mismo tiempo la misma habitación si hay mucha concurrencia
    Room isRoomAvailable(@Param("checkIn")Date checkIn, @Param("checkOut") Date checkOut, @Param("roomId") Long roomId);
}
