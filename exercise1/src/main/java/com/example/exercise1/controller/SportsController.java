package com.example.exercise1.controller;

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
@RequestMapping("/sports")
@RequiredArgsConstructor
public class SportsController {
    private final SportsService sportsService;

    @SneakyThrows
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@RequestBody ItemDto item){
            sportsService.save(item);
    }

    @SneakyThrows
    @PutMapping("item/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateItem(@PathVariable("itemId") Long itemId, @RequestBody @Validated ItemDto itemDto){
        sportsService.updateItem(itemId, itemDto);
    }

    @SneakyThrows
    @GetMapping("count")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> countItems(){

        return ResponseEntity.ok(sportsService.countItems());
    }

    @SneakyThrows
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllElectricityItems(){
        return ResponseEntity.ok(sportsService.getAllItems());
    }

    @SneakyThrows
    @GetMapping("item/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getItemById(@PathVariable Long itemId){
        return ResponseEntity.ok(sportsService.getOneItem(itemId));
    }
}
