package mq.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class ConsumerListener {

    @RabbitListener(queues = "test.queue")
    public void process(String msg, Channel channel, Message message) throws IOException {
        try {
            log.info("msg:{},channel:{},message:{}", msg, channel, message);
            //DeliveryTag是rabbitMq服务器生成的唯一标识，false表示不是批量确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // requeue=false → 进入 DLQ（需要队列配了 x-dead-letter-exchange）
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

}
