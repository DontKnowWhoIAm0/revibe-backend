package ru.kpfu.itis.revibe.service.interfaces.orders;

import ru.kpfu.itis.revibe.dto.orders.BookingDto;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    BookingDto createBooking(UUID userId, UUID productId, UUID branchId);
    List<BookingDto> getUserBookings(UUID userId);
    BookingDto completeBooking(UUID bookingId);
    String getBookingQr(UUID bookingId);
}