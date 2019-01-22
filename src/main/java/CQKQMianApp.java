import com.mumu.webclient.KQWebClient;
import controller.CQMSGAdapter;

import java.net.URI;
import java.net.URISyntaxException;

public class CQKQMianApp {
    /**
     * KQURL 酷Q本机地址
     */
    private static final String KQURL="ws://localhost:253030";
    private static KQWebClient cc;
    public static void main(String[] args) throws URISyntaxException {
         cc= new KQWebClient(new URI(KQURL));
         cc.addQQMSGListenner(new CQMSGAdapter(cc));
    }
}
