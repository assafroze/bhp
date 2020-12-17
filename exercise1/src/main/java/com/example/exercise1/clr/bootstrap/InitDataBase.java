package com.example.exercise1.clr.bootstrap;

import com.example.exercise1.beans.Item;
import com.example.exercise1.beans.ItemType;
import com.example.exercise1.repo.ItemRepository;
import com.example.exercise1.util.ArtUtils;
import com.example.exercise1.util.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Order(1)
@RequiredArgsConstructor
public class InitDataBase implements CommandLineRunner {

    private final ItemRepository itemRepository;
    @Override
    public void run(String... args) throws Exception {

        System.out.println(ArtUtils.BOOTSTRAP);

        Item item1 = Item.builder()
                        .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                        .itemType(ItemType.SPORTS)
                        .name("basketball")
                        .price(BigDecimal.valueOf(50.43))
                        .build();

        Item item2 = Item.builder()
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .itemType(ItemType.SPORTS)
                .name("football")
                .price(BigDecimal.valueOf(22.09))
                .build();

        Item item3 = Item.builder()
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .itemType(ItemType.SPORTS)
                .name("tennis ball")
                .price(BigDecimal.valueOf(5.6))
                .build();

        Item item4 = Item.builder()
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .itemType(ItemType.SPORTS)
                .name("running shoes")
                .price(BigDecimal.valueOf(110.99))
                .build();

        Item item5 = Item.builder()
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .itemType(ItemType.SPORTS)
                .name("golf ball")
                .price(BigDecimal.valueOf(9.99))
                .build();

        Item item6 = Item.builder()
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .itemType(ItemType.ELECTRICITY)
                .name("tv")
                .price(BigDecimal.valueOf(1100.95))
                .build();

        Item item7 = Item.builder()
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .itemType(ItemType.ELECTRICITY)
                .name("cell phone")
                .price(BigDecimal.valueOf(800.95))
                .build();

        Item item8 = Item.builder()
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .itemType(ItemType.ELECTRICITY)
                .name("refrigerator")
                .price(BigDecimal.valueOf(2000.00))
                .build();

        Item item9 = Item.builder()
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .itemType(ItemType.ELECTRICITY)
                .name("toaster")
                .price(BigDecimal.valueOf(22.22))
                .build();


        Item item10 = Item.builder()
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .itemType(ItemType.ELECTRICITY)
                .name("speakers")
                .price(BigDecimal.valueOf(100))
                .build();

        itemRepository.saveAll(Arrays.asList(item1,item2,item3,item4,item5,item6,item7,item8,item9,item10));

        TablePrinter.print(itemRepository.findAll());

    }
}
