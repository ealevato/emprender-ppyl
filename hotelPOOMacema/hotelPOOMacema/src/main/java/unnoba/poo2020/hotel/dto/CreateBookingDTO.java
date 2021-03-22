package unnoba.poo2020.hotel.dto;

import java.util.Date;

public class CreateBookingDTO {
    private Long roomId;
    private Date checkIn;
    private Date checkOut;

    // REVISAR - pero creo que acá se agregarían al crear el booking datos como cochera, desayuno y cancelación

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }
}
