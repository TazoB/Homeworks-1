package com.example.market.controller;

import com.example.market.model.ItemRequestDTO;
import com.example.market.model.ItemResponseDTO;
import com.example.market.service.MarketService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/market")
public class MarketController {
    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> findAll() {
        return ResponseEntity
                .ok()
                .body(marketService.findAll());
    }

    @GetMapping("/{page}")
    public ResponseEntity<List<ItemResponseDTO>> findAllByPageNumber(@PathVariable int page) {
        Pageable firstSixItems = PageRequest.of(page-1, 6);
        return ResponseEntity
                .ok()
                .body(marketService.findAll(firstSixItems));
    }

    @PostMapping
    public ResponseEntity<ItemResponseDTO> saveItem(@RequestBody ItemRequestDTO item) {
        return ResponseEntity
                .ok()
                .body(marketService.saveItem(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> updateItem(@PathVariable int id, @RequestBody ItemRequestDTO item) {
        return ResponseEntity
                .ok()
                .body(marketService.updateItem(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> deleteItem(@PathVariable int id) {
        return ResponseEntity
                .ok()
                .body(marketService.deleteItem(id));
    }
}
