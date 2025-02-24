package com.hms.service;

import com.hms.payload.OTPDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {
    
    private final Map<String,OTPDetails>otpStore = new HashMap<>();
    
    private static final int OTO_EXPIRATION_TIME=5;
    
    public String generateOtp(String mobile){
        String otp = String.format("%06d", new Random().nextInt(9999999));
        OTPDetails otpDetails = new OTPDetails(otp, System.currentTimeMillis());
        otpStore.put(mobile, otpDetails);
        return otp;
    }

    public boolean validateOtp(String mobile, String otp){
        OTPDetails otpDetails = otpStore.get(mobile);
        if(otpDetails==null){
            return false;
        }

        long currentTime = System.currentTimeMillis();
        long otpTime = otpDetails.getTimestamp();
        long timeDifference = TimeUnit.MILLISECONDS.toMinutes(currentTime - otpTime);
        if(timeDifference>OTO_EXPIRATION_TIME){
            otpStore.remove(mobile);
            return false;
        }
        return otpDetails.getOtp().equals(otp);
    }
}
