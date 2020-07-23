package mercadopago.ui;

import com.mercadopago.resources.Preference;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static java.util.Optional.ofNullable;
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
        fillParameters(session);
        preference = (Preference) session.getAttribute(PREFERENCE);
        title = (String) session.getAttribute(TITLE);
        img = (String) session.getAttribute(IMG);
        price = (String) session.getAttribute(PRICE);
        unit = (String) session.getAttribute(UNIT);
        final Optional<String> payment_status = ofNullable(Executions.getCurrent().getParameter("payment_status"));
        if (payment_status.isPresent()) {
            switch (payment_status.get()) {
                case APPROVED: {
                    Executions.getCurrent().sendRedirect(SUCCESS_ZUL);
                    break;
                }
                case IN_PROCESS:
                case PENDING: {
                    Executions.getCurrent().sendRedirect(PENDING_ZUL);
                    break;
                }
                case RECHAZADO: {
                    Executions.getCurrent().sendRedirect(FAILURE_ZUL);
                    break;
                }
            }
        }
    }

    private void fillParameters(Session session) {
        final HttpServletRequest req = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
        session.setAttribute("payment_status", req.getParameter("payment_status"));
        session.setAttribute("collection_id", req.getParameter("collection_id"));
        session.setAttribute("collection_status", req.getParameter("collection_status"));
        session.setAttribute("external_ref", req.getParameter("external_ref"));
        session.setAttribute("payment_type", req.getParameter("payment_type"));
        session.setAttribute("preference_id", req.getParameter("preference_id"));
        session.setAttribute("site_id", req.getParameter("site_id"));
        session.setAttribute("processing_mode", req.getParameter("processing_mode"));
        session.setAttribute("merchant_account_id", req.getParameter("merchant_account_id"));
    }

}
