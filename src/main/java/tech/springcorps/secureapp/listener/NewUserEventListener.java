package tech.springcorps.secureapp.listener;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import tech.springcorps.secureapp.event.NewUserEvent;
import tech.springcorps.secureapp.service.EventService;

import static tech.springcorps.secureapp.utils.RequestUtils.getDevice;
import static tech.springcorps.secureapp.utils.RequestUtils.getIpAddress;

@Component
@RequiredArgsConstructor
public class NewUserEventListener {
    private final EventService eventService;
    private final HttpServletRequest request;

    @EventListener
    public void onNewUserEvent(NewUserEvent event) {
        eventService.addUserEvent(event.getEmail(), event.getType(), getDevice(request), getIpAddress(request));
    }
}
