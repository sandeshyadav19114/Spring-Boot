package com.api.exapmle.service;

import com.api.exapmle.configuration.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WhatsappSender {

    @Autowired
    private TwilioConfig twilioConfig;

    @PostConstruct
    public void initTwilio() {
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
    }

    public String sendWhatsappMessage(String to, String message) {
        Message whatsappMessage = Message.creator(
                new PhoneNumber("whatsapp:" + to),
                new PhoneNumber("whatsapp:" + twilioConfig.getWhatsappNumber()),
                message
        ).create();

        return "Message Sent, SID: " + whatsappMessage.getSid();
    }
}
