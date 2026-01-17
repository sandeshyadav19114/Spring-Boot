package com.api.exapmle.service;

import com.api.exapmle.configuration.TwilioConfig;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class SmsSender {

    private final TwilioConfig twilioConfig;

    public SmsSender(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;

        Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
    }
    public String sendSms(String to, String body) {
        Message message =  Message.creator(
                        new PhoneNumber(to),
                        new PhoneNumber(twilioConfig.setFromNumber()),
                        body)
                .create();
        return message.getSid();
    }
}


