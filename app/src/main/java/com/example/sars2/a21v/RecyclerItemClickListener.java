package com.example.sars2.a21v;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

/**
 * Created by sars2 on 10.12.2015.
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
