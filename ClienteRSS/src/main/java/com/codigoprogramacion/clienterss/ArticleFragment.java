package com.codigoprogramacion.clienterss;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * Created by jorge on 11/22/13.
 */
public class ArticleFragment extends Fragment {

    TextView title,date,link;
    WebView wv;
    String tLink="",tTitle="";
    public ArticleFragment()
    {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_article, container, false);

        title =
        ((TextView)rootView.findViewById(R.id.articleTitle));
        title.setText(tTitle);
        date = ((TextView)rootView.findViewById(R.id.articleDate));
        //date.setText("123123");
        link = ((TextView)rootView.findViewById(R.id.articleLink));
        link.setText(tLink);
        wv = (WebView)rootView.findViewById(R.id.artContent);
        return rootView;
    }

    public void setTitle(String txt)
    {
        tTitle=txt;
    }

    public void setDate(String txt)
    {
       date.setText(txt);
    }

    public void setLink(String txt)
    {
        tLink = txt;
    }

    public void setContent(String txt)
    {
        wv.loadData(txt,null,"utf-8");
    }
}