package unnoba.poo2020.hotel.dto;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class ConfirmBookingRequestDTO {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    private Long roomId;
    private String checkIn;
    private String checkOut;
    private int occupancy;
    // Atributos a agregar para versión posterior son:
    // Desayuno, cochera, cancelación como boolean (haciendo alusión a sí se abono por dichos servicios)

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public Date getCheckInDateConverted() throws ParseException {
        return dateFormat.parse(this.checkIn);
    }

    public void setCheckInDate(Date date) {
        this.checkIn = dateFormat.format(date);
    }

    public Date getCheckOutDateConverted() throws ParseException {
        return dateFormat.parse(this.checkOut);
    }

    public void setCheckOutDate(Date date) {
        this.checkIn = dateFormat.format(date);
    }
}
