package com.applaudo.project.util;

import com.applaudo.project.model.IceCreamDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

public class IceCreamCombinator {


    private static BigDecimal calculateIceCreamPrice(Double combinationPercentage,IceCreamDto iceCreamToCheck, IceCreamDto highestPriceIceCream) {
        final BigDecimal  MAX_ICE_CREAM_PERCENTAGE = new BigDecimal(1.00);
        final BigDecimal  COMBINATION_PERCENTAGE = new BigDecimal(combinationPercentage).setScale(2, RoundingMode.CEILING).divide(new BigDecimal(100));
        
        Boolean isHighestPriceIceCream = iceCreamToCheck.getId().equals(highestPriceIceCream.getId());


        System.out.println("iceCream: " + iceCreamToCheck.getName() + " percentage to apply: " + (isHighestPriceIceCream? MAX_ICE_CREAM_PERCENTAGE: COMBINATION_PERCENTAGE) );
        return iceCreamToCheck.getCost().multiply(isHighestPriceIceCream ? MAX_ICE_CREAM_PERCENTAGE: COMBINATION_PERCENTAGE);
    }

    private static String buildIceCreamName(List<IceCreamDto> sortedIceCreamByPrice, IceCreamDto hightestPriceIceCream, IceCreamDto lowestPirceIceCream){
        
        StringBuilder iceCreamName = new StringBuilder();

       for (IceCreamDto iceCreamDto : sortedIceCreamByPrice) {
         if(iceCreamDto.getId().equals(hightestPriceIceCream.getId())){
            iceCreamName.append(iceCreamDto.getName());
          }else if(iceCreamDto.getId().equals(lowestPirceIceCream.getId()) ){
            iceCreamName.append(" and ");
            iceCreamName.append(iceCreamDto.getName());
          }else {
            iceCreamName.append(" , ");
            iceCreamName.append(iceCreamDto.getName());
          }  
       } 
       return iceCreamName.toString();
        
    }

    public static IceCreamDto createIceCreamCombination(List<IceCreamDto> iceCreams, Double percentageCombinationCost) {
        
        List<IceCreamDto> sortedIceCreamByPrice = iceCreams.stream().sorted(Comparator.comparing(IceCreamDto::getCost).reversed()).toList();

        IceCreamDto hightestPriceIceCream = sortedIceCreamByPrice.getFirst();
        IceCreamDto lowestPirceIceCream = sortedIceCreamByPrice.getLast();


        BigDecimal totalCost = sortedIceCreamByPrice
        .stream().map(iceCream -> ((calculateIceCreamPrice(percentageCombinationCost, iceCream, hightestPriceIceCream)))).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2);        

        String iceCreamName = buildIceCreamName(sortedIceCreamByPrice, hightestPriceIceCream, lowestPirceIceCream);
       
       
        return IceCreamDto.builder().id(null).cost(totalCost).name(iceCreamName).build();
    }
}
