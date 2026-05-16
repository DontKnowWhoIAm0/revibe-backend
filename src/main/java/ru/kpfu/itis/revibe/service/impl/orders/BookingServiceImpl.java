package ru.kpfu.itis.revibe.service.impl.orders;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.revibe.dto.orders.BookingDto;
import ru.kpfu.itis.revibe.entity.orders.Booking;
import ru.kpfu.itis.revibe.entity.products.Product;
import ru.kpfu.itis.revibe.entity.user.User;
import ru.kpfu.itis.revibe.entity.products.Branch;
import ru.kpfu.itis.revibe.repository.orders.BookingRepository;
import ru.kpfu.itis.revibe.repository.products.ProductRepository;
import ru.kpfu.itis.revibe.repository.user.UserRepository;
import ru.kpfu.itis.revibe.repository.products.BranchRepository;
import ru.kpfu.itis.revibe.service.interfaces.orders.BookingService;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              UserRepository userRepository,
                              ProductRepository productRepository,
                              BranchRepository branchRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public BookingDto createBooking(UUID userId, UUID productId, UUID branchId) {
        User user = userRepository.findById(userId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();
        Branch branch = branchRepository.findById(branchId).orElseThrow();

        if (product.isSold()) {
            throw new RuntimeException("Product is already sold");
        }

        boolean alreadyBooked = bookingRepository.findAll().stream()
                .anyMatch(b -> b.getProduct().getArticle().equals(product.getArticle()) && !b.isCompleted());
        if (alreadyBooked) {
            throw new RuntimeException("Product is already booked");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setProduct(product);
        booking.setBranch(branch);
        booking.setBookedAt(LocalDateTime.now());
        booking.setCompleted(false);

        bookingRepository.save(booking);

        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setUserId(userId);
        dto.setProductId(productId);
        dto.setBranchId(branchId);
        dto.setBookedAt(booking.getBookedAt());
        dto.setCompleted(false);

        return dto;
    }

    @Override
    public List<BookingDto> getUserBookings(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return bookingRepository.findByUser(user).stream().map(b -> {
            BookingDto dto = new BookingDto();
            dto.setId(b.getId());
            dto.setUserId(userId);
            dto.setProductId(b.getProduct().getArticle() != null ? b.getProduct().getArticle() : null);
            dto.setBranchId(b.getBranch().getId());
            dto.setBookedAt(b.getBookedAt());
            dto.setCompleted(b.isCompleted());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public BookingDto completeBooking(UUID bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        booking.setCompleted(true);
        bookingRepository.save(booking);

        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setUserId(booking.getUser().getId());
        dto.setProductId(booking.getProduct().getArticle());
        dto.setBranchId(booking.getBranch().getId());
        dto.setBookedAt(booking.getBookedAt());
        dto.setCompleted(true);

        return dto;
    }

    @Override
    public String getBookingQr(UUID bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        String qrContent = "BOOKING:" + booking.getId();
        return Base64.getEncoder().encodeToString(qrContent.getBytes());
    }
}