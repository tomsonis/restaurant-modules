package com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.category;

import com.beben.tomasz.restaurant.products.domain.Category;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@Table
@Entity
@NoArgsConstructor(staticName = "newInstance")
@AllArgsConstructor(staticName = "of")
public class CategoryEntity implements Category {

    @Id
    private String id;

    private String name;

    private int orderOnList;

    private String restaurantReference;
}
