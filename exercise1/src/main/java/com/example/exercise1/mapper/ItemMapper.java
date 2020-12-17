package com.example.exercise1.mapper;

import com.example.exercise1.beans.Item;
import com.example.exercise1.dto.ItemDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {DateMapper.class},componentModel = "spring")
@DecoratedWith(ItemMapperDecorator.class)
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

//    @Mappings({@Mapping(source = "name",target = "itemName"),})
    ItemDto itemToItemDto(Item item);

//    @Mappings(value = {@Mapping(source = "name", target = "itemName"),})
    Item itemDtoToItem(ItemDto itemDto);

    List<ItemDto> mapDtosToItems(List<Item> items);
    List<Item> mapItemsToDtos(List<ItemDto> ItemDto);
}

