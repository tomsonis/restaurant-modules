package com.beben.tomasz.restaurant.products.infrastructure.spring.persistance.write.volume;

import com.beben.tomasz.restaurant.products.domain.UnitEnum;
import com.beben.tomasz.restaurant.products.domain.Volume;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@Table
@Entity
@NoArgsConstructor(staticName = "newInstance")
@AllArgsConstructor(staticName = "of")
public class VolumeEntity implements Volume {

    @Id
    private String id = UUID.randomUUID().toString();

    private int capacity;

    @Enumerated(EnumType.STRING)
    private UnitEnum unitEnum;
}
