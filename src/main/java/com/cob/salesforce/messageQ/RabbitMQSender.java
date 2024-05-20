package com.cob.salesforce.messageQ;

import com.cob.salesforce.models.message.DoctorMessage;
//import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
//    @Autowired
    //private AmqpTemplate rabbitTemplate;

//    @Value("${rabbitmq.patient.intake.exchange}")
//    private String exchange;
//    @Value("${rabbitmq.patient.intake.potential.routing.key}")
//    private String potentialRoutingKey;
//    @Value("${rabbitmq.patient.intake.target.routing.key}")
//    private String targetRoutingKey;
//
//    public void send(DoctorMessage doctorMessage) {
//        if (doctorMessage.getIsPotential())
//            //rabbitTemplate.convertAndSend(exchange, potentialRoutingKey, doctorMessage);
//        else
//            rabbitTemplate.convertAndSend(exchange, targetRoutingKey, doctorMessage);
//    }

}
