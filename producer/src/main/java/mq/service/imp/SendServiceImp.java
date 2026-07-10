package mq.service.imp;

import lombok.RequiredArgsConstructor;
import mq.service.SendService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SendServiceImp implements SendService {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendMq(String msg) {
        //全局配置中的回调使用correlationData
        CorrelationData cd = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("test.exchange", "test.routingKey", msg, cd);
    }
}
