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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            @RequestParam("balance")
            Balance balance,
            @RequestParam(value = "operationId", required = false)
            String operationId
    ) {
        return ResponseEntity.ok(balanceService.updateBalance(balance, operationId));
    }
}
