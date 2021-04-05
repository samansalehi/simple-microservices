package com.sam.currencuconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ConversionController {
    @Autowired
    CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    @GetMapping("/conversion/from/{from}/to/{to}/quantity/{quantity}")
    CurrencyConversion currencyConversion(@PathVariable String from, @PathVariable String to,
                                                         @PathVariable BigDecimal quantity)
    {

        Map<String,String> pathVariable= new HashMap<>();
        pathVariable.put("from",from);
        pathVariable.put("to",to);
        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().
                getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class,pathVariable);
        CurrencyConversion  currencyConversion=responseEntity.getBody();
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalAmount(quantity.multiply(currencyConversion.getConversionRate()));
        return  currencyConversion;
    }

    @GetMapping("/conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    CurrencyConversion currencyConversionByFeign(@PathVariable String from, @PathVariable String to,
                                          @PathVariable BigDecimal quantity)
    {
        CurrencyConversion currencyConversion = currencyExchangeServiceProxy.retriveCurrencyExchange(from, to);
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalAmount(quantity.multiply(currencyConversion.getConversionRate()));
        return  currencyConversion;
    }
}
