package ru.kpfu.itis.revibe.service.impl.admin;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.revibe.dto.auth.UserDto;
import ru.kpfu.itis.revibe.dto.orders.OrderDto;
import ru.kpfu.itis.revibe.dto.products.*;
import ru.kpfu.itis.revibe.entity.enums.Role;
import ru.kpfu.itis.revibe.entity.orders.Order;
import ru.kpfu.itis.revibe.entity.products.*;
import ru.kpfu.itis.revibe.entity.user.*;
import ru.kpfu.itis.revibe.repository.orders.*;
import ru.kpfu.itis.revibe.repository.products.*;
import ru.kpfu.itis.revibe.repository.user.*;
import ru.kpfu.itis.revibe.service.interfaces.admin.AdminService;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final BranchRepository branchRepository;

    public AdminServiceImpl(ProductRepository productRepository,
                            UserRepository userRepository,
                            OrderRepository orderRepository,
                            BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public void addProduct(ProductCreateDto dto) {
        Product p = new Product();
        p.setArticle(UUID.randomUUID());
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        p.setCategory(dto.getCategory());
        p.setBrand(dto.getBrand());
        p.setColor(dto.getColor());
        p.setSize(dto.getSize());
        p.setGender(dto.getGender());
        p.setCondition(dto.getCondition());
        p.setImageUrl(dto.getImageUrl());
        Branch branch = branchRepository.findById(dto.getBranchId()).orElseThrow();
        p.setBranch(branch);
        productRepository.save(p);
    }

    @Override
    public void updateProduct(UUID article, ProductCreateDto dto) {
        Product p = productRepository.findById(article).orElseThrow();
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        p.setCategory(dto.getCategory());
        p.setBrand(dto.getBrand());
        p.setColor(dto.getColor());
        p.setSize(dto.getSize());
        p.setGender(dto.getGender());
        p.setCondition(dto.getCondition());
        p.setImageUrl(dto.getImageUrl());
        productRepository.save(p);
    }

    @Override
    public void deleteProduct(UUID article) {
        Product p = productRepository.findById(article).orElseThrow();
        p.setSold(true);
        productRepository.save(p);
    }

    @Override
    public UserDto addEmployee(String fullName, String email, String password) {
        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.USER);
        userRepository.save(user);

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(email);
        dto.setFullName(fullName);
        dto.setRole(Role.USER);
        return dto;
    }

    @Override
    public void setEmployeeRole(UUID employeeId, String role) {
        User user = userRepository.findById(employeeId).orElseThrow();
        user.setRole(Role.valueOf(role));
        userRepository.save(user);
    }

    @Override
    public List<OrderDto> searchOrders(String orderNumber) {
        UUID orderId = null;
        try {
            orderId = UUID.fromString(orderNumber);
        } catch (Exception ignored) {}
        List<Order> orders = orderRepository.findAll();
        if (orderId != null) {
            UUID finalOrderId = orderId;
            orders = orders.stream()
                    .filter(o -> o.getId().equals(finalOrderId))
                    .collect(Collectors.toList());
        }
        return orders.stream().map(o -> {
            OrderDto dto = new OrderDto();
            dto.setId(o.getId());
            dto.setUserId(o.getUser().getId());
            dto.setBranchId(o.getBranch().getId());
            dto.setItemIds(o.getItems().stream()
                    .map(i -> i.getProduct().getArticle())
                    .collect(Collectors.toList()));
            dto.setCreatedAt(o.getCreatedAt());
            dto.setAccepted(o.isAccepted());
            dto.setActive(o.isActive());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public BranchDto addBranch(BranchDto dto) {
        Branch branch = new Branch();
        branch.setName(dto.getName());
        branch.setCity(dto.getCity());
        branch.setAddress(dto.getAddress());
        branchRepository.save(branch);

        BranchDto result = new BranchDto();
        result.setId(branch.getId());
        result.setName(branch.getName());
        result.setCity(branch.getCity());
        result.setAddress(branch.getAddress());
        return result;
    }

    @Override
    public List<BranchDto> getAllBranches() {
        return branchRepository.findAll().stream().map(branch -> {
            BranchDto dto = new BranchDto();
            dto.setId(branch.getId());
            dto.setName(branch.getName());
            dto.setCity(branch.getCity());
            dto.setAddress(branch.getAddress());
            return dto;
        }).collect(Collectors.toList());
    }
}
