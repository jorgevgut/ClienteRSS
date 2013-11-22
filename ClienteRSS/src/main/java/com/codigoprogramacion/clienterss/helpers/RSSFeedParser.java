package com.codigoprogramacion.clienterss.helpers;

/**
 * Created by jorge on 11/21/13.
 */

import android.util.Log;
import android.util.Xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import com.codigoprogramacion.clienterss.models.*;
import com.codigoprogramacion.clienterss.helpers.RssHandler;

import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;

public class RSSFeedParser {
    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String CHANNEL = "channel";
    static final String LANGUAGE = "language";
    static final String COPYRIGHT = "copyright";
    static final String LINK = "link";
    static final String AUTHOR = "author";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";
    static final String GUID = "guid";

    final String url;

    public RSSFeedParser(String feedUrl) {

        this.url = feedUrl;

    }

    public Feed readFeed() {
        Feed feed = null;
        try {
            boolean isFeedHeader = true;
            // Set header values intial to the empty string
            String description = "";
            String title = "";
            String link = "";
            String language = "";
            String copyright = "";
            String author = "";
            String pubdate = "";
            String guid = "";

            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            File xml = new File(url);
            FileInputStream fin = new FileInputStream(xml);
            InputStreamReader in = new InputStreamReader(fin);
            BufferedReader br = new BufferedReader(in);
            parser.setInput(br);
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "rss");

            /*Log.d("CLIENTE RSS","leyendo un tag :"+parser.getName());
            if(parser.getName().equals("rss"))
            {
                parser.nextTag();
                parser.require(XmlPullParser.START_TAG,null,"channel");
                Log.d("CLIENTE RSS","leyendo un tag :"+parser.getName());
                parser.nextTag();
                parser.require(XmlPullParser.START_TAG,null,"title");
                parser.next();
                Log.d("CLIENTE RSS","TITULO:"+parser.getText());

                parser.require(XmlPullParser.START_TAG,null,"item");
                //parser.nextTag();
            }*/

            //read the feed

            int act;
            String tag="";
            while((act=parser.next()) != XmlPullParser.END_DOCUMENT)
            {

                switch (act)
                {
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        break;

                    case XmlPullParser.TEXT:
                        if(tag.equals("title"))
                        {Log.d("CLIENTE RSS",parser.getText());
                        tag="";}
                        break;
                }



                /*
                String name = parser.getName();
                Log.d("CLIENTE RSS","leyendo un tag :"+name);
                    if(name.equals(ITEM))
                        if (isFeedHeader) {
                            isFeedHeader = false;
                            feed = new Feed(title, link, description, language,
                                    copyright, pubdate);
                        }

                if(name.equals(TITLE))
                        title = parser.getAttributeValue(null,name);

                if(name.equals(DESCRIPTION))
                        description = parser.getAttributeValue(null,name);
                if(name.equals(LINK))
                        link = parser.getAttributeValue(null,name);
                if(name.equals( GUID))
                        guid = parser.getAttributeValue(null,name);
                if(name.equals(LANGUAGE))
                        language = parser.getAttributeValue(null,name);
                if(name.equals(AUTHOR))
                        author = parser.getAttributeValue(null,name);
                if(name.equals(PUB_DATE))
                        pubdate = parser.getAttributeValue(null,name);
                if(name.equals(COPYRIGHT))
                        copyright = parser.getAttributeValue(null,name);

                */
            }

            in.close();
            // read the XML document




        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return feed;
    }


}