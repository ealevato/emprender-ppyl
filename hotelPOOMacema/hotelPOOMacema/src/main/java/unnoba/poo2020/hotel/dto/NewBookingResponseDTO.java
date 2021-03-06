package unnoba.poo2020.hotel.dto;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

// Es el DTO para la respuesto a una nueva reserva
public class NewBookingResponseDTO {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");
    
    private RoomDTO room;
    private String checkIn;
    private String checkOut;
    private int occupancy;

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
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
