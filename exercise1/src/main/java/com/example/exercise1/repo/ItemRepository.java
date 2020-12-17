package com.example.exercise1.repo;

import com.example.exercise1.beans.Item;
import com.example.exercise1.beans.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "SELECT count(*) FROM items WHERE item_type = :item_type ",nativeQuery = true)
    int countItemByType(@Param("item_type") String item_type);

    List<Item> findByItemType(ItemType itemType);

    Item findFirstByOrderByIdDesc();

    @Query(value = "SELECT * FROM items WHERE item_type = :item_type LIMIT 1",nativeQuery = true)
    Item findFirstByItemType(@Param("item_type") String item_type);
}
