
package teamsC;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "TeamsService", targetNamespace = "http://team.ch01/", wsdlLocation = "http://localhost:8888/teams?wsdl")
public class TeamsService
    extends Service
{

    private final static URL TEAMSSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(teamsC.TeamsService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = teamsC.TeamsService.class.getResource(".");
            url = new URL(baseUrl, "http://localhost:8888/teams?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://localhost:8888/teams?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        TEAMSSERVICE_WSDL_LOCATION = url;
    }

    public TeamsService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TeamsService() {
        super(TEAMSSERVICE_WSDL_LOCATION, new QName("http://team.ch01/", "TeamsService"));
    }

    /**
     * 
     * @return
     *     returns Teams
     */
    @WebEndpoint(name = "TeamsPort")
    public Teams getTeamsPort() {
        return super.getPort(new QName("http://team.ch01/", "TeamsPort"), Teams.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Teams
     */
    @WebEndpoint(name = "TeamsPort")
    public Teams getTeamsPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://team.ch01/", "TeamsPort"), Teams.class, features);
    }

}
