package com.mobcomp.gesturepro;

import android.gesture.Gesture;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class GestureSelectActivity extends AppCompatActivity {

    private List<GestureItem> gestureList = new ArrayList<>();
    private RecyclerView recyclerView;
    private GestureListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mAdapter = new GestureListAdapter(gestureList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        prepareGestureData();

    }

    void prepareGestureData(){
        gestureList.add(new GestureItem("Gift", "https://www.signingsavvy.com/media/mp4-ld/23/23781.mp4"));
        gestureList.add(new GestureItem("Car", "https://www.signingsavvy.com/media/mp4-ld/26/26165.mp4"));
        gestureList.add(new GestureItem("Pay", ""));
        gestureList.add(new GestureItem("Pet", ""));
        gestureList.add(new GestureItem("Sell", ""));
        gestureList.add(new GestureItem("Explain", ""));
        gestureList.add(new GestureItem("That", ""));
        gestureList.add(new GestureItem("Book", ""));
        gestureList.add(new GestureItem("Now", ""));
        gestureList.add(new GestureItem("Work",""));
        gestureList.add(new GestureItem("Total", ""));
        gestureList.add(new GestureItem("Trip", ""));
        gestureList.add(new GestureItem("Future", ""));
        gestureList.add(new GestureItem("Good", ""));
        gestureList.add(new GestureItem("Thank you", ""));
        gestureList.add(new GestureItem("Agent", ""));
        gestureList.add(new GestureItem("Should", ""));
        gestureList.add(new GestureItem("Like", ""));
        gestureList.add(new GestureItem("Movie",""));

        mAdapter.notifyDataSetChanged();
    }
}
