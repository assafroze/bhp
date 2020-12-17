package com.example.exercise1.clr.services;

import com.example.exercise1.beans.Item;
import com.example.exercise1.beans.ItemType;
import com.example.exercise1.clr.bootstrap.ItemFactory;
import com.example.exercise1.mapper.ItemMapper;
import com.example.exercise1.service.SportsServiceImpl;
import com.example.exercise1.util.ArtUtils;
import com.example.exercise1.util.TablePrinter;
import com.example.exercise1.util.TestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@Order(3)
@RequiredArgsConstructor
public class SportsServiceTesting implements CommandLineRunner {

    private final ItemMapper itemMapper;
    private final SportsServiceImpl sportsService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(ArtUtils.ROUND_3_SERVICE_TESTING);

        TestUtils.printTestInfo("sports service - get all items");
        TablePrinter.print(sportsService.getAllItems());

        TestUtils.printTestInfo("sports service - add item");
        Item toAdd1 = ItemFactory.generate();
        toAdd1.setItemType(ItemType.SPORTS);
        sportsService.save(itemMapper.itemToItemDto(toAdd1));
        TablePrinter.print(sportsService.getAllItems());

        TestUtils.printTestInfo("sports service - update item");
        sportsService.updateItem(5L, itemMapper.itemToItemDto(toAdd1));
        TablePrinter.print(sportsService.getAllItems());

        TestUtils.printTestInfo("sports service - count items");
        System.out.println(sportsService.countItems());

        TestUtils.printTestInfo("sports service - update non existing item");
        try {
            sportsService.updateItem(200L, itemMapper.itemToItemDto(toAdd1));
        } catch (Exception e){
            System.err.println("update non existing item");
        }

        TestUtils.printTestInfo("sports service - update non SPORTS item");
        try {
            toAdd1.setItemType(ItemType.FOOD);
            sportsService.updateItem(2L, itemMapper.itemToItemDto(toAdd1));
        } catch (Exception e){
            System.err.println("update non SPORTS item");
        }

        TestUtils.printTestInfo("admin service - get non existing item");
        try {
            sportsService.getOneItem(300L);
        } catch (Exception e){
            System.err.println("get non existing item");
        }

        TestUtils.printTestInfo("sports service - save non SPORTS item");
        try {
            toAdd1.setItemType(ItemType.FOOD);
            sportsService.save(itemMapper.itemToItemDto(toAdd1));
        } catch (Exception e){
            System.err.println("save non SPORTS item");
        }

    }
}
