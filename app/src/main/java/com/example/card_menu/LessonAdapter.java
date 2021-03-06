package com.example.card_menu;

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

    public static final String EXTRA_BOOK = "com.example.card_menu.BOOK";
    public static final String EXTRA_LESSON = "com.example.card_menu.LESSON";

    private List<RecyclerItem> listItems;
    private Context mContext;

    public LessonAdapter(List<RecyclerItem> listItems, Context mContext) {
        this.listItems = listItems;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public LessonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        return new LessonAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final LessonAdapter.ViewHolder viewHolder, int i) {

        final RecyclerItem itemList = listItems.get(i);
        final int x = i;
        viewHolder.txtTitle.setText(itemList.getTitle());
        viewHolder.txtDescription.setText(itemList.getDescription());
        viewHolder.imgDisplay.setImageDrawable(itemList.getImage());
        viewHolder.txtOptionDigit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //display option menu
                PopupMenu popupMenu = new PopupMenu(mContext, viewHolder.txtOptionDigit);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.menu_item_save:
                                Toast.makeText(mContext, "Saved", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.menu_item_delete:
                                //delete item
                                listItems.remove(x);
                                notifyDataSetChanged();
                                Toast.makeText(mContext, "Deleted", Toast.LENGTH_LONG).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Clicked on book " + itemList.getBook() + ", lesson " + itemList.getLesson(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mContext, pdf_view.class);
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle;
        public TextView txtDescription;
        public TextView txtOptionDigit;
        public ImageView imgDisplay;
        public RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtOptionDigit = itemView.findViewById(R.id.txtOptionDigit);
            imgDisplay = itemView.findViewById(R.id.imgDisplay);
            parentLayout = itemView.findViewById(R.id.relative_layout);
        }
    }
}
