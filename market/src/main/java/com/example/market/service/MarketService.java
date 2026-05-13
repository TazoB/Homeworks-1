package com.example.market.service;

import com.example.market.model.Item;
import com.example.market.model.ItemRequestDTO;
import com.example.market.model.ItemResponseDTO;
import com.example.market.repository.MarketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarketService {
    private final MarketRepository marketRepository;

    public MarketService(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    public List<ItemResponseDTO> findAll() {
        List<Item> items = marketRepository.findAll();
        List<ItemResponseDTO> itemResponseDTOS = new ArrayList<>();

        for (Item item : items) {
            itemResponseDTOS.add(createItemResponseDTO(item));
        }
        return itemResponseDTOS;
    }

    public ItemResponseDTO saveItem(ItemRequestDTO itemRequest) {
        Item item = new Item(
                itemRequest.getName(),
                itemRequest.getPrice(),
                itemRequest.getDescription(),
                LocalDateTime.now(),
                itemRequest.getPhotoUrl()
        );

        Item savedItem = marketRepository.save(item);
        return createItemResponseDTO(savedItem);
    }

    public ItemResponseDTO updateItem(int id, ItemRequestDTO itemRequest) {
        Item item = marketRepository.findById(id).orElseThrow();
        item.setName(itemRequest.getName());
        item.setPrice(itemRequest.getPrice());
        item.setDescription(itemRequest.getDescription());
        item.setPhotoUrl(item.getPhotoUrl());

        Item savedItem = marketRepository.save(item);
        return createItemResponseDTO(savedItem);
    }

    public ItemResponseDTO deleteItem(int id) {
        Item item = marketRepository.findById(id).orElseThrow();
        marketRepository.deleteById(id);
        return createItemResponseDTO(item);
    }

    private ItemResponseDTO createItemResponseDTO(Item item) {
        return new ItemResponseDTO(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getDescription(),
                item.getPhotoUrl(),
                item.getSubmissionTime()
        );
    }
}
