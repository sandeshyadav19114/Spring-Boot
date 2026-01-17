package com.api.exapmle.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @Value("${twilio.phone-number}")
    private String fromNumber;

    public String getAccountSid() {
        return accountSid;
    }

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getFromNumber() {
        return fromNumber;
    }


    public String setFromNumber() {
        this.fromNumber=fromNumber;
        return "";
    }
// for Whatsapp service
    private String whatsappNumber;

    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    @PostConstruct
    public void  initTwilio(){
        Twilio.init(accountSid,authToken);
    }
}

