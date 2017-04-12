package Video;



import javax.media.*;
import javax.swing.JFrame;

public class ReceptorRTPVideo extends JFrame implements Runnable
{
   private String ip;
   private String porta;
   
    public ReceptorRTPVideo(String ip, String porta)
    {
        this.ip = ip;
        this.porta = porta;
    }
    public void start(){
        try
        {
            //String url= "rtp://10.65.97.94:5530/video";
            //192.168.1.107    5530
            String url = "rtp://"+ip+":"+porta+"/video";
            MediaLocator dadosRecebidos = new MediaLocator(url);

            Player player = Manager.createRealizedPlayer(dadosRecebidos);
            
            System.out.println("to aki?");
            player.start();

            setSize (300, 300);
            getContentPane().add (player.getVisualComponent());
            setVisible(true);
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }

    @Override
    public void run() {
        start();
    }

}
