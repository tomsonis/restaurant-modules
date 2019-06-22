package com.beben.tomasz.restaurant.core.application.command;

import com.beben.tomasz.restaurant.core.api.command.AddTableCommand;
import com.beben.tomasz.restaurant.core.application.factory.TestRestaurantTableFactory;
import com.beben.tomasz.restaurant.core.domain.RestaurantNotExistException;
import com.beben.tomasz.restaurant.core.domain.RestaurantTable;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableFactory;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableRepository;
import org.mockito.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AddTableCommandHandlerTest {

    @Mock
    private RestaurantTableRepository restaurantTableRepository;

    @Mock
    private RestaurantTableFactory restaurantTableFactory;

    @InjectMocks
    private AddTableCommandHandler addTableCommandHandler;

    @Captor
    private ArgumentCaptor<RestaurantTable> restaurantTableArgumentCaptor;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddTable() throws RestaurantNotExistException {

        //        given
        AddTableCommand tableCommand = AddTableCommand.of(
                TestRestaurantTableFactory.TEST_NAME,
                TestRestaurantTableFactory.TEST_POSITION,
                TestRestaurantTableFactory.TEST_POSITION,
                TestRestaurantTableFactory.TEST_RESTAURANT_REFERENCE,
                TestRestaurantTableFactory.TEST_CAPACITY
        );

        RestaurantTable testRestaurantTable = TestRestaurantTableFactory.createTable();

        //        when
        when(restaurantTableFactory.createTable(anyString(), anyString(), anyString(), anyInt()))
                .thenReturn(testRestaurantTable);

        String tableId = addTableCommandHandler.handle(tableCommand);

        //        then
        assertThat(tableId).isNotBlank();
        assertThat(tableId).isEqualTo(TestRestaurantTableFactory.TEST_ID);

        verify(restaurantTableRepository, times(1)).save(restaurantTableArgumentCaptor.capture());
        verifyNoMoreInteractions(restaurantTableRepository);

        RestaurantTable tableArgumentCaptorValue = restaurantTableArgumentCaptor.getValue();
        assertThat(tableArgumentCaptorValue.getId()).isEqualTo(testRestaurantTable.getId());
        assertThat(tableArgumentCaptorValue.getName()).isEqualTo(testRestaurantTable.getName());
        assertThat(tableArgumentCaptorValue.getPosition()).isEqualTo(testRestaurantTable.getPosition());
        assertThat(tableArgumentCaptorValue.getRestaurantReference()).isEqualTo(testRestaurantTable.getRestaurantReference());
        assertThat(tableArgumentCaptorValue.getCapacity()).isEqualTo(testRestaurantTable.getCapacity());
    }
}