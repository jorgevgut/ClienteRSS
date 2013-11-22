package com.codigoprogramacion.clienterss.helpers;


import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jorch on 11/4/13.
 */
public class DownloadRSS {

     public static String getRSSfromURL(String url)
     {
         HttpURLConnection conexion;
         URL miUrl;
         InputStream entrada;
         File xml;
         FileOutputStream fileOut;
         final int bufferSize = 1024;
         byte[] buffer;
         try{
             miUrl = new URL(url);
             conexion = (HttpURLConnection)miUrl.openConnection();
             conexion.setReadTimeout(10000);
             conexion.setConnectTimeout(10000);
             conexion.setDoInput(true);
             conexion.setRequestMethod("GET");
             conexion.connect();

             entrada = conexion.getInputStream();
             xml = new File("/storage/sdcard0/rss.xml");
             fileOut = new FileOutputStream(xml);

             int bytesAEscribir;
             buffer = new byte[bufferSize];
             while ((bytesAEscribir = entrada.read(buffer))>0)
             {
                 fileOut.write(buffer,0,bytesAEscribir);
             }
             entrada.close();
             fileOut.close();
             conexion.disconnect();
             Log.d("CLIENTE RSS","descargado");

         }catch(Exception e){
             e.printStackTrace();
         }

         return null;
     }
}
