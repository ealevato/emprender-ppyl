package unnoba.poo2020.hotel.controller;

import unnoba.poo2020.hotel.dto.*;
import unnoba.poo2020.hotel.model.Booking;
import unnoba.poo2020.hotel.model.Room;
import unnoba.poo2020.hotel.model.User;
import unnoba.poo2020.hotel.service.BookingService;
import unnoba.poo2020.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



/**
Este controlador maneja la gestión de reservas (alta y baja de reservas)
 NOTA: la baja (cancelación) de las reservas se implementará en versiones posteriores
 */
@Controller
@RequestMapping("/bookings")  // Mapeamos las HTTP request a /bookings a la clase controlador BookingController
public class BookingController {

    private RoomService roomService;
    private BookingService bookingService;
    private ModelMapper modelMapper;  // Nos va a permitir convertir un DTO a objeto de negocios y de objeto de negocios a DTO
                                      // Parsea 2 objetos que son similares

    @Autowired
    public BookingController(RoomService roomService, BookingService bookingService, ModelMapper modelMapper){
        this.bookingService=bookingService;
        this.roomService=roomService;
        this.modelMapper=modelMapper;
    }

    @GetMapping("/availability")
    public String roomsAvailabily(Model model){    // Cambiar Nombre
        // model es lo que vamos a pasarle a la vista
        // Agregamos 2 tipos de atributos haciendo uso de 2 DTOs distintos ya que los datos que tienen no son iguales
        model.addAttribute("roomsAvailability", new RoomsAvailabilityDTO());
        model.addAttribute("rooms", new ArrayList<RoomDTO>());
        return "bookings/availability";  // Renderizamos la vista bookings/availability
    }

    @PostMapping("/availability")
    public String getRoomsAvailable(@ModelAttribute RoomsAvailabilityDTO roomsAvailabilityDTO, Model model){
        List<Room> rooms = new ArrayList<>();
        try {
            rooms = roomService.getRoomsAvailable(
                    roomsAvailabilityDTO.getCheckInDateConverted(),
                    roomsAvailabilityDTO.getCheckOutDateConverted(),
                    roomsAvailabilityDTO.getOccupancy());
        } catch (Exception e) {}

        List<RoomDTO> roomsDTO = rooms.stream()
                .map(room -> modelMapper.map(room, RoomDTO.class))
                .collect(Collectors.toList());
        model.addAttribute("rooms", roomsDTO);
        model.addAttribute("roomsAvailability", roomsAvailabilityDTO);
        model.addAttribute("booking", new NewBookingRequestDTO());
        return "bookings/availability";
    }

    @PostMapping("/new")
    public String newBooking(@ModelAttribute NewBookingRequestDTO newBookingRequestDTO,
                             Model model){
        // Pasamos como parámetro la petición de reserva anteriormente creada
        // Creamos la respuesta a la petición de reserva del usuario para poder generar los parámetros de salida
        NewBookingResponseDTO booking = new NewBookingResponseDTO();
        RoomDTO roomDTO = modelMapper.map(roomService.findById(newBookingRequestDTO.getRoomId()).get(), RoomDTO.class);
        // Seteamos cada dato para que se corresponda a la petición de reserva que hizo
        booking.setRoom(roomDTO);
        booking.setCheckIn(newBookingRequestDTO.getCheckIn());
        booking.setCheckOut(newBookingRequestDTO.getCheckOut());
        booking.setOccupancy(newBookingRequestDTO.getOccupancy());
        // Agregamos la respuesta de la reserva para luego ser renderizada en la vista
        model.addAttribute("booking", booking);
        return "bookings/new";
    }

    @PostMapping("/confirm")
    public String createBooking(@ModelAttribute ConfirmBookingRequestDTO confirmBookingRequestDTO,
                                Authentication authentication,
                                Model model){
        Booking booking = modelMapper.map(confirmBookingRequestDTO, Booking.class);
        booking.setId(null);
        booking.setGuest((User)authentication.getPrincipal());
        try {  // Verifica si se puede reservar esa habitación y de ser así, lo redirecciona a la página de confirmación de reserva
            bookingService.newBooking(booking);
            return "redirect:/bookings/confirm";
        } catch (Exception e) { // En caso que la habitación no esté disponible al clickear en confirmar, lo redirecciona a la página de realizar reservas
            return "redirect:/bookings/availability";
        }
    }

    @GetMapping("/confirm")
    public String bookingConfirmed(){
        return "bookings/confirmed";
    }
}



