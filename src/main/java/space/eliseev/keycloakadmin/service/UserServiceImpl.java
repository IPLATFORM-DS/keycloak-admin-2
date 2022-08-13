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
import space.eliseev.keycloakadmin.commons.TimeUtils;
import space.eliseev.keycloakadmin.dto.RealmDto;
import space.eliseev.keycloakadmin.dto.UserDto;
import space.eliseev.keycloakadmin.entity.User;
import space.eliseev.keycloakadmin.mapper.UserMapper;
import space.eliseev.keycloakadmin.repository.UserRepository;

import java.time.LocalDateTime;
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
    private final RealmService realmService;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getById(@NonNull final String id) {
        Optional<User> user = userRepository.findById(id);
        UserDto toDto = user.map(this::toDto).orElse(null);
        return Optional.ofNullable(toDto);
    }
    @Override
    public Optional<UserDto> getByUsername(@NonNull String username) {
        Optional<User> user = userRepository.findByUsername(username);
        UserDto toDto = user.map(this::toDto).orElse(null);
        return Optional.ofNullable(toDto);
    }

    @Override
    public List<UserDto> getByEmail(@NonNull String email) {
        List<UserDto> user = userRepository.findByEmail(email).stream().map(this::toDto).collect(Collectors.toList());
        return user;
    }

    private UserDto toDto(User user) {
        Optional<RealmDto> realm = realmService.getById(user.getRealmId());
        LocalDateTime time = TimeUtils.toLocalDateTime(user.getCreatedTimestamp());
        String realmName = realm.map(RealmDto::getName).orElse(null);
        UserDto dto = userMapper.userToUserDto(user);
        dto.setRealmName(realmName);
        dto.setCreatedTimestampLocalDateTime(time);
        return dto;
    }
}
