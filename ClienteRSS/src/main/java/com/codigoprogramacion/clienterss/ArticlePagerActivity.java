package com.codigoprogramacion.clienterss;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import com.codigoprogramacion.clienterss.helpers.RSSFeedParser;
import com.codigoprogramacion.clienterss.models.FeedMessage;

import java.util.LinkedList;


/**
 * Created by jorge on 11/22/13.

 */
public class ArticlePagerActivity extends FragmentActivity implements ViewPager.OnPageChangeListener{


    public ArticlePagerActivity(){}
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static  int NUM_PAGES;
    private static  LinkedList<FeedMessage> feed;
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private FragmentPagerAdapter mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_pager);


        this.feed = new RSSFeedParser("/storage/sdcard0/rss.xml").readFeed();
        NUM_PAGES = feed.size();
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setOnPageChangeListener(this);
        //mPager.setOffscreenPageLimit(2);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        Log.d("CLIENTE RSS","datos feed, size:"+feed.size());

    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {


    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }



        @Override
        public Fragment getItem(int position) {
            Log.d("CLIENTE RSS","Position fragment "+position);
            FeedMessage fm = (FeedMessage)feed.get(position);
            ArticleFragment article = new ArticleFragment();

            Log.d("CLIENTE RSS",fm.getLink());
            article.setLink(fm.getLink());
            article.setTitle(fm.getTitle());
            article.setContent(fm.getDescription());
            article.setFecha(fm.getGuid());

            return article;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
