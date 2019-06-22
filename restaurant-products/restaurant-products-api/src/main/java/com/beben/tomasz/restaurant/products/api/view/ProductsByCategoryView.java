package com.beben.tomasz.restaurant.products.api.view;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class ProductsByCategoryView {

    private String id;

    private String name;

    private String description;

    private List<AllergenView> allergenViews;

    private CategoryView categoryView;

    public ProductsByCategoryView(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
