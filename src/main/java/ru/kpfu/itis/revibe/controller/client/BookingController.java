package ru.kpfu.itis.revibe.controller.client;

import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.revibe.dto.orders.BookingDto;
import ru.kpfu.itis.revibe.service.interfaces.orders.BookingService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public BookingDto createBooking(@RequestParam UUID userId,
                                    @RequestParam UUID productId,
                                    @RequestParam UUID branchId) {
        return bookingService.createBooking(userId, productId, branchId);
    }

    @GetMapping
    public List<BookingDto> getUserBookings(@RequestParam UUID userId) {
        return bookingService.getUserBookings(userId);
    }

    @PostMapping("/{id}/complete")
    public BookingDto completeBooking(@PathVariable UUID id) {
        return bookingService.completeBooking(id);
    }

    @GetMapping("/{id}/qr")
    public String getQr(@PathVariable UUID id) {
        return bookingService.getBookingQr(id);
    }
}