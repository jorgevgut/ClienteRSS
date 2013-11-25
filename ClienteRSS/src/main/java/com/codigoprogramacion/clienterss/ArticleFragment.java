package com.codigoprogramacion.clienterss;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
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
    WebView wview;

    String tLink="",tTitle="",tDate="",tContent="";
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
        date.setText(tDate);

        link = ((TextView)rootView.findViewById(R.id.articleLink));
        link.setText(tLink);

        wview = (WebView)rootView.findViewById(R.id.articleContent);
        wview.loadData(tContent, "text/html", "UTF-8");

        return rootView;
    }

    public void setTitle(String txt)
    {
        tTitle=txt;
    }

    public void setFecha(String txt)
    {
        tDate =txt;
    }

    public void setLink(String txt)
    {
        tLink = txt;
    }

    public void setContent(String txt)
    {
        tContent = txt;
    }
}