package com.beben.tomasz.restaurant.products.application.query;

import com.beben.tomasz.cqrs.api.query.QueryHandler;
import com.beben.tomasz.restaurant.products.api.query.SearchProductByIdsQuery;
import com.beben.tomasz.restaurant.products.api.view.ProductView;
import com.beben.tomasz.restaurant.products.application.converter.ProductViewConverter;
import com.beben.tomasz.restaurant.products.domain.Product;
import lombok.AllArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
public class SearchProductByIdsQueryHandler implements QueryHandler<SearchProductByIdsQuery, List<ProductView>> {

    private ProductsReadRepository productsReadRepository;

    private ProductViewConverter productViewConverter;

    @Override
    public List<ProductView> handle(@Valid SearchProductByIdsQuery searchProductByIdsQuery) {
        List<Product> products = productsReadRepository.findByIds(searchProductByIdsQuery.getProductIds());

        return productViewConverter.convert(products);
    }
}
