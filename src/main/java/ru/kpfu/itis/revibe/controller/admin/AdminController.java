package ru.kpfu.itis.revibe.controller.admin;

import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.revibe.dto.auth.UserDto;
import ru.kpfu.itis.revibe.dto.orders.OrderDto;
import ru.kpfu.itis.revibe.dto.products.BranchDto;
import ru.kpfu.itis.revibe.dto.products.ProductCreateDto;
import ru.kpfu.itis.revibe.service.interfaces.admin.AdminService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody ProductCreateDto dto) {
        adminService.addProduct(dto);
    }

    @PutMapping("/products/{article}")
    public void updateProduct(@PathVariable String article,
                              @RequestBody ProductCreateDto dto) {
        adminService.updateProduct(article, dto);
    }

    @DeleteMapping("/products/{article}")
    public void deleteProduct(@PathVariable String article) {
        adminService.deleteProduct(article);
    }

    @PostMapping("/employees")
    public UserDto addEmployee(@RequestParam String fullName,
                               @RequestParam String email,
                               @RequestParam String password) {
        return adminService.addEmployee(fullName, email, password);
    }

    @PutMapping("/employees/{id}/role")
    public void setEmployeeRole(@PathVariable UUID id,
                                @RequestParam String role) {
        adminService.setEmployeeRole(id, role);
    }

    @GetMapping("/orders/search")
    public List<OrderDto> searchOrders(@RequestParam String orderNumber) {
        return adminService.searchOrders(orderNumber);
    }

    @PostMapping("/branches")
    public BranchDto addBranch(@RequestBody BranchDto dto) {
        return adminService.addBranch(dto);
    }

    @GetMapping("/branches")
    public List<BranchDto> getAllBranches() {
        return adminService.getAllBranches();
    }
}
