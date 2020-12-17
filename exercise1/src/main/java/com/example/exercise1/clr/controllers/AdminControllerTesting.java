package com.example.exercise1.clr.controllers;

import com.example.exercise1.beans.Item;
import com.example.exercise1.clr.bootstrap.ItemFactory;
import com.example.exercise1.dto.ItemDto;
import com.example.exercise1.mapper.ItemMapper;
import com.example.exercise1.util.AppArtUtils;
import com.example.exercise1.util.ArtUtils;
import com.example.exercise1.util.TablePrinter;
import com.example.exercise1.util.TestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Order(5)
@RequiredArgsConstructor
public class AdminControllerTesting implements CommandLineRunner {

    private  final RestTemplate restTemplate;
    private final String URL = "http://localhost:8080/admin";
    private final ItemMapper itemMapper;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(ArtUtils.CONTROLLER_TESTING);
        System.out.println(AppArtUtils.ADMIN);

        ItemDto toAdd = itemMapper.itemToItemDto(ItemFactory.generate());
//        toAdd.setId(15L);

        TestUtils.printTestInfo("admin controller - get all");
        String res = restTemplate.getForObject(URL, String.class);
        System.out.println(res);

        TestUtils.printTestInfo("admin controller - save");
        HttpEntity<ItemDto> request = new HttpEntity<>(toAdd);
        ResponseEntity<ItemDto> resToDelete = restTemplate.exchange(URL, HttpMethod.POST,request, ItemDto.class);
        ItemDto itemToDelete = resToDelete.getBody();

//        res = restTemplate.postForObject(URL, toAdd, String.class);
//        System.out.println(res);

        TestUtils.printTestInfo("admin controller - update");
        restTemplate.put(URL+"/item/2", toAdd);

        TestUtils.printTestInfo("admin controller - delete");
        toAdd.setId(1L);
        request = new HttpEntity<>(toAdd);
//        restTemplate.exchange(URL+"/item", HttpMethod.DELETE,request, String.class);

        TestUtils.printTestInfo("admin controller - get one item");
        res = restTemplate.getForObject(URL+"/item/5", String.class);
        System.out.println(res);

        TestUtils.printTestInfo("admin controller - count item");
        res = restTemplate.getForObject(URL+"/count", String.class);
        System.out.println(res);

        System.out.println("end of admin controller test");

    }
}
