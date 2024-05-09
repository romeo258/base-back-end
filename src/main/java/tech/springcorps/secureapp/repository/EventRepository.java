package tech.springcorps.secureapp.repository;

import tech.springcorps.secureapp.domain.UserEvent;
import tech.springcorps.secureapp.enumeration.EventType;

import java.util.Collection;

public interface EventRepository {

    Collection<UserEvent> getEventsByUserId(Long userId);
    void addUserEvent(String email, EventType eventType, String device, String ipAddress);
    void addUserEvent(Long userId, EventType eventType, String device, String ipAddress);
}
