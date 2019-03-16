package com.example.biblelessonviewer;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {

    //LessonAdapter to handle data between lesson RecyclerItems

    //strings to pack data into intent with
    public static final String EXTRA_BOOK = "com.example.biblelessonviewer.BOOK";
    public static final String EXTRA_LESSON = "com.example.biblelessonviewer.LESSON";
    public static final String EXTRA_PHOTOS = "com.example.biblelessonviewer.PHOTOS";

    //list of RecyclerItems and activity context
    private List<RecyclerItem> listItems;
    private Context mContext;

    //constructor
    public LessonAdapter(List<RecyclerItem> listItems, Context mContext) {
        this.listItems = listItems;
        this.mContext = mContext;
    }

    //connect layout item with RecyclerItem object
    @NonNull
    @Override
    public LessonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        return new LessonAdapter.ViewHolder(v);
    }


    //set RecyclerItem values
    @Override
    public void onBindViewHolder(@NonNull final LessonAdapter.ViewHolder viewHolder, int i) {

        final RecyclerItem itemList = listItems.get(i);
        viewHolder.txtTitle.setText(itemList.getTitle());
        viewHolder.txtDescription.setText(itemList.getDescription());
        viewHolder.imgDisplay.setImageDrawable(itemList.getImage());
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onClick to create intent and set book number, lesson number, and photo array related to lesson
                Intent intent = new Intent(mContext, PdfView.class);
                intent.putExtra(EXTRA_BOOK, itemList.getBook());
                intent.putExtra(EXTRA_LESSON, itemList.getLesson());
                intent.putExtra(EXTRA_PHOTOS, itemList.getPhotos());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    //ViewHolder class to set data for RecyclerItems
    public class ViewHolder extends RecyclerView.ViewHolder {

        //variables
        public TextView txtTitle;
        public TextView txtDescription;
        public ImageView imgDisplay;
        public RelativeLayout parentLayout;

        //constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //getting layout items
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            imgDisplay = itemView.findViewById(R.id.imgDisplay);
            parentLayout = itemView.findViewById(R.id.relative_layout);
        }
    }
}
