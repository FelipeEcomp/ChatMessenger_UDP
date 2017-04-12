package Video;;

import javax.media.*;
import javax.media.control.*;
import javax.media.format.*;
import javax.media.protocol.*;
import javax.media.*;
import javax.media.control.*;
import javax.media.format.*;
import javax.media.protocol.*;
import javax.media.*;
import javax.media.control.*;
import javax.media.format.*;
import javax.media.protocol.*;
import javax.media.*;
import javax.media.control.*;
import javax.media.format.*;
import javax.media.protocol.*;

public class EmissorRTPAudio
{
    private String ip;
    private String porta;
    //ip e porta do destino
    public EmissorRTPAudio(String ip, String porta){
        this.ip = ip;
        this.porta = porta;
        
    }
    public void start(){
        try{
            //configurando o microfone como a origem
            String arquivo = "javasound://44100";

            MediaLocator localizacao = null;
            DataSource dataSource = null;
            Processor processor = null;

            localizacao = new MediaLocator (arquivo);
            dataSource = Manager.createDataSource(localizacao);
            processor = Manager.createProcessor(dataSource);

            processor.configure();

            while (processor.getState() != Processor.Configured)
            {
                Thread.sleep(100);
            }

            processor.setContentDescriptor(new ContentDescriptor(ContentDescriptor.RAW));

            TrackControl track[] = processor.getTrackControls();
            track[0].setFormat(new AudioFormat(AudioFormat.MPEG_RTP));

            processor.realize();
            while (processor.getState() != Processor.Realized)
            {
                Thread.sleep(100);
            }

            //Dados j� processados
            DataSource sourceFinal = processor.getDataOutput();

            String url = "rtp://"+ip+":"+porta+"/audio";
            MediaLocator destino = new MediaLocator(url);

            DataSink sink = Manager.createDataSink(sourceFinal, destino);
            sink.open();
            sink.start();
            processor.start();
        }
        catch (Exception exc)
        {
            System.err.println (exc.toString());
            System.exit(1);
        }
    }
}

