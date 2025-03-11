package com.infy.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.infy.product.dto.InventoryDto;
import com.infy.product.dto.ProductDto;
import com.infy.product.entity.Inventory;
import com.infy.product.entity.Product;
import com.infy.product.exception.ProductException;
import com.infy.product.repository.InventoryRepository;
import com.infy.product.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository productRepository;
	
	private InventoryRepository inventoryRepository;
	
	private ModelMapper modelMapper;
	
	public ProductServiceImpl(ProductRepository productRepository, InventoryRepository inventoryRepository, ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.inventoryRepository = inventoryRepository;
		this.modelMapper=modelMapper;
	}

	@Override
	public ProductDto isProductAvailable(Long productId, Integer quantityRequired) throws ProductException {
		Optional<Product> productOpt = productRepository.findById(productId);
		if(productOpt.isEmpty()) {
			throw new ProductException("No Product found with given Id");
		}
		
		Optional<Inventory> inventOpt = inventoryRepository.findByProductId(productId);
		
		if(inventOpt.isEmpty() || inventOpt.get().getQuantityInStock() < quantityRequired) {
			throw new ProductException("Out of stock");
		}
		
		return ProductDto.convertToDto(productOpt.get());
		
	}

	@Transactional
	public String modifyProductQuantity(Long productId, Integer quantity, String mode) throws ProductException {
		Optional<Inventory> inventOpt = inventoryRepository.findByProductId(productId);
		Inventory inventory = inventOpt.orElseThrow(()-> new ProductException("No inventory with given ID"));
		
		switch(mode) {
			case "DEC" -> {
				inventory.setQuantityInStock(inventory.getQuantityInStock() - quantity);
				inventory.setQuantitySold(inventory.getQuantitySold() + quantity);

			}
		}
		
		inventoryRepository.save(inventory);
		return "success";
	}
	public String addProduct(ProductDto productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product savedProduct = productRepository.save(product);
        Inventory inventory = new Inventory();
        inventory.setProductId(savedProduct.getId());
        inventory.setQuantityInStock(0);
        inventory.setQuantitySold(0);
        inventoryRepository.save(inventory);
        modelMapper.map(savedProduct, ProductDto.class);
        return "Product Added Successfully";
    }
	
	
	public ProductDto getProduct(Long productId) throws ProductException{
        Optional<Product> productOpt = productRepository.findById(productId);
        if (!productOpt.isPresent()) {
            throw new ProductException("Product not found");
        }
        return modelMapper.map(productOpt.get(), ProductDto.class);
    }
	
	public List<ProductDto> getAllProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();
        return products.stream()
                       .map(product -> modelMapper.map(product, ProductDto.class))
                       .collect(Collectors.toList());
    }
	
	public InventoryDto getInventory(Long productId) throws ProductException{
        Optional<Product> productOpt = productRepository.findById(productId);
        if (!productOpt.isPresent()) {
            throw new ProductException("Product not found");
        }
        Inventory inventory = inventoryRepository.findByProductId(productOpt.get().getId()).get();
        if (inventory == null) {
            throw new ProductException("Inventory not found for product");
        }
        InventoryDto dto = new InventoryDto();
        dto.setId(inventory.getId());
        dto.setProductDto(modelMapper.map(productOpt.get(), ProductDto.class));
        dto.setQuantityInStock(inventory.getQuantityInStock());
        dto.setQuantitySold(inventory.getQuantitySold());
        dto.setLastReplenishmentDate(inventory.getLastReplenishmentDate());
        return dto;
    }
	
	@Transactional
    public InventoryDto updateInventory(Long productId, InventoryDto updateDTO) throws ProductException {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (!productOpt.isPresent()) {
            throw new ProductException("Product not found");
        }
        Inventory inventory = inventoryRepository.findByProductId(productOpt.get().getId()).get();
        if (inventory == null) {
            throw new ProductException("Inventory not found for product");
        }
        inventory.setQuantityInStock(updateDTO.getQuantityInStock());
        inventory.setQuantitySold(updateDTO.getQuantitySold());
        inventoryRepository.save(inventory);

        InventoryDto dto = new InventoryDto();
        dto.setId(inventory.getId());
        dto.setProductDto(modelMapper.map(productOpt.get(), ProductDto.class));
        dto.setQuantityInStock(inventory.getQuantityInStock());
        dto.setQuantitySold(inventory.getQuantitySold());
        return dto;
    }
	
}
