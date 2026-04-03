package ru.kpfu.itis.revibe.service.interfaces.admin;

import ru.kpfu.itis.revibe.dto.auth.UserDto;
import ru.kpfu.itis.revibe.dto.orders.OrderDto;
import ru.kpfu.itis.revibe.dto.products.ProductCreateDto;

import java.util.List;
import java.util.UUID;

public interface AdminService {

    void addProduct(ProductCreateDto dto);
    void updateProduct(String article, ProductCreateDto dto);
    void deleteProduct(String article);

    UserDto addEmployee(String fullName, String email, String password);
    void setEmployeeRole(UUID employeeId, String role);

    List<OrderDto> searchOrders(String orderNumber);
}
