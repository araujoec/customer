package br.com.invillia.cdb.customer.web.controller;

import br.com.invillia.cdb.customer.application.TradingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "trading")
@Tag(name = "Trading", description = "the trading API for buying and selling CDBs.")
public class TradingController {

    @Autowired
    private TradingService tradingService;

    @Operation(summary = "Buy CDB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "paper bought with success")
    })
    @PostMapping(value = "/buy")
    public void buyCDB(
            @RequestParam("document")
            String document,
            @RequestParam("amount")
            long amount
    ) {
        tradingService.buyCDB(document, amount);
    }

    @Operation(summary = "Sell CDB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "paper sold with success")
    })
    @PostMapping(value = "/sell")
    public void sellCDB(
            @RequestParam("document")
            String document,
            @RequestParam("amount")
            long amount
    ) {
        tradingService.sellCDB(document, amount);
    }
}
