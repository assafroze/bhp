package com.example.exercise1.service;

import com.example.exercise1.beans.Item;
import com.example.exercise1.beans.ItemType;
import com.example.exercise1.dto.ItemDto;
import com.example.exercise1.exception.InvalidEntityException;
import com.example.exercise1.exception.InvalidOperationException;
import com.example.exercise1.mapper.ItemMapper;
import com.example.exercise1.repo.ItemRepository;
import com.example.exercise1.service.base.BaseItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportsServiceImpl extends BaseItemServiceImpl implements SportsService{

    public SportsServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper) {
        super(itemRepository, itemMapper);
    }

    private boolean isSportItem(ItemType type){
        if (type.equals(ItemType.SPORTS)){
            return true;
        }

        return false;
    }

    @Override
    public void save(ItemDto itemDto) throws InvalidOperationException {
        if (!isSportItem(itemDto.getItemType())){
            throw new InvalidOperationException("cannot add item outside your domain");
        }

        super.save(itemDto);
    }

    @Override
    public void updateItem(Long id, ItemDto itemDto) throws InvalidEntityException, InvalidOperationException {
        if (!isSportItem(itemDto.getItemType())){
            throw new InvalidOperationException("cannot add item outside your domain");
        }

        super.updateItem(id, itemDto);
    }

    @Override
    public long countItems() {
        return itemRepository.countItemByType(ItemType.SPORTS.toString());
    }

    @Override
    public ItemDto getOneItem(Long id) throws InvalidEntityException, InvalidOperationException {
        System.out.println("rgvgr");
        ItemDto itemDto = super.getOneItem(id);

        if (!isSportItem(itemDto.getItemType())){
            throw new InvalidOperationException("cannot add item outside your domain");
        }

        return itemDto;
    }

    @Override
    public List<ItemDto> getAllItems() {
        return this.itemMapper.mapDtosToItems(itemRepository.findByItemType(ItemType.SPORTS));
    }
}
