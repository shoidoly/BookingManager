package com.pa165.bookingmanager.service;

import com.pa165.bookingmanager.dto.RoomDto;
import org.hibernate.criterion.Criterion;

import java.util.List;

/**
 * @author Jakub Polak
 */
public interface RoomService
{
    /**
     * Find all
     *
     * @return list of room DTOs
     */
    List<RoomDto> findAll();

    /**
     * Find by criteria
     *
     * @param criterion
     * @return list of room DTOs
     */
    List<RoomDto> findByCriteria(Criterion criterion);

    /**
     * Find
     *
     * @param id
     * @return room DTO
     */
    RoomDto find(Long id);

    /**
     * Create
     *
     * @param roomDto room DTO
     */
    void create(RoomDto roomDto);

    /**
     * Update
     *
     * @param roomDto room DTO
     */
    void update(RoomDto roomDto);

    /**
     * Delete
     *
     * @param roomDto room DTO
     */
    void delete(RoomDto roomDto);
}
