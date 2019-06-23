package com.beben.tomasz.restaurant.products.api.view;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class CategoryView implements Serializable {

    private String id;

    private String name;

    private int orderOnList;

    private String restaurantReference;
}
