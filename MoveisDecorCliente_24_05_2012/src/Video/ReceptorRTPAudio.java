package Video;;



import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;


import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;


public class ReceptorRTPAudio implements Runnable 
{
    //ip e porta do localhost
    private String porta;
    private String ip;
    
    public ReceptorRTPAudio(String ip, String porta){
        this.ip = ip;
        this.porta = porta;
        
    }
    public void start(){
        try
        {
            String url= "rtp://"+ip+":"+porta+"/audio";

            MediaLocator localizacao = new MediaLocator(url);

            Player player = Manager.createPlayer(localizacao);

            player.realize();

            while (player.getState() != Player.Realized){
                Thread.sleep(50);
            }

            player.start();
        } 
        catch (Exception exc)
        {
            System.err.println(exc.toString());
            System.exit(1);
        }
}

    @Override
    public void run() {
        start();
    }
}


