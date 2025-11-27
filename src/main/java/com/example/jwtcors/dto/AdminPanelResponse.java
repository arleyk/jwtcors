package com.example.jwtcors.dto;

public record AdminPanelResponse(String title,
                                 Long userId,
                                 String Username,
                                 String role,
                                 Object Permissions) {


}
