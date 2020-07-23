package mercadopago.ui;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.*;
import lombok.extern.slf4j.Slf4j;
import mercadopago.config.Credentials;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import static java.lang.String.format;
import static mercadopago.Constants.*;

@Slf4j
public class IndexVM {
    public static final String AMEX = "amex";
    public static final String REDLINK = "redlink";
    public static final int INSTALLMENTS = 6;
    public static final String EXTERNAL_REFERENCE = "fselvatici@gmail.com";

    @WireVariable
    private Credentials credentials;

    @Command
    public void buyProduct(@BindingParam("img") String img,
                           @BindingParam("title") String title,
                           @BindingParam("price") String price,
                           @BindingParam("unit") String unit) throws MPException {
        final Session session = Sessions.getCurrent();
        final Preference preference = createPreference(title, unit, price, img);
        session.setAttribute(PREFERENCE, preference);
        session.setAttribute(TITLE, title);
        session.setAttribute(IMG, img);
        session.setAttribute(PRICE, price);
        session.setAttribute(UNIT, unit);
        Executions.getCurrent().sendRedirect(DETAIL_ZUL);
    }

    private Preference createPreference(String title, String unit, String price, String img) throws MPException {
        MercadoPago.SDK.setAccessToken(credentials.getAccessToken());
        MercadoPago.SDK.setIntegratorId(credentials.getIntegratorId());

        final Preference preference = new Preference();
        preference.setExternalReference(EXTERNAL_REFERENCE);
        preference.setNotificationUrl(credentials.getWebhook());
        preference.appendItem(createItem(title, unit, price, img));
        preference.setPayer(createPayer());
        preference.setAutoReturn(Preference.AutoReturn.approved);
        preference.setBackUrls(new BackUrls(getPath(SUCCESS_ZUL), getPath(PENDING_ZUL), getPath(FAILURE_ZUL)));
        preference.setPaymentMethods(createPaymentMethods());
        preference.save();
        log.info("PreferenceId:{}, NotificationUrl:{}, ExternalPreference:{}",
                preference.getId(), preference.getNotificationUrl(), preference.getExternalReference());
        return preference;
    }

    private Item createItem(String title, String unit, String price, String img) {
        return new Item().setId("1234")
                .setTitle(title)
                .setDescription("Dispositivo móvil de Tienda e-commerce")
                .setPictureUrl(getPath(img))
                .setQuantity(Integer.parseInt(unit))
                .setUnitPrice(Float.parseFloat(price));
    }

    private Payer createPayer() {
        return new Payer().setName(credentials.getName())
                .setSurname(credentials.getSurname())
                .setEmail(credentials.getUserEmail())
                .setPhone(new Phone().setAreaCode(credentials.getUserAreaCode())
                        .setNumber(credentials.getUserTelephone()))
                .setAddress(new Address().setStreetName(credentials.getStreetName())
                        .setStreetNumber(credentials.getStreetNumber())
                        .setZipCode(credentials.getUserAreaCode()));
    }

    private PaymentMethods createPaymentMethods() {
        return new PaymentMethods().setExcludedPaymentMethods(AMEX)
                .setExcludedPaymentTypes(REDLINK)
                .setInstallments(INSTALLMENTS);
    }

    private String getPath(String relativePath) {
        final Execution current = Executions.getCurrent();
        return format("%s://%s:%s/%s", current.getScheme(), current.getServerName(), current.getServerPort(), relativePath);
    }
}
