package com.example.demo1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DatabaseService {
    private static final Map<Integer, Item> database = new HashMap<>();

    public static void addItem(Item item) {
        database.put(item.getId(), item);
    }

    public static Collection<Item> getAllItems() {
        return database.values();
    }

    public static Item getItem(int id) {
        return database.get(id);
    }

    public static void updateItem(int id, Double price, Integer quantity) {
        Item item = database.get(id);
        if (item != null) {
            if (price != null) item.setPrice(price);
            if (quantity != null) item.setQuantity(quantity);
        }
    }

    public static void deleteItem(int id) {
        database.remove(id);
    }
}
