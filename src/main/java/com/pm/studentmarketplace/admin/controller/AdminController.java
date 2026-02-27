package com.pm.studentmarketplace.admin.controller;

import com.pm.studentmarketplace.admin.service.AdminService;
import com.pm.studentmarketplace.auth.model.User;
import com.pm.studentmarketplace.listing.model.Listing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    public final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        long totalUsers = adminService.getTotalUsers();
        long totalListings = adminService.getTotalListings();

        List<User> users = adminService.getAllUsers();
        List<Listing> listings = adminService.getAllListings();

        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("totalListings", totalListings);
        model.addAttribute("users", users);
        model.addAttribute("listings", listings);

        return  "admin/dashboard";
    }

    @PostMapping("/listing/{id}/approve")
    public String approveListing(@PathVariable Long id){
        adminService.approveListing(id);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/listing/{id}/block")
    public String blockListing(@PathVariable Long id){
        adminService.blockListing(id);
        return "redirect:/admin/dashboard";
    }
}
