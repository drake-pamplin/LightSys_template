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

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    //string that is used to pass the book number between activities
    public static final String EXTRA_BOOK = "com.example.biblelessonviewer.BOOK";

    //list of items and the context of the activity
    private List<RecyclerItem> listItems;
    private Context mContext;

    //BookAdapter constructor
    public BookAdapter(List<RecyclerItem> listItems, Context mContext) {
        this.listItems = listItems;
        this.mContext = mContext;
    }

    //called on ViewHolder created, adds a RecyclerItem's data to the View
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        return new ViewHolder(v);
    }

    //called on creation of RecyclerItem, sets RecyclerItem instance's variables
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        final RecyclerItem itemList = listItems.get(i);
        final int x = i;
        viewHolder.txtTitle.setText(itemList.getTitle());
        viewHolder.txtDescription.setText(itemList.getDescription());
        viewHolder.imgDisplay.setImageDrawable(itemList.getImage());
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onClick event packs the book data into the intent and starts LessonView
                Intent intent = new Intent(mContext, LessonView.class);
                intent.putExtra(EXTRA_BOOK, itemList.getBook());
                mContext.startActivity(intent);
            }
        });
    }

    //getItemCount function; no discernible use at the moment
    @Override
    public int getItemCount() {
        return listItems.size();
    }

    //ViewHolder class, connects viewport items to RecyclerItems
    public class ViewHolder extends RecyclerView.ViewHolder {

        //variables
        public TextView txtTitle;
        public TextView txtDescription;
        public ImageView imgDisplay;
        public RelativeLayout parentLayout;

        //constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            imgDisplay = itemView.findViewById(R.id.imgDisplay);
            parentLayout = itemView.findViewById(R.id.relative_layout);
        }
    }
}
