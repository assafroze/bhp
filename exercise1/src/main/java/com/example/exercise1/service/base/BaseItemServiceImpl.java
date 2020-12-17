package com.example.exercise1.service.base;

import com.example.exercise1.beans.Item;
import com.example.exercise1.dto.ItemDto;
import com.example.exercise1.exception.InvalidEntityException;
import com.example.exercise1.exception.InvalidOperationException;
import com.example.exercise1.mapper.ItemMapper;
import com.example.exercise1.repo.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class BaseItemServiceImpl implements BaseItemService {

    protected final ItemRepository itemRepository;
    protected final ItemMapper itemMapper;

    @Override
    public void save(ItemDto itemDto) throws InvalidOperationException {
        Item item = itemMapper.itemDtoToItem(itemDto);
        itemRepository.save(item);
    }

    @Override
    public void updateItem(Long id, ItemDto itemDto) throws InvalidEntityException, InvalidOperationException {
        if (!itemRepository.existsById(id)){
            throw new InvalidEntityException("Cannot update not existing id");
        }

        Item item = itemMapper.itemDtoToItem(itemDto);
        item.setId(id);
        itemRepository.saveAndFlush(item);
    }

    @Override
    public ItemDto getOneItem(Long id) throws InvalidEntityException, InvalidOperationException {
        if (!itemRepository.existsById(id)){
            throw new InvalidEntityException("item not found");
        }

        Item item = itemRepository.getOne(id);

        return itemMapper.itemToItemDto(item);
    }
}
