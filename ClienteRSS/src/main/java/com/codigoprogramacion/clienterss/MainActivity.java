package com.codigoprogramacion.clienterss;

import android.app.Activity;
import android.content.Intent;

import android.app.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;



import com.codigoprogramacion.clienterss.helpers.DownloadRSS;
import com.codigoprogramacion.clienterss.helpers.RSSFeedParser;
import com.codigoprogramacion.clienterss.ArticlePagerActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        //Ejecución de codigo


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void openPager()
    {
        Intent i = new Intent(this,ArticlePagerActivity.class);
        //Intent i = new Intent(this,PagerTest.class);
        startActivity(i);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public class PlaceholderFragment extends Fragment implements View.OnClickListener
    {

        WebView mywebview;
        View root;
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            root = rootView;
            mywebview = (WebView)rootView.findViewById(R.id.myWebview);
            Log.d("CLIENTE RSS", "INICIANDO APP");

            Button btn = (Button)rootView.findViewById(R.id.initBtn);

            btn.setOnClickListener(this);

            return rootView;
        }

        @Override
        public void onClick(View view) {

            Intent i = new Intent(getActivity(),ArticlePagerActivity.class);
            //Intent i = new Intent(this,PagerTest.class);
            startActivity(i);
            //openPager();
            //DownloadRSSTask d = new DownloadRSSTask();
            //d.execute();
        }


        private class DownloadRSSTask extends AsyncTask<Void,Void,Void>
        {
            @Override
            protected Void doInBackground(Void... voids) {
                Log.d("CLIENTE RSS","test");
                //DownloadRSS.getRSSfromURL("http://codigoprogramacion.com/feed");
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {

                InputStream entrada;
                File xml;
                FileInputStream fileIn;
                final int bufferSize = 1024;
                try{

                    xml = new File("/storage/sdcard0/rss.xml");
                    fileIn = new FileInputStream(xml);

                    InputStreamReader in = new InputStreamReader(fileIn);
                    BufferedReader br = new BufferedReader(in);
                    String txt,total="";
                    while((txt = br.readLine())!=null)
                    {
                        total+=txt;
                        Log.d("CLIENTE RSS",txt);

                    }
                    br.close();
                    in.close();
                    Log.d("CLIENTE RSS",total);

                    /*mywebview.loadData(total, "text/html", "UTF-8");
                    RSSFeedParser p = new RSSFeedParser("/storage/sdcard0/rss.xml");
                    p.readFeed();*/




                }catch(Exception e){

                    e.printStackTrace();

                }
            }
        }
    }



}
