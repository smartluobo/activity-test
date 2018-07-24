package com.redpacket.activity.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

public class DefineMessageConverter extends JsonMessageConverter {

    private MessageProperties messageProperties;

    public DefineMessageConverter(MessageProperties messageProperties){
        this.messageProperties = messageProperties;
    }
    public Message createMessage(Object objectToConvert)
            throws MessageConversionException {
        return super.createMessage(objectToConvert,messageProperties);
    }

}
