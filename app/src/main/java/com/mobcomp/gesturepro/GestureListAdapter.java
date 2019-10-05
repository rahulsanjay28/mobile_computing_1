package com.mobcomp.gesturepro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GestureListAdapter extends RecyclerView.Adapter<GestureListAdapter.MyViewHolder> {
    private List<GestureItem> gestureList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView gestureName;
        String url;
        private final Context context;

        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            gestureName = (TextView) view.findViewById(R.id.txtName);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, GesturePlayActivity.class);
                    intent.putExtra("EXTRA_URL", url);
                    context.startActivity(intent);
                }
            });
        }
    }

    public GestureListAdapter(List<GestureItem> gestureList){
        this.gestureList = gestureList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GestureItem gestureItem = gestureList.get(position);
        holder.gestureName.setText(gestureItem.getGestureName());
        holder.url = gestureItem.getUrl();
    }

    @Override
    public int getItemCount() {
        return gestureList.size();
    }

}
