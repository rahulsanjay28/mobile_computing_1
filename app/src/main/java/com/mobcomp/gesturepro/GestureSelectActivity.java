package com.mobcomp.gesturepro;

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
        gestureList.add(new GestureItem("Pay", "https://www.signingsavvy.com/media/mp4-ld/14/14618.mp4"));
        gestureList.add(new GestureItem("Pet", "https://www.signingsavvy.com/media/mp4-ld/7/7515.mp4"));
        gestureList.add(new GestureItem("Sell", "https://www.signingsavvy.com/media/mp4-ld/9/9199.mp4"));
        gestureList.add(new GestureItem("Explain", "https://www.signingsavvy.com/media/mp4-ld/22/22623.mp4"));
        gestureList.add(new GestureItem("That", "https://www.signingsavvy.com/media/mp4-ld/14/14366.mp4"));
        gestureList.add(new GestureItem("Book", "https://www.signingsavvy.com/media/mp4-ld/14/14326.mp4"));
        gestureList.add(new GestureItem("Now", "https://www.signingsavvy.com/media/mp4-ld/7/7774.mp4"));
        gestureList.add(new GestureItem("Work","https://www.signingsavvy.com/media/mp4-ld/14/14523.mp4"));
        gestureList.add(new GestureItem("Total", "https://www.signingsavvy.com/media/mp4-ld/26/26467.mp4"));
        gestureList.add(new GestureItem("Trip", "https://www.signingsavvy.com/media/mp4-ld/9/9117.mp4"));
        gestureList.add(new GestureItem("Future", "https://www.signingsavvy.com/media/mp4-ld/14/14736.mp4"));
        gestureList.add(new GestureItem("Good", "https://www.signingsavvy.com/media/mp4-ld/21/21534.mp4"));
        gestureList.add(new GestureItem("Thank you", "https://www.signingsavvy.com/media/mp4-ld/21/21533.mp4"));
        gestureList.add(new GestureItem("Learn", "https://www.signingsavvy.com/media/mp4-ld/21/21560.mp4"));
        gestureList.add(new GestureItem("Agent", "https://www.signingsavvy.com/media/mp4-ld/10/10000.mp4"));
        gestureList.add(new GestureItem("Should", "https://www.signingsavvy.com/media/mp4-ld/9/9563.mp4"));
        gestureList.add(new GestureItem("Like", "https://www.signingsavvy.com/media/mp4-ld/6/6394.mp4"));
        gestureList.add(new GestureItem("Movie","https://www.signingsavvy.com/media/mp4-ld/8/8626.mp4"));

        mAdapter.notifyDataSetChanged();
    }
}
