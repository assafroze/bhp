package com.example.exercise1.service;

import com.example.exercise1.beans.ItemType;
import com.example.exercise1.dto.ItemDto;
import com.example.exercise1.exception.InvalidEntityException;
import com.example.exercise1.exception.InvalidOperationException;
import com.example.exercise1.mapper.ItemMapper;
import com.example.exercise1.repo.ItemRepository;
import com.example.exercise1.service.base.BaseItemServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectricityServiceImpl extends BaseItemServiceImpl implements ElectricityService{
    public ElectricityServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper) {
        super(itemRepository, itemMapper);
    }

    private boolean isElectricityItem(ItemType type){
        if (type.equals(ItemType.ELECTRICITY)){
            return true;
        }

        return false;
    }

    @Override
    public void save(ItemDto itemDto) throws InvalidOperationException {
        if (!isElectricityItem(itemDto.getItemType())){
            throw new InvalidOperationException("cannot add item outside your domain");
        }

        super.save(itemDto);
    }

    @Override
    public void updateItem(Long id, ItemDto itemDto) throws InvalidEntityException, InvalidOperationException {
        if (!isElectricityItem(itemDto.getItemType())){
            throw new InvalidOperationException("cannot add item outside your domain");
        }

        super.updateItem(id, itemDto);
    }

    @Override
    public ItemDto getOneItem(Long id) throws InvalidEntityException, InvalidOperationException {
        ItemDto itemDto = super.getOneItem(id);

        if (!isElectricityItem(itemDto.getItemType())){
            throw new InvalidOperationException("cannot add item outside your domain");
        }

        return itemDto;
    }

    @Override
    public long countItems() {
        return itemRepository.countItemByType(ItemType.ELECTRICITY.toString());
    }

    @Override
    public List<ItemDto> getAllItems() {
        return this.itemMapper.mapDtosToItems(itemRepository.findByItemType(ItemType.ELECTRICITY));
    }
}
