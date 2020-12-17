package com.example.exercise1.service;

import com.example.exercise1.beans.Item;
import com.example.exercise1.dto.ItemDto;
import com.example.exercise1.exception.InvalidEntityException;
import com.example.exercise1.mapper.ItemMapper;
import com.example.exercise1.repo.ItemRepository;
import com.example.exercise1.service.base.BaseItemService;
import com.example.exercise1.service.base.BaseItemServiceImpl;
import com.example.exercise1.util.TablePrinter;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends BaseItemServiceImpl implements AdminService {

    public AdminServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper) {
        super(itemRepository, itemMapper);
    }

    @Override
    public long countItems() {

        return this.itemRepository.count();
    }

    @Override
    public void deleteItem(ItemDto itemDto) throws InvalidEntityException {
        if(!itemRepository.existsById(itemDto.getId())){
            throw new InvalidEntityException("cannot delete - id not exist");
        }

        itemRepository.delete(itemMapper.itemDtoToItem(itemDto));
    }

    @Override
    public List<ItemDto> getAllItems() {
        List<ItemDto> list = itemMapper.mapDtosToItems(itemRepository.findAll());

        return itemMapper.mapDtosToItems(itemRepository.findAll());
    }
}
