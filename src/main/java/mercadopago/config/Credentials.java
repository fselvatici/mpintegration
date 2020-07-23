package mercadopago.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class Credentials {
    @Value("${mp.integratorId}")
    private String integratorId;
    @Value("${mp.webhook}")
    private String webhook;

    @Value("${mp.testSeller.collectorId}")
    private String collectorId;
    @Value("${mp.testSeller.publicKey}")
    private String publicKey;
    @Value("${mp.testSeller.accessToken}")
    private String accessToken;

    @Value("${mp.buyer.id}")
    private String userId;
    @Value("${mp.buyer.email}")
    private String userEmail;
    @Value("${mp.buyer.password}")
    private String userPassword;
    @Value("${mp.buyer.name}")
    private String name;
    @Value("${mp.buyer.surname}")
    private String surname;
    @Value("${mp.buyer.areaCode}")
    private String userAreaCode;
    @Value("${mp.buyer.telephone}")
    private String userTelephone;
    @Value("${mp.buyer.streetName}")
    private String streetName;
    @Value("${mp.buyer.streetNumber}")
    private Integer streetNumber;
    @Value("${mp.buyer.postalCode}")
    private Integer postalCode;
}
