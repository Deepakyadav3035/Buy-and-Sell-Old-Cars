package com.hms.service;

import com.hms.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private final TwilioConfig twilioConfig;

    // Constructor-based dependency injection
    @Autowired
    public SmsService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    public void sendSms(String to, String body) {
        Message.creator(
                new PhoneNumber(to),  // The recipient's phone number
                new PhoneNumber(twilioConfig.getTwilioPhoneNumber()),  // Your Twilio phone number
                body  // The message body
        ).create();
    }
}
