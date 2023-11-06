package br.com.invillia.cdb.customer.application.producer;

import br.com.invillia.cdb.customer.domain.Paper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProducerService {

    @Value("${topic.name}")
    private String cdbTopic;

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public ProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(Paper paper) {
        try {
            String paperAsMessage = objectMapper.writeValueAsString(paper);
            kafkaTemplate.send(cdbTopic, paperAsMessage);
            log.info("food order produced {}", paperAsMessage);
        } catch (JsonProcessingException e) {
            log.warn("Message not sent for paper {}", paper);
        }

    }
}
