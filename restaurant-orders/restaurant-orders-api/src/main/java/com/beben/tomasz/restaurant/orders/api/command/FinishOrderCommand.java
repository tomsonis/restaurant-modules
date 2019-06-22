package com.beben.tomasz.restaurant.orders.api.command;

import com.beben.tomasz.cqrs.api.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class FinishOrderCommand implements Command<Void> {

    @NotEmpty
    private String orderId;
}
