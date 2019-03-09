import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class QueueModel extends Thread {

    private Queue<String> myQueue ;
    private FileSystem file;
    private int MinSleepTime = 1000;
    private int MaxSleepTime = 2000;
    public QueueModel(String FileName) throws FileNotFoundException, UnsupportedEncodingException {
        myQueue = new LinkedList<String>();
        file = new FileSystem(FileName);
    }
    public void SetMinMax(int Min,int Max){MinSleepTime=Min;MaxSleepTime=Max;}
    public synchronized boolean setMessage(String Message)
    {
        System.out.println("size of queue is : " + myQueue.size() );
        if(myQueue.size() > 100)
            return false;
        myQueue.add(Message);
        return true;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Random rand = new Random(System.currentTimeMillis());
                sleep(MinSleepTime+rand.nextInt(MaxSleepTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(myQueue.size() == 0)
                continue;
            String out = myQueue.poll();
            file.WriteToFile(out);
        }
    }
    public static void main(String[] args) throws IOException {
        QueueModel queue = new QueueModel("text");
        queue.setMessage("hello Yaseeo");
        queue.setMessage("hello Yaseeo");
        queue.setMessage("hello Yaseeo");
        queue.setMessage("hello Yaseeo");
        queue.setMessage("hello Yaseeo");
        queue.start();

    }

}
