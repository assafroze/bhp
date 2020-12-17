package com.example.exercise1.clr.services;

import com.example.exercise1.beans.Item;
import com.example.exercise1.beans.ItemType;
import com.example.exercise1.clr.bootstrap.ItemFactory;
import com.example.exercise1.mapper.ItemMapper;
import com.example.exercise1.service.ElectricityService;
import com.example.exercise1.service.SportsServiceImpl;
import com.example.exercise1.util.ArtUtils;
import com.example.exercise1.util.TablePrinter;
import com.example.exercise1.util.TestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
@RequiredArgsConstructor
public class ElectricityServiceTesting implements CommandLineRunner {
    private final ItemMapper itemMapper;
    private final ElectricityService electricityService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(ArtUtils.ROUND_4_SERVICE_TESTING);

        TestUtils.printTestInfo("electricity service - get all items");
        TablePrinter.print(electricityService.getAllItems());

        TestUtils.printTestInfo("electricity service - add item");
        Item toAdd1 = ItemFactory.generate();
        toAdd1.setItemType(ItemType.ELECTRICITY);
        electricityService.save(itemMapper.itemToItemDto(toAdd1));
        TablePrinter.print(electricityService.getAllItems());

        TestUtils.printTestInfo("electricity service - update item");
        electricityService.updateItem(8L, itemMapper.itemToItemDto(toAdd1));
        TablePrinter.print(electricityService.getAllItems());

        TestUtils.printTestInfo("electricity service - count items");
        System.out.println(electricityService.countItems());

        TestUtils.printTestInfo("electricity service - update non existing item");
        try {
            electricityService.updateItem(200L, itemMapper.itemToItemDto(toAdd1));
        } catch (Exception e) {
            System.err.println("update non existing item");
        }

        TestUtils.printTestInfo("electricity service - update non ELECTRICITY item");
        try {
            toAdd1.setItemType(ItemType.FOOD);
            electricityService.updateItem(2L, itemMapper.itemToItemDto(toAdd1));
        } catch (Exception e) {
            System.err.println("update non ELECTRICITY item");
        }

        TestUtils.printTestInfo("electricity service - get non existing item");
        try {
            electricityService.getOneItem(300L);
        } catch (Exception e) {
            System.err.println("get non existing item");
        }

        TestUtils.printTestInfo("electricity service - save non SPORTS item");
        try {
            toAdd1.setItemType(ItemType.FOOD);
            electricityService.save(itemMapper.itemToItemDto(toAdd1));
        } catch (Exception e) {
            System.err.println("save non SPORTS item");
        }
    }
}