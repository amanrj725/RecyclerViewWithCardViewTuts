package com.learning.recyclerviewwithcardview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<NatureModel> objectList;
    private LayoutInflater inflater;
    private static final String TAG = "MyAdapter";

    public MyAdapter(Context context, List<NatureModel> objectList) {
        inflater = LayoutInflater.from(context);
        this.objectList = objectList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        Log.d(TAG,"Test_onCreateViewHolder");
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NatureModel current =  objectList.get(position);
        holder.setData(current,position);
        Log.d(TAG,"Test_onBindViewHolder "+ position);
        holder.setListeners();

    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView title;
        private ImageView imgThumb, imgDelete, imgCopy;
        private int position;
        private NatureModel currentObject;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            imgThumb = itemView.findViewById(R.id.image_thumb);
            imgDelete = itemView.findViewById(R.id.img_delete);
            imgCopy = itemView.findViewById(R.id.img_copy);
        }

        public void setData(NatureModel currentObject, int position) {
            this.title.setText(currentObject.getTitle());
            this.imgThumb.setImageResource(currentObject.getImageID());
            this.position = position;
            this.currentObject = currentObject;
        }

        public void setListeners() {
            imgCopy.setOnClickListener(MyViewHolder.this);
            imgDelete.setOnClickListener(MyViewHolder.this);
            imgThumb.setOnClickListener(MyViewHolder.this);

        }
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.img_delete: {
                    removeItem(position);
                    break;
                }

                case R.id.img_copy:
                {
                    AddItem(position,currentObject);
                    break;
                }

                default:
            }

        }

        private void AddItem(int position, NatureModel currentObject) {
            objectList.add(position,currentObject);
            notifyItemInserted(position);
            notifyItemRangeChanged(position,objectList.size());
           //notifyDataSetChanged(); // used with ListView // this does not apply any kind of animation like above two method.
        }

        private void removeItem(int position) {
            objectList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,objectList.size());
            //notifyDataSetChanged();
        }
    }
}
