package com.example.exercise1.service;

import com.example.exercise1.beans.Item;
import com.example.exercise1.dto.ItemDto;
import com.example.exercise1.exception.InvalidEntityException;
import com.example.exercise1.exception.InvalidOperationException;
import com.example.exercise1.service.base.BaseItemService;

import java.util.List;

public interface AdminService extends BaseItemService {
    long countItems();
    public void deleteItem(ItemDto itemDto) throws InvalidEntityException;
    List<ItemDto> getAllItems();


}
