package com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.allergen;

import com.beben.tomasz.restaurant.products.domain.Allergen;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@ToString
@EqualsAndHashCode
@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class AllergenEntity implements Allergen {

    @Id
    private String id;

    private String name;

    private String description;

    private String restaurantReference;
}
