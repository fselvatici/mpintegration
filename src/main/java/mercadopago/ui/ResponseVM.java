package mercadopago.ui;

import lombok.Getter;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

@Getter
public class ResponseVM {
    private String paymentStatus;
    private String collectionId;
    private String collectionStatus;
    private String externalRef;
    private String paymentType;
    private String preferenceId;
    private String siteId;
    private String processingMode;
    private String merchantAccountId;

    @Init
    public void init() {
        final Session current = Sessions.getCurrent();
        paymentStatus = (String) current.getAttribute("payment_status");
        collectionId = (String) current.getAttribute("collection_id");
        collectionStatus = (String) current.getAttribute("collection_status");
        externalRef = (String) current.getAttribute("external_ref");
        paymentType = (String) current.getAttribute("payment_type");
        preferenceId = (String) current.getAttribute("preference_id");
        siteId = (String) current.getAttribute("site_id");
        processingMode = (String) current.getAttribute("processing_mode");
        merchantAccountId = (String) current.getAttribute("merchant_account_id");
    }

    public String getColor() {
        if (DetailVM.APPROVED.equals(paymentStatus)) {
            return "green";
        } else if (DetailVM.IN_PROCESS.equals(paymentStatus) || DetailVM.PENDING.equals(paymentStatus)) {
            return "yellow";
        } else if (DetailVM.RECHAZADO.equals(paymentStatus)) {
            return "red";
        } else {
            return "white";
        }
    }
}
