package com.example.exercise1.service.base;

import com.example.exercise1.dto.ItemDto;
import com.example.exercise1.exception.InvalidEntityException;
import com.example.exercise1.exception.InvalidOperationException;

public interface BaseItemService {
    void save(ItemDto item) throws InvalidOperationException;
    void updateItem(Long itemId, ItemDto item) throws InvalidEntityException, InvalidOperationException;
    ItemDto getOneItem(Long id) throws InvalidOperationException, InvalidEntityException;
}
