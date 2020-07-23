package mercadopago.ui;

import com.mercadopago.resources.Preference;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

import static mercadopago.Constants.*;

@Getter
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
@Slf4j
public class DetailVM {
    public static final String APPROVED = "approved";
    public static final String IN_PROCESS = "in_process";
    public static final String PENDING = "pending";
    public static final String RECHAZADO = "rechazado";

    private Preference preference;
    private String title;
    private String img;
    private String price;
    private String unit;

    @Init
    public void init() {
        final Session session = Sessions.getCurrent();
        preference = (Preference) session.getAttribute(PREFERENCE);
        title = (String) session.getAttribute(TITLE);
        img = (String) session.getAttribute(IMG);
        price = (String) session.getAttribute(PRICE);
        unit = (String) session.getAttribute(UNIT);
    }
}
