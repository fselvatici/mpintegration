package mercadopago.ui;

import lombok.Getter;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;

import javax.servlet.http.HttpServletRequest;

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
        final HttpServletRequest request = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
        paymentStatus = request.getParameter("payment_status");
        collectionId = request.getParameter("collection_id");
        collectionStatus = request.getParameter("collection_status");
        externalRef = request.getParameter("external_reference");
        paymentType = request.getParameter("payment_type");
        preferenceId = request.getParameter("preference_id");
        siteId = request.getParameter("site_id");
        processingMode = request.getParameter("processing_mode");
        merchantAccountId = request.getParameter("merchant_account_id");
    }

    public String getColor() {
        if (DetailVM.APPROVED.equals(collectionStatus)) {
            return "green";
        } else if (DetailVM.IN_PROCESS.equals(collectionStatus) || DetailVM.PENDING.equals(collectionStatus)) {
            return "yellow";
        } else if (DetailVM.RECHAZADO.equals(collectionStatus)) {
            return "red";
        } else {
            return "white";
        }
    }
}
