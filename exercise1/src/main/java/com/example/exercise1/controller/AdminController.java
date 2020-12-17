package com.example.exercise1.controller;

import com.example.exercise1.dto.ItemDto;
import com.example.exercise1.exception.InvalidEntityException;
import com.example.exercise1.exception.InvalidOperationException;
import com.example.exercise1.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @SneakyThrows
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@RequestBody @Validated ItemDto itemDto){
        adminService.save(itemDto);
    }

    @SneakyThrows
    @PutMapping("item/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateItem(@PathVariable("itemId") Long itemId, @RequestBody @Validated ItemDto itemDto){
        adminService.updateItem(itemId,itemDto);
    }

    @SneakyThrows
    @DeleteMapping("item")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@RequestBody @Validated ItemDto itemDto){
        adminService.deleteItem(itemDto);
    }

    @SneakyThrows
    @GetMapping("item/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getItemById(@PathVariable Long itemId){
        return ResponseEntity.ok(adminService.getOneItem(itemId));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllItems(){
        return ResponseEntity.ok(adminService.getAllItems());
    }

    @GetMapping("count")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> countItems(){
        return ResponseEntity.ok(adminService.getAllItems());
    }
}
