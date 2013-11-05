package com.codigoprogramacion.clienterss.helpers;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import android.util.Log;

/**
 * Created by jorch on 11/4/13.
 */
public class DownloadRSS {

     public static String getRSSfromURL(String url)
     {
         HttpURLConnection con;
         URL miurl;
         String rss="def";
         InputStream entrada=null;
         try{
             miurl = new URL(url);
             con = (HttpURLConnection)miurl.openConnection();
             con.setReadTimeout(15000);
             con.setConnectTimeout(15000);
             con.setDoInput(true);
             con.setReadTimeout(15000);

             con.connect();

             entrada = con.getInputStream();

             Log.d("CLIENTE RSS","Termina configuración de conexión");
             //Lectura de rss
             final int size = 1024*1;

             byte[] buffer = new byte[size];
             ByteArrayOutputStream out = new ByteArrayOutputStream();

             while(entrada.read(buffer)>0)
             {
                 out.write(buffer,0,size);
             }



            out.close();
            entrada.close();
            con.disconnect();

            rss = new String(out.toByteArray(),"UTF-8");
            return rss;
         }catch(Exception e){
             Log.d("CLIENTE RSS 'ERROR'",e.getMessage());}

         Log.d("CLIENTE RSS","no se bajo la info");
         return rss;
     }
}
