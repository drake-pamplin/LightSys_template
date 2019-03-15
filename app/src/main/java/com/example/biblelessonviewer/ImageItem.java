package com.example.biblelessonviewer;

import android.graphics.drawable.Drawable;

public class ImageItem {

    private Drawable image;
    private String title;
    private String book;
    private String lesson;

    public ImageItem(Drawable image, String title, String book, String lesson) {
        this.image = image;
        this.title = title;
        this.book = book;
        this.lesson = lesson;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }
}