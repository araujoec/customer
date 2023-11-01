package br.com.invillia.cdb.customer.application;

import br.com.invillia.cdb.customer.exception.TradingException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class TradingService {

    @Autowired
    private WalletService walletService;

    @Autowired
    private CustomerService customerService;

    public void buyCDB(String document, long amount) {
        if (amount <= 0) {
            throw new TradingException("Valor inválido de CDB!");
        }
        walletService.buyCDB(customerService.getCustomer(document), amount);
    }

    public void sellCDB(String document, long amount) {
        if (amount <= 0) {
            throw new TradingException("Valor inválido de CDB!");
        }
        walletService.sellCDB(customerService.getCustomer(document), amount);
    }
}
