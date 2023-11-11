package br.com.invillia.cdb.customer.web.controller;

import br.com.invillia.cdb.customer.application.CustomerService;
import br.com.invillia.cdb.customer.domain.Customer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
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


    @Operation(summary = "Create a new customer and wallet",
            description = "create a new customer with name, document, email and the initial balance for its wallet")
    @PostMapping(value = "/create")
    public ResponseEntity<Customer> createCustomer(
            @RequestParam("name")
            String name,
            @RequestParam("document")
            String document,
            @RequestParam("email")
            String email,
            @RequestParam(name = "balance", required = false, defaultValue = "0")
            @Min(0)
            Double balance
    ) {
        Customer newCustomer = customerService.createCustomer(name, document, email, balance);
        return ResponseEntity.ok(newCustomer);
    }

    @Operation(summary = "Get a customer by document")
    @GetMapping(value = "/get")
    public ResponseEntity<Customer> getCustomer(
            @RequestParam("document")
            String document,
            @RequestParam(value = "transactionId", required = false)
            String transactionId
    ) {
        Customer customer = customerService.getCustomer(document, transactionId);

        return ResponseEntity.ok(customer);
    }
}
