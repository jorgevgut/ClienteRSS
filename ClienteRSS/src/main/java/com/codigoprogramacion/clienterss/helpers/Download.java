package com.codigoprogramacion.clienterss.helpers;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.util.Log;

/**
 * Created by jorch on 11/4/13.
 */
public class Download {

     public static String getRSSfromURL(String url)
     {
         HttpURLConnection con;
         URL miurl;
         InputStream entrada;
         try{
             miurl = new URL(url);
             con = (HttpURLConnection)miurl.openConnection();
             con.setReadTimeout(5000);
             con.setConnectTimeout(5000);
             con.setDoInput(true);
             con.setRequestMethod("GET");

             con.connect();
             entrada = con.getInputStream();

             //Lectura de rss
             final int size = 1024;
             byte[] buffer = new byte[size];

             ByteArrayOutputStream os = new ByteArrayOutputStream();
             int remaining,tmpSize;
             while(true)
             {
                 remaining = entrada.available();
                 if(remaining>0)
                 {
                     if(remaining>size)
                     {
                         tmpSize=size;
                     }
                     else {
                         tmpSize=remaining;
                     }
                     entrada.read(buffer,0,tmpSize);

                     os.write(buffer);
                 }
                 else {break;}

             }

             String rss = new String(os.toByteArray(),"UTF-8");
            os.close();
            entrada.close();

            return rss;
         }catch(Exception e){
             Log.d("CLIENTE RSS","Error en la conexion");
             e.printStackTrace();}

         Log.d("CLIENTE RSS","no se bajo la info");
         return "";
     }
}
