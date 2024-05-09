package tech.springcorps.secureapp.service;

import tech.springcorps.secureapp.domain.UserEvent;
import tech.springcorps.secureapp.enumeration.EventType;

import java.util.Collection;

public interface EventService {
    Collection<UserEvent> getEventsByUserId(Long userId);
    void addUserEvent(String email, EventType eventType, String device, String ipAddress);
    void addUserEvent(Long userId, EventType eventType, String device, String ipAddress);
}
