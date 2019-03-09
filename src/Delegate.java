import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Delegate {

    private Peer p1 ;

    public Delegate() throws IOException {
        p1 = new Peer() ;
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter 1 to work on Network , 2 to work local: ");
        String x = reader.nextLine();
        if(x.equals("1"))
            Network();
        else
            Local();
    }
    private void Network()throws IOException
    {
        String UserName = System.getProperty("user.name");
        String FileName = "text";
        int PortNumper = 4441;
        p1.SetFileName(FileName);
        p1.SetServer(PortNumper);
        Scanner reader = new Scanner(System.in);
        System.out.println("the server created press any key to create clients on peer: ");
        reader.nextLine();
        p1.SetNetworkClient(PortNumper);
        p1.SetUserName(UserName);
        p1.start();
        p1.StartServer();
    }
    private void Local()throws IOException
    {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a file name: ");
        String filename = reader.nextLine();
        p1.SetFileName(filename);

        System.out.println("Enter a server port: ");
        int serverPort = reader.nextInt();
        p1.SetServer(serverPort);

        reader.nextLine();
        System.out.println("Enter a client port: ");
        String ClientPort = reader.nextLine();
        p1.SetLocalClient(ClientPort);

        System.out.println("Enter Your name: ");
        String m = reader.nextLine();
        p1.SetUserName(m);

        p1.start();
        p1.StartServer();
    }

    public static void main(String[] args) throws IOException {
        Delegate d = new Delegate();
    }

}
