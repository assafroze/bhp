package com.example.exercise1.clr.controllers;

import com.example.exercise1.beans.ItemType;
import com.example.exercise1.clr.bootstrap.ItemFactory;
import com.example.exercise1.dto.ItemDto;
import com.example.exercise1.mapper.ItemMapper;
import com.example.exercise1.util.AppArtUtils;
import com.example.exercise1.util.ArtUtils;
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
@Order(6)
@RequiredArgsConstructor
public class SportsControllerTesting implements CommandLineRunner {
    private final RestTemplate restTemplate;
    private final String URL = "http://localhost:8080/sports";
    private final ItemMapper itemMapper;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(ArtUtils.CONTROLLER_TESTING);
        System.out.println(AppArtUtils.SPORT);

        ItemDto toAdd = itemMapper.itemToItemDto(ItemFactory.generate());
        toAdd.setItemType(ItemType.SPORTS);

        TestUtils.printTestInfo("sports controller - get all");
        String res = restTemplate.getForObject(URL, String.class);
        System.out.println(res);

        TestUtils.printTestInfo("sports controller - save");
        HttpEntity<ItemDto> request = new HttpEntity<>(toAdd);
        ResponseEntity<ItemDto> resToDelete = restTemplate.exchange(URL, HttpMethod.POST, request, ItemDto.class);

//        res = restTemplate.postForObject(URL, toAdd, String.class);
//        System.out.println(res);

        TestUtils.printTestInfo("sports controller - update");
        try {
            restTemplate.put(URL + "/item/2", toAdd);
        } catch (Exception e) {
            System.out.println("service exception not tested here");
        }

        TestUtils.printTestInfo("sports controller - get one item");
        try {
            res = restTemplate.getForObject(URL + "/item/5", String.class);
            System.out.println(res);
        } catch (Exception e) {
            System.out.println("service exception not tested here");
        }

        TestUtils.printTestInfo("sports controller - count item");
        res = restTemplate.getForObject(URL + "/count", String.class);
        System.out.println(res);

        System.out.println("end of sports controller test");
    }
}