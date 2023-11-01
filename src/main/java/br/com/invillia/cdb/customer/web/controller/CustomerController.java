package br.com.invillia.cdb.customer.web.controller;

import br.com.invillia.cdb.customer.application.CustomerService;
import br.com.invillia.cdb.customer.domain.Customer;
import br.com.invillia.cdb.customer.persistence.entities.CustomerEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "customer")
@Tag(name = "Customer", description = "the customer API")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private Logger logger = LoggerFactory.getLogger(Logger.class);

//    public CustomerController(CustomerService customerService) {
//        this.customerService = customerService;
//    }

    @Operation(summary = "Create a new customer and wallet",
            description = "create a new customer with name, document, email and the initial balance for its wallet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "customer created with success")
    })
    @PostMapping(value = "/create")
    public ResponseEntity<Customer> createCustomer(
            @RequestParam("name")
            String name,
            @RequestParam("document")
            String document,
            @RequestParam("email")
            String email,
            @RequestParam("balance")
            Long balance
    ) {
        Customer newCustomer = customerService.createCustomer(name, document, email, balance);
        logger.info(String.format("Cliente criado com sucesso.\tNome: %s\tE-mail: %s\tBalance: %s",
                        newCustomer.getName(),
                        newCustomer.getDocument(),
                        newCustomer.getWallet().getBalance()
                )
        );
        return ResponseEntity.ok(newCustomer);
    }

    @Operation(summary = "Get a customer by document")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "customer created with success"),
            @ApiResponse(responseCode = "404", description = "customer not found in database")
    })
    @GetMapping(value = "/get")
    public ResponseEntity<Customer> getCustomer(
            @RequestParam("document")
            String document
    ) {
        Customer customer = customerService.getCustomer(document);

        return ResponseEntity.ok(customer);
    }
}
