package br.com.invillia.cdb.customer.application;

import br.com.invillia.cdb.customer.application.producer.ProducerService;
import br.com.invillia.cdb.customer.domain.Paper;
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

    @Autowired
    private ProducerService producerService;

    public void buyCDB(String document, Long amount) {
        if (amount <= 0) {
            throw new TradingException("Valor inválido de CDB!");
        }
        walletService.buyCDB(customerService.getCustomer(document), amount);
        Paper paper = new Paper(document, amount);

        producerService.sendMessage(paper);
    }

    public void sellCDB(String document, long amount) {
        if (amount <= 0) {
            throw new TradingException("Valor inválido de CDB!");
        }
        walletService.sellCDB(customerService.getCustomer(document), amount);
    }
}
