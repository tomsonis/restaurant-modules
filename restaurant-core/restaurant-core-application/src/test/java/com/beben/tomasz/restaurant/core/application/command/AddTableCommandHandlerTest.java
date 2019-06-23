package com.beben.tomasz.restaurant.core.application.command;

import com.beben.tomasz.restaurant.core.api.command.AddTableCommand;
import com.beben.tomasz.restaurant.core.application.factory.TestRestaurantTableFactory;
import com.beben.tomasz.restaurant.core.domain.RestaurantId;
import com.beben.tomasz.restaurant.core.domain.RestaurantNotExistException;
import com.beben.tomasz.restaurant.core.domain.RestaurantTable;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableFactory;
import com.beben.tomasz.restaurant.core.domain.RestaurantTableRepository;
import com.beben.tomasz.restaurant.core.domain.TableId;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

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
        when(restaurantTableFactory.createTable(anyString(), anyString(), any(RestaurantId.class), anyInt()))
                .thenReturn(testRestaurantTable);

        TableId tableId = addTableCommandHandler.handle(tableCommand);

        //        then
        assertThat(tableId).isNotNull();
        assertThat(tableId).isEqualTo(TestRestaurantTableFactory.TEST_ID);
        assertThat(tableId.getId()).isNotBlank();

        verify(restaurantTableRepository, times(1)).save(restaurantTableArgumentCaptor.capture());
        verifyNoMoreInteractions(restaurantTableRepository);

        RestaurantTable tableArgumentCaptorValue = restaurantTableArgumentCaptor.getValue();
        assertThat(tableArgumentCaptorValue.getTableId()).isEqualTo(testRestaurantTable.getTableId());
        assertThat(tableArgumentCaptorValue.getName()).isEqualTo(testRestaurantTable.getName());
        assertThat(tableArgumentCaptorValue.getPosition()).isEqualTo(testRestaurantTable.getPosition());
        assertThat(tableArgumentCaptorValue.getRestaurantReference()).isEqualTo(testRestaurantTable.getRestaurantReference());
        assertThat(tableArgumentCaptorValue.getCapacity()).isEqualTo(testRestaurantTable.getCapacity());
    }
}