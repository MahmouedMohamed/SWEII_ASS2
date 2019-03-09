import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class DynamicPeerGenerated {
    private List<Client> cleints ;

    

    public List<Client> GetLocalPeers(String ports) throws IOException {
        String[] parts = ports.split(" ");
        cleints = new ArrayList<>();
        for(int i =0 ;i <parts.length;i++)
            cleints.add(new Client("127.0.0.1",Integer.parseInt(parts[i])));
        return cleints;
    }

    public static void main(String[] args) throws IOException,SocketException {

        
    }

}
