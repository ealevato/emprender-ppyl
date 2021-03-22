package unnoba.poo2020.hotel.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bookings")  // Cómo se va a llamar la tabla en nuestra BD
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "guest_id")
    private User guest;
    @Column(name = "check_in")
    @Temporal(TemporalType.DATE)
    private Date checkIn;
    @Column(name = "check_out")
    @Temporal(TemporalType.DATE)
    private Date checkOut;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    private float cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getGuest() {
        return guest;
    }

    public void setGuest(User guest) {
        this.guest = guest;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
