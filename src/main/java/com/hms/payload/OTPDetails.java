package com.hms.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OTPDetails {
    private final String otp;
    private final long timestamp;

    public OTPDetails(String otp, long timestamp) {
        this.otp = otp;
        this.timestamp = timestamp;
    }
}
