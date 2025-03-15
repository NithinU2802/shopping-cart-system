package com.infy.product.controller;


import com.infy.product.dto.InventoryReplenishmentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.infy.product.dto.InventoryDto;
import com.infy.product.exception.ProductException;
import com.infy.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final ProductService productService;

    public InventoryController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<InventoryDto> getInventory(@PathVariable Long productId) throws ProductException {
        InventoryDto inventoryDTO = productService.getInventory(productId);
        return new ResponseEntity<>(inventoryDTO, HttpStatus.OK);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<InventoryDto> updateInventory(@PathVariable Long productId,
                                                        @RequestBody InventoryDto updateInventoryDTO) throws ProductException {
        InventoryDto updatedInventory = productService.updateInventory(productId, updateInventoryDTO);
        return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
    }

    @GetMapping("/replenish")
    public  ResponseEntity<List<InventoryReplenishmentDTO>> getProductsToBeReplenished() throws ProductException {
        return new ResponseEntity<>(productService.getProductsToBeReplenished(), HttpStatus.OK);
    }
}
