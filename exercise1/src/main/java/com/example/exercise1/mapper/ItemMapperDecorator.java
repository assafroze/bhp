package com.example.exercise1.mapper;

import com.example.exercise1.beans.Item;
import com.example.exercise1.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;


public abstract class ItemMapperDecorator implements ItemMapper {
    private ItemMapper mapper;

    private DateMapper dateMapper;

    @Autowired
    public void setDateMapper(DateMapper dateMapper) {

        this.dateMapper = dateMapper;
    }

    @Autowired
    public void setMapper(ItemMapper mapper) {

        this.mapper = mapper;
    }

    @Override
    public ItemDto itemToItemDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setCreatedDate(dateMapper.asOffsetDateTime(item.getCreatedDate()));
        itemDto.setLastModifiedDate(dateMapper.asOffsetDateTime(item.getLastModifiedDate()));
        itemDto.setItemType(item.getItemType());
        itemDto.setPrice(item.getPrice());
        itemDto.setItemName(item.getName());
        return itemDto;
    }

    @Override
    public Item itemDtoToItem(ItemDto itemDto) {
        System.out.println(itemDto);
        Item item = new Item();
        item.setId(itemDto.getId());
        item.setCreatedDate(dateMapper.asTimestamp(itemDto.getCreatedDate()));
        item.setLastModifiedDate(dateMapper.asTimestamp(itemDto.getLastModifiedDate()));
        item.setItemType(itemDto.getItemType());
        item.setPrice(itemDto.getPrice());
        item.setName(itemDto.getItemName());
        return item;
    }

    @Override
    public List<ItemDto> mapDtosToItems(List<Item> items){
        return items.stream().map(item -> this.itemToItemDto(item)).collect(Collectors.toList());
    }

    @Override
    public List<Item> mapItemsToDtos(List<ItemDto> itemDto){
        return itemDto.stream().map(item -> this.itemDtoToItem(item)).collect(Collectors.toList());
    }
}