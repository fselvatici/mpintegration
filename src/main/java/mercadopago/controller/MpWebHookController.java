package mercadopago.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import mercadopago.model.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.HttpStatus.OK;

@Controller
@Slf4j
@RequestMapping(name = "/mphook")
public class MpWebHookController {
    @SneakyThrows
    @PostMapping
    public ResponseEntity<Void> notification(@RequestBody Event event) {
        logReceivedEvent(new ObjectMapper().writeValueAsString(event));
        return new ResponseEntity<>(OK);
    }

    protected void logReceivedEvent(String json) {
        log.info("Evento recibido: {}", json);
    }
}
