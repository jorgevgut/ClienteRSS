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
import java.util.LinkedList;

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

    public LinkedList<FeedMessage> readFeed() {
        LinkedList<FeedMessage> feeds = new LinkedList();
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

            //read the feed

            int act;
            String tag="";
            FeedMessage temp = new FeedMessage();
            boolean enterItems=false;
            while((act=parser.next()) != XmlPullParser.END_DOCUMENT)
            {

                switch (act)
                {
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        Log.d("CLIENTE RSS",tag);
                        if(tag.equals("item"))
                        {enterItems=true;}
                        break;

                    case XmlPullParser.TEXT:
                        if(tag.equals("title"))
                        {
                            temp.setTitle(parser.getText());

                        }
                        if(tag.equals("pubDate"))
                        {
                            temp.setGuid(parser.getText());
                        }
                        if(tag.equals("link"))
                        {
                            temp.setLink(parser.getText());
                        }
                        if(tag.equals("description"))
                        {
                            temp.setDescription(parser.getText());
                        }

                        tag="";
                        break;

                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item"))
                        {
                            feeds.push(temp);
                        }
                        break;
                }

            }

            in.close();
            // read the XML document




        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return feeds;
    }


}