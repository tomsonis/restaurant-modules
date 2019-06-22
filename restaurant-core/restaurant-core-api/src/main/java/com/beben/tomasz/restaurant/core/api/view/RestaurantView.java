package com.beben.tomasz.restaurant.core.api.view;

import com.beben.tomasz.restaurant.commons.view.AddressView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(staticName = "newInstance")
@AllArgsConstructor(staticName = "of")
public class RestaurantView implements Serializable {

    private String id;

    private String name;

    private AddressView address;

    private String photoUrl;
}
