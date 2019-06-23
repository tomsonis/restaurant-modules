package com.beben.tomasz.restaurant.products.application.command;


import com.beben.tomasz.restaurant.products.api.command.AddProductCommand;
import com.beben.tomasz.restaurant.products.application.model.TestAllergen;
import com.beben.tomasz.restaurant.products.application.model.TestCategory;
import com.beben.tomasz.restaurant.products.application.model.TestIngredient;
import com.beben.tomasz.restaurant.products.application.model.TestProduct;
import com.beben.tomasz.restaurant.products.application.model.TestVolume;
import com.beben.tomasz.restaurant.products.domain.Product;
import com.beben.tomasz.restaurant.products.domain.ProductFactory;
import com.beben.tomasz.restaurant.products.domain.ProductRepository;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class AddProductCommandHandlerTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductFactory productFactory;

    @InjectMocks
    private AddProductCommandHandler addProductCommandHandler;

    @Captor
    private ArgumentCaptor<Product> productArgumentCaptor;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHandle() {

        //given
        AddProductCommand addProductCommand = AddProductCommand.of(
                TestProduct.TEST_PROD_NAME,
                TestProduct.TEST_PROD_DESC,
                Collections.singletonList(TestIngredient.TEST_NAME),
                Collections.singletonList(TestAllergen.TEST_NAME),
                TestVolume.TEST_ID,
                TestProduct.TEST_PRICE,
                TestProduct.TEST_PHOTO_URL,
                TestProduct.TEST_AVAILABLE,
                TestCategory.TEST_ID,
                TestProduct.TEST_REST_REF
        );
        Product product = new TestProduct();

        //when
        when(productFactory.createProduct(
                anyString(), anyString(), anyCollectionOf(String.class), anyCollectionOf(String.class), anyString(),
                any(), anyString(), anyBoolean(), anyString(), anyString()
        ))
                .thenReturn(product);

        String productId = addProductCommandHandler.handle(addProductCommand);

        assertThat(productId).isNotBlank();

        verify(productRepository).save(productArgumentCaptor.capture());
        verifyNoMoreInteractions(productRepository);

        Product argumentCaptorValue = productArgumentCaptor.getValue();
        assertThat(argumentCaptorValue).isNotNull();
        assertThat(argumentCaptorValue.getId()).isEqualTo(productId);
        assertThat(argumentCaptorValue.getId()).isEqualTo(product.getId());

        assertThat(argumentCaptorValue.getName()).isEqualTo(product.getName());
        assertThat(argumentCaptorValue.getDescription()).isEqualTo(product.getDescription());
        assertThat(argumentCaptorValue.getPhotoUrl()).isEqualTo(product.getPhotoUrl());
        assertThat(argumentCaptorValue.getRestaurantReference()).isEqualTo(product.getRestaurantReference());
    }
}