package mercadopago.model;

import lombok.Builder;

@lombok.Data
@Builder
public class Event {
    public Long id;
    public Boolean liveMode;
    public String type;
    public String dateCreated;
    public Long applicationId;
    public Long userId;
    public Long version;
    public String apiVersion;
    public String action;
    public Data data;
}