package com.example.exercise1.controller;

import com.example.exercise1.beans.Item;
import com.example.exercise1.dto.ItemDto;
import com.example.exercise1.exception.InvalidEntityException;
import com.example.exercise1.exception.InvalidOperationException;
import com.example.exercise1.service.ElectricityService;
import com.example.exercise1.service.SportsService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/electricity")
@RequiredArgsConstructor
public class ElectricityController {

    private final ElectricityService electricityService;

    @SneakyThrows
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@RequestBody ItemDto item){
        electricityService.save(item);
    }

    @SneakyThrows
    @PutMapping("item/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateItem(@PathVariable("itemId") Long itemId, @RequestBody @Validated ItemDto itemDto){
            electricityService.updateItem(itemId, itemDto);
    }

    @SneakyThrows
    @GetMapping("count")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> countItems(){
        return ResponseEntity.ok(electricityService.countItems());
    }

    @SneakyThrows
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllElectricityItems(){
        return ResponseEntity.ok(electricityService.getAllItems());
    }

    @SneakyThrows
    @GetMapping("item/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getItemById(@PathVariable Long itemId){
        return ResponseEntity.ok(electricityService.getOneItem(itemId));
    }
}
