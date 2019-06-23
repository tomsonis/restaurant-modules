package com.beben.tomasz.restaurant.products.api.query;

import com.beben.tomasz.cqrs.api.query.Query;
import com.beben.tomasz.restaurant.products.api.view.ProductView;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class SearchProductByIdsQuery implements Query<List<ProductView>> {

    @Size(min = 1)
    private List<String> productIds;
}
