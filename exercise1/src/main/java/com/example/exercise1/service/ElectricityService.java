package com.example.exercise1.service;

import com.example.exercise1.dto.ItemDto;
import com.example.exercise1.service.base.BaseItemService;

import java.util.List;

public interface ElectricityService extends BaseItemService {
    long countItems();
    List<ItemDto> getAllItems();
}
