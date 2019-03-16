package com.example.biblelessonviewer;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    //strings that are used when packing data into intents to pass data between activities
    public static final String EXTRA_BOOK = "com.example.biblelessonviewer.BOOK";
    public static final String EXTRA_LESSON = "com.example.biblelessonviewer.LESSON";

    //variables, list of items created and context of the current activity
    private List<ImageItem> listItems;
    private Context mContext;

    //constructor, sets values of listItems and mContext
    public ImageAdapter(List<ImageItem> listItems, Context mContext) {
        this.listItems = listItems;
        this.mContext = mContext;
    }

    //inflates ImageItem to current activity view
    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_item, viewGroup, false);
        return new ImageAdapter.ViewHolder(v);
    }

    //Binds values to ImageItem
    @Override
    public void onBindViewHolder(@NonNull final ImageAdapter.ViewHolder viewHolder, int i) {

        final ImageItem itemList = listItems.get(i);
        viewHolder.txtTitle.setText(itemList.getTitle());
        viewHolder.imgDisplay.setImageDrawable(itemList.getImage());
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onClick packs book and lesson numbers into intent and starts the ImageViewer
                Intent intent = new Intent(mContext, ImageViewer.class);
                intent.putExtra(EXTRA_BOOK, itemList.getBook());
                intent.putExtra(EXTRA_LESSON, itemList.getLesson());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    //ViewHolder class to bind activity element to ImageItem object
    public class ViewHolder extends RecyclerView.ViewHolder {

        //variables
        public TextView txtTitle;
        public ImageView imgDisplay;
        public RelativeLayout parentLayout;

        //constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            imgDisplay = itemView.findViewById(R.id.imgDisplay);
            parentLayout = itemView.findViewById(R.id.relative_layout);
        }
    }
}
