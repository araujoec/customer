package br.com.invillia.cdb.customer.web.controller;

import br.com.invillia.cdb.customer.application.CustomerService;
import br.com.invillia.cdb.customer.persistence.entities.CustomerEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Api(value = "CustomerController")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private Logger logger = LoggerFactory.getLogger(Logger.class);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "cadastrar-cliente")
    @RequestMapping(value = "create-customer", method = RequestMethod.GET)
    public ResponseEntity<CustomerEntity> createCustomer(
            @RequestParam("name")
            String name,
            @RequestParam("document")
            String document,
            @RequestParam("email")
            String email,
            @RequestParam("balance")
            Long balance
    ) {
        CustomerEntity newCustomer = customerService.createCustomer(name, document, email, balance);
        logger.info(String.format("Cliente criado com sucesso.\tNome: %s\tE-mail: %s\tBalance: %s",
                        newCustomer.getName(),
                        newCustomer.getDocument(),
                        newCustomer.getWallet().getBalance()
                )
        );
        return ResponseEntity.ok(newCustomer);
    }
}
