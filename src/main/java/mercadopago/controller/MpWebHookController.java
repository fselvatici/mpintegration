package mercadopago.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import mercadopago.model.Event;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mphook")
@Slf4j
public class MpWebHookController {
    @PostMapping
    public String notification(@RequestBody Event event) {
        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(event);
            logReceivedEvent(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @GetMapping
    public String hello() {
        return "hello";
    }

    protected void logReceivedEvent(String json) {
        log.info("Evento recibido: {}", json);
    }
}
