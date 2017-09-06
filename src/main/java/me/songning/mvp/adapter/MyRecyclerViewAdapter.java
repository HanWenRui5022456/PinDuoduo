package me.songning.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.songning.mvp.R;


/**
 * Created by ArUi on 2017/8/30.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{
    private Context context;
    private List<String> list;

    public MyRecyclerViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv.setText(list.get(position));
        holder.img.setImageResource(R.mipmap.ic_launcher);

    }

    @Override
    public int getItemCount() {


        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
          ImageView img;
          TextView tv;

        public ViewHolder(View itemView) {

            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.item_img);
            tv = (TextView) itemView.findViewById(R.id.item_text);
        }
    }
}



