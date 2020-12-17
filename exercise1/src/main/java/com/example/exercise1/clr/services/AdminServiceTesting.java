package com.example.exercise1.clr.services;

import com.example.exercise1.beans.Item;
import com.example.exercise1.beans.ItemType;
import com.example.exercise1.clr.bootstrap.ItemFactory;
import com.example.exercise1.mapper.ItemMapper;
import com.example.exercise1.service.AdminServiceImpl;
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
@Order(2)
@RequiredArgsConstructor
public class AdminServiceTesting implements CommandLineRunner {

    private final ItemMapper itemMapper;
    private final AdminServiceImpl adminService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(ArtUtils.ROUND_2_SERVICE_TESTING);

        TestUtils.printTestInfo("admin service - get all items");
        TablePrinter.print(adminService.getAllItems());

        TestUtils.printTestInfo("admin service - add item");
        Item toAdd1 = ItemFactory.generate();
        adminService.save(itemMapper.itemToItemDto(toAdd1));
        TablePrinter.print(adminService.getAllItems());

        TestUtils.printTestInfo("admin service - update item");
        adminService.updateItem(2L, itemMapper.itemToItemDto(toAdd1));
        TablePrinter.print(adminService.getAllItems());

        TestUtils.printTestInfo("admin service - count items");
        System.out.println(adminService.countItems());

        TestUtils.printTestInfo("admin service - delete item");
        toAdd1.setId(1L);
        adminService.deleteItem(itemMapper.itemToItemDto(toAdd1));
        TablePrinter.print(adminService.getAllItems());

        TestUtils.printTestInfo("admin service - update non existing item");
        try {
            adminService.updateItem(200L, itemMapper.itemToItemDto(toAdd1));
        } catch (Exception e){
            System.err.println("update non existing item");
        }

        TestUtils.printTestInfo("admin service - get non existing item");
        try {
            adminService.getOneItem(300L);
        } catch (Exception e){
            System.err.println("get non existing item");
        }

        TestUtils.printTestInfo("admin service - delete non existing item");
        Item toDelete = Item.builder()
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .itemType(ItemType.SPORTS)
                .name("running shoes")
                .price(BigDecimal.valueOf(110.99))
                .id(9999L)
                .build();

        try {
            adminService.deleteItem(itemMapper.itemToItemDto(toDelete));
        } catch (Exception e){
            System.err.println("delete non existing item");
        }
    }
}
