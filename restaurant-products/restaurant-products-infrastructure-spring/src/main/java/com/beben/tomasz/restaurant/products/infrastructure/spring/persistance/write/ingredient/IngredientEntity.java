package com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.ingredient;


import com.beben.tomasz.restaurant.products.domain.Ingredient;
import com.beben.tomasz.restaurant.products.domain.Volume;
import com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.volume.VolumeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@ToString
@EqualsAndHashCode
@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class IngredientEntity implements Ingredient {

    @Id
    private String id;

    private String name;

    private String restaurantReference;

    @ManyToOne
    @JoinColumn(name = "volume_id")
    private VolumeEntity volumeEntity;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getRestaurantReference() {
        return restaurantReference;
    }

    @Override
    public Volume getVolume() {
        return volumeEntity;
    }
}
