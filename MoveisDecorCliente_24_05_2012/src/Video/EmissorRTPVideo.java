package Video;;


import java.util.Vector;
import javax.media.format.*;
import javax.media.protocol.*;
import javax.media.rtp.*;
import javax.media.*;
import javax.media.control.*;
import java.util.Vector;
import javax.media.format.*;
import javax.media.protocol.*;
import javax.media.rtp.*;
import javax.media.*;
import javax.media.control.*;
import java.util.Vector;
import javax.media.format.*;
import javax.media.protocol.*;
import javax.media.rtp.*;
import javax.media.*;
import javax.media.control.*;
import java.util.Vector;
import javax.media.format.*;
import javax.media.protocol.*;
import javax.media.rtp.*;
import javax.media.*;
import javax.media.control.*;

public class EmissorRTPVideo
{ 
    private String ip;
    private String porta;
    private MediaLocator camera = new MediaLocator("vfw:Microsoft WDM Image Capture (Win32):0");;
    
    public EmissorRTPVideo(String ip, String porta) 
    {
        this.ip = ip;
        this.porta = porta;
    }
        
        public void start(){
        try
        {
           // Vector deviceList = CaptureDeviceManager.getDeviceList(new VideoFormat(VideoFormat.YUV));
            //CaptureDeviceInfo device = (CaptureDeviceInfo)deviceList.get(0);
            
            Processor processor = Manager.createProcessor(camera);
            
            processor.configure();
            while (processor.getState() != Processor.Configured)
            {
                Thread.sleep(100);
            }

            processor.setContentDescriptor(new ContentDescriptor(ContentDescriptor.RAW));

            TrackControl track[] = processor.getTrackControls();
            track[0].setFormat(new VideoFormat (VideoFormat.JPEG_RTP));

            processor.realize();
            while (processor.getState() != Processor.Realized)
            {
                Thread.sleep(100);
            }

            DataSource sourceFinal = processor.getDataOutput();

            String url= "rtp://"+ip+":"+porta+"/video";
            MediaLocator destino = new MediaLocator(url);

            DataSink sink = Manager.createDataSink(sourceFinal, destino);
            sink.open();
            sink.start();
            processor.start();            
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }   
   
}