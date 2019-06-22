package com.beben.tomasz.restaurant.products.application.command;

import com.beben.tomasz.cqrs.api.command.CommandHandler;
import com.beben.tomasz.restaurant.products.api.command.AddProductCommand;
import com.beben.tomasz.restaurant.products.domain.Product;
import com.beben.tomasz.restaurant.products.domain.ProductFactory;
import com.beben.tomasz.restaurant.products.domain.ProductRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddProductCommandHandler implements CommandHandler<AddProductCommand, String> {

    private ProductRepository productRepository;

    private ProductFactory productFactory;

    @Override
    public String handle(AddProductCommand addProductCommand) {

        Product product = productFactory.createProduct(
                addProductCommand.getName(),
                addProductCommand.getDescription(),
                addProductCommand.getIngredientIds(),
                addProductCommand.getAllergenIds(),
                addProductCommand.getVolumeId(),
                addProductCommand.getPrice(),
                addProductCommand.getPhotoUrl(),
                addProductCommand.isAvailable(),
                addProductCommand.getCategoryId(),
                addProductCommand.getRestaurantReference()
        );

        productRepository.save(product);

        return product.getId();
    }
}
