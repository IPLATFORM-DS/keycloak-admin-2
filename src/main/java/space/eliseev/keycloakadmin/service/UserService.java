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
import space.eliseev.keycloakadmin.dto.UserDto;

import java.util.List;
import java.util.Optional;

/**
 * Получение информации о пользователях
 *
 * @author <a href="mailto:a.s.eliseev@yandex.ru">Aleksandr Eliseev</a>
 */
public interface UserService {

    /**
     * Получить список всех пользователей
     *
     * @return список всех пользователей
     */
    List<UserDto> getAllUsers();

    /**
     * Получить пользователя по идентификатору
     *
     * @param id Идентификатор пользователя
     * @return пользователя
     */
    Optional<UserDto> getById(@NonNull String id);
}
