package com.example.card_menu;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class RecyclerItem {

    private String title;
    private String description;
    private Drawable image;
    private String book_number;
    private String lesson_number;

    public RecyclerItem(String title, String description, Drawable image, String book_number, String lesson_number) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.book_number = book_number;
        this.lesson_number = lesson_number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getBook() {
        return book_number;
    }

    public void setBook(String book_number) {
        this.book_number = book_number;
    }

    public String getLesson() {
        return lesson_number;
    }

    public void setLesson(String lesson_number) {
        this.lesson_number = lesson_number;
    }
}
