/*
 * Copyright (c) 2022 Aleksandr Eliseev
 *
 * This source code is Aleksandr Eliseev's Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package space.eliseev.keycloakadmin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.eliseev.keycloakadmin.dto.UserDto;
import space.eliseev.keycloakadmin.service.UserService;

import java.util.List;
import java.util.Optional;

/**
 * Получение информации о пользователях
 *
 * @author <a href="mailto:a.s.eliseev@yandex.ru">Aleksandr Eliseev</a>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user", produces = "application/json; charset=UTF-8")
@Tag(name = "user", description = "The User API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get all users", description = "It can be used to get list of all users in all realms",
            tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))),
                    description = "Successful operation (List may be empty)")
    })
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @Operation(summary = "Get user by ID", description = "It returns one user with specified id",
            tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))),
                    description = "Successful operation (List may be empty)"),
            @ApiResponse(responseCode = "404", content = @Content, description = "User not found")
    })
    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getById(@Parameter(required = true, description = "ID of requested user")
                                           @PathVariable String id) {
        final Optional<UserDto> user = userService.getById(id);
        return user
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get user by Username", description = "It returns one user with specified username",
            tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))),
                    description = "Successful operation (List may be empty)"),
            @ApiResponse(responseCode = "404", content = @Content, description = "User not found")
    })
    @GetMapping("/get/username")
    public ResponseEntity<UserDto> getUserByUsername(@RequestParam String username) {
        final Optional<UserDto> user = userService.getByUsername(username);
        return user
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get user by email", description = "It returns one user with specified email",
            tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))),
                    description = "Successful operation (List may be empty)"),
            @ApiResponse(responseCode = "404", content = @Content, description = "User not found")
    })
    @GetMapping("/get/email")
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
        final Optional<UserDto> user = userService.getByEmail(email);
        return user
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
