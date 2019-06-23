package com.beben.tomasz.restaurant.products.application.query.list;

import com.beben.tomasz.restaurant.products.api.query.SearchProductsGroupedByCategoriesQuery;
import com.beben.tomasz.restaurant.products.api.view.CategoryProductsView;
import com.beben.tomasz.restaurant.products.api.view.CategoryView;
import com.beben.tomasz.restaurant.products.api.view.ProductView;
import com.beben.tomasz.restaurant.products.application.converter.AllergenViewConverter;
import com.beben.tomasz.restaurant.products.application.converter.CategoryProductsViewConverter;
import com.beben.tomasz.restaurant.products.application.converter.CategoryViewConverter;
import com.beben.tomasz.restaurant.products.application.converter.IngredientViewConverter;
import com.beben.tomasz.restaurant.products.application.converter.ProductViewConverter;
import com.beben.tomasz.restaurant.products.application.model.TestCategory;
import com.beben.tomasz.restaurant.products.application.model.TestProduct;
import com.beben.tomasz.restaurant.products.application.query.ProductsReadRepository;
import com.beben.tomasz.restaurant.products.application.query.SearchProductsGroupedByCategoriesQueryHandler;
import com.beben.tomasz.restaurant.products.domain.Category;
import com.beben.tomasz.restaurant.products.domain.Product;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class SearchProductsGroupedByCategoriesQueryHandlerTest {

    private static final String TEST_RESTAURANT_ID = "TEST_RESTAURANT_ID";

    private AllergenViewConverter allergenViewConverter = new AllergenViewConverter();

    private IngredientViewConverter ingredientViewConverter = new IngredientViewConverter();

    private ProductViewConverter productViewConverter = new ProductViewConverter(
            allergenViewConverter,
            ingredientViewConverter
    );

    private CategoryViewConverter categoryViewConverter = new CategoryViewConverter();

    private CategoryProductsViewConverter categoryProductsViewConverter = Mockito.spy(
            new CategoryProductsViewConverter(
                    categoryViewConverter,
                    productViewConverter
            )
    );

    @Mock
    private ProductsReadRepository productsReadRepository;

    @InjectMocks
    private SearchProductsGroupedByCategoriesQueryHandler searchProductsGroupedByCategoriesQueryHandler;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHandle() {

        //given
        SearchProductsGroupedByCategoriesQuery searchProductsGroupedByCategoriesQuery = SearchProductsGroupedByCategoriesQuery.of(
                TEST_RESTAURANT_ID
        );

        Category testCategory = new TestCategory();
        Product testProduct = new TestProduct();

        HashMap<Category, List<Product>> categoryListHashMap = new HashMap<Category, List<Product>>() {{
            put(testCategory, Collections.singletonList(testProduct));
        }};

        when(productsReadRepository.findProductsGroupedByCategory(TEST_RESTAURANT_ID))
                .thenReturn(categoryListHashMap);

        //when
        List<CategoryProductsView> categoryProductsViews = searchProductsGroupedByCategoriesQueryHandler.handle(
                searchProductsGroupedByCategoriesQuery
        );

        //then
        assertThat(categoryProductsViews).isNotNull();
        assertThat(categoryProductsViews).hasSizeGreaterThan(0);

        CategoryProductsView categoryProductsView = categoryProductsViews.get(0);

        CategoryView category = categoryProductsView.getCategory();
        assertThat(category.getId()).isEqualTo(testCategory.getId());
        assertThat(category.getName()).isEqualTo(testCategory.getName());

        List<ProductView> products = categoryProductsView.getProducts();
        assertThat(products).isNotNull();
        assertThat(products).hasSizeGreaterThan(0);

        ProductView productView = products.get(0);
        assertThat(productView.getId()).isEqualTo(testProduct.getId());
        assertThat(productView.getName()).isEqualTo(testProduct.getName());
    }
}