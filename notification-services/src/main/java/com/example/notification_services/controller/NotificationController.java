package com.example.notification_services.controller;

import com.example.event.dto.NotificationEvent;
import com.example.notification_services.dto.request.Recipient;
import com.example.notification_services.dto.request.SendEmailRequest;
import com.example.notification_services.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationController {
    EmailService emailService;

    @KafkaListener(topics = "notification-delivery")
    public void listenNotification(NotificationEvent message) {
        emailService.sendEmail(SendEmailRequest.builder()
                .to(Recipient.builder()
                        .email(message.getRecipient())
                        .name(message.getSubject())
                        .build())
                .subject(message.getSubject())
                .build());
    }
}
