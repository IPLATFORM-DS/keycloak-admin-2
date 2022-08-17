/*
 * Copyright (c) 2022 Aleksandr Eliseev
 *
 * This source code is Aleksandr Eliseev's Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */

package space.eliseev.keycloakadmin.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.dto.UserDto;
import space.eliseev.keycloakadmin.mapper.UserMapper;
import space.eliseev.keycloakadmin.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Реализация {@link UserService}
 *
 * @author <a href="mailto:a.s.eliseev@yandex.ru">Aleksandr Eliseev</a>
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getById(@NonNull final String id) {
        return userRepository.findById(id).map(userMapper::userToUserDto);
    }
    @Override
    public List<UserDto> getByUsername(@NonNull String username) {
        return userRepository.findByUsername(username)
                .stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getByEmail(@NonNull String email) {
        return userRepository.findByEmail(email).stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

}
