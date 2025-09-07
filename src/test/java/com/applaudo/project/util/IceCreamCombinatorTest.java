package com.applaudo.project.util;

import org.junit.jupiter.api.Test;

import com.applaudo.project.model.IceCreamDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

/**
 * Its fast and better do test here to validate your logic
 */
class IceCreamCombinatorTest {

    private static final IceCreamDto vanillaIceCream = IceCreamDto.builder().id(1L).name("Vanilla").cost(new BigDecimal("2.00")).build();
    private static final IceCreamDto chocolateIceCream = IceCreamDto.builder().id(2L).name("Chocolate").cost(new BigDecimal("5.00")).build();
    private static final IceCreamDto strawberryIceCream = IceCreamDto.builder().id(3L).name("Strawberry").cost(new BigDecimal("3.00")).build();

    @Test
    void onlyOneIceCreamShouldNotTakeCombinationCost() {
        
        List<IceCreamDto> iceCreams = List.of(vanillaIceCream);
        final Double percentageCombinationCost = 30.0;

        IceCreamDto result = IceCreamCombinator.createIceCreamCombination(iceCreams, percentageCombinationCost);

        final String expectedName = "Vanilla Ice Cream";
        final BigDecimal expectedCost = vanillaIceCream.getCost();
        assertEquals(expectedName, result.getName());
        assertEquals(expectedCost, expectedCost);
    }

    void twoIceCreamsShouldTakeCombinationCost() {
        
        List<IceCreamDto> iceCreams = List.of(vanillaIceCream, chocolateIceCream);
        final Double percentageCombinationCost = 30.0;

        IceCreamDto result = IceCreamCombinator.createIceCreamCombination(iceCreams, percentageCombinationCost);

        final String expectedName = "Chocolate and Vanilla Ice Cream";
        final BigDecimal expectedCost = new BigDecimal("5.60");
        assertEquals(expectedName, result.getName());
        assertEquals(expectedCost, expectedCost);
    }

    void threeIceCreamsShouldTakeCombinationCost() {
        
        List<IceCreamDto> iceCreams = List.of(vanillaIceCream, chocolateIceCream, strawberryIceCream);
        final Double percentageCombinationCost = 30.0;

        IceCreamDto result = IceCreamCombinator.createIceCreamCombination(iceCreams, percentageCombinationCost);

        final String expectedName = "Chocolate, Strawberry and Vanilla Ice Cream";
        final BigDecimal expectedCost = new BigDecimal("7.90");
        assertEquals(expectedName, result.getName());
        assertEquals(expectedCost, expectedCost);
    }

    

}
