import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

public class Peer extends Thread {
    private static List<Client> cleints ;
    private static String UserName;
    private MultiMsgMultiClientServer server;
    private QueueModel queue;
    private FakeDataGenerator fakeDataGenerator = new FakeDataGenerator();
    private DynamicPeerGenerated dynamicPeerGenerated = new DynamicPeerGenerated();
    private int MinSleepTime = 1000;
    private int MaxSleepTime = 5000;
    public void SetFileName(String FileName) throws FileNotFoundException, UnsupportedEncodingException {
        queue = new QueueModel(FileName);
        queue.start();
    }
    public void SetLocalClient(String ports) throws IOException {
        cleints = dynamicPeerGenerated.GetLocalPeers(ports);
    }
    public void SetNetworkClient(int PortNumber) throws IOException {
        cleints = dynamicPeerGenerated.GetPeersIpOnPort(PortNumber);
    }
    public void SetServer(int sport) throws IOException {
        server= new MultiMsgMultiClientServer(sport,queue);
    }
    public void SetUserName(String UserName){
        this.UserName= UserName;
    }
    public void StartServer() throws IOException {
        server.start();
    }
    public void SetMinMax(int Min,int Max){MinSleepTime=Min;MaxSleepTime=Max;}

    public synchronized static void SendMessage(String SendMessage) throws IOException {
        for(int i = 0 ;i <cleints.size(); i++) {
            cleints.get(i).sendMessage(  UserName + ": " +SendMessage);
        }
    }
    @Override
    public void run() {
        while (true)
        {
            try {
                Random rand = new Random(System.currentTimeMillis());
                sleep(MinSleepTime+rand.nextInt(MaxSleepTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                String SendMessage = fakeDataGenerator.GetRandomQuotes();
                if (!queue.setMessage("You: " +SendMessage))
                {//no space
                    //1 ->sleep theed no lose
                    //2 -> no thing with lose
                    sleep(5000);
                }
                SendMessage(SendMessage);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {


    }

}
