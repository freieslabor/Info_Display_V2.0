package Info_Display.V20.persistence.service;

import Info_Display.V20.lib.Exception.NoAccessForipAddressException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AccessControllService {

    String[] privateIpNet = {"10.0.0.0", "172.16.0.0", "192.168.0.0"};

    public void checkIpAddress(HttpServletRequest request) throws NoAccessForipAddressException {

        for (int ii = 0; ii < privateIpNet.length; ii++){
            if(privateIpNet[ii].equals(request.getRemoteAddr())){
            }else{
                throw new NoAccessForipAddressException("You\'re not in the Hackerspace Network");
            }
        }

    }

}
