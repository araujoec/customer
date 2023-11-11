package br.com.invillia.cdb.customer.web.controller;

import br.com.invillia.cdb.customer.application.BalanceService;
import br.com.invillia.cdb.customer.domain.Balance;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "balance")
@Tag(name = "Balance", description = "balance API")
public class BalanceController {

    @Autowired
    BalanceService balanceService;

    @Operation(summary = "Update balance for customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "balance updated with success")
    })
    @PostMapping(value = "/update")
    public ResponseEntity<Balance> updateBalance(
            @RequestBody
            Balance balance,
            @RequestHeader(value = "transactionId", required = false)
            String transactionId
    ) {
        return ResponseEntity.ok(balanceService.updateBalance(balance, transactionId));
    }
}
