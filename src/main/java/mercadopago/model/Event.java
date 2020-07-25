package mercadopago.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@lombok.Data
@Builder
public class Event {
    @JsonProperty
    public Long id;
    @JsonProperty("live_mode")
    public Boolean liveMode;
    @JsonProperty
    public String type;
    @JsonProperty("date_created")
    public String dateCreated;
    @JsonProperty("application_id")
    public Long applicationId;
    @JsonProperty("user_id")
    public Long userId;
    @JsonProperty
    public String version;
    @JsonProperty("api_version")
    public String apiVersion;
    @JsonProperty
    public String action;
    @JsonProperty
    public Data data;
}