package com.hms.service;

import com.hms.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppService {

    private final TwilioConfig twilioConfig;

    @Autowired
    public WhatsAppService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    public void sendWhatsAppMessage(String to, String body) {
        Message.creator(
                new PhoneNumber("whatsapp:" + to),  // Recipient's WhatsApp number
                new PhoneNumber(twilioConfig.getTwilioWhatsAppNumber()),  // Your Twilio WhatsApp number
                body  // Message content
        ).create();
    }
}
