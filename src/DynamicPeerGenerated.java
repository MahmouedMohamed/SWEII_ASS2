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

    private List<InetAddress> listAllBroadcastAddresses() throws SocketException {
        List<InetAddress> broadcastList = new ArrayList<>();
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();

            if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                continue;
            }
            networkInterface.getInterfaceAddresses().stream()
                    .map(a -> a.getBroadcast())
                    .filter(Objects::nonNull)
                    .forEach(broadcastList::add);
        }
        return broadcastList;
    }

    public List<Client> GetPeersIpOnPort(int PortNumber) throws IOException {

        List<InetAddress> list = listAllBroadcastAddresses();
        cleints = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++)
        {
            Socket socket = null;
            try {
                System.out.println(list.get(i).toString().substring(1));
                socket = new Socket(list.get(i).toString().substring(1), PortNumber);
                socket.close();
                cleints.add(new Client(list.get(i).toString().substring(1),PortNumber));
                System.out.println(list.get(i).toString().substring(1));
            } catch (Exception e) {
                if (socket != null) {
                    socket.close();
                }
            }
        }
        return cleints;
    }

    public List<Client> GetLocalPeers(String ports) throws IOException {
        String[] parts = ports.split(" ");
        cleints = new ArrayList<>();
        for(int i =0 ;i <parts.length;i++)
            cleints.add(new Client("127.0.0.1",Integer.parseInt(parts[i])));
        return cleints;
    }

    public static void main(String[] args) throws IOException,SocketException {

        MultiMsgMultiClientServer m1 = new MultiMsgMultiClientServer(4441,new QueueModel("yaseen"));
        Client c1 = new Client("172.0.0.1",4441);
        DynamicPeerGenerated d1 = new DynamicPeerGenerated();
        d1.GetPeersIpOnPort(4441);
    }

}
