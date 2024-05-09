package tech.springcorps.secureapp.service;

import tech.springcorps.secureapp.enumeration.VerificationType;

public interface EmailService {
    void sendVerificationEmail(String firstName, String email, String verificationUrl, VerificationType verificationType);
}
