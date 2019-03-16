package com.example.biblelessonviewer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.IOException;

public class ImageViewer extends AppCompatActivity {

    //activity that displays the selected image from ImageList

    //initialize ImageView variable
    ImageView imgDisplay;

    String[] booklink = {
            "A64560",
            "A65688",
            "A65689",
            "A65761",
            "A65762",
            "A65763",
            "A65764",
            "A65765",
            "A65766"
    };

    MediaPlayer mediaPlayer;

    public String link ="https://5fish.mobi/";
    public Menu menu;
    private WifiManager wifiManager;
    boolean wifi = false;
    boolean enter = true;
    boolean go = false;

    private BroadcastReceiver wifiStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                    WifiManager.WIFI_STATE_UNKNOWN);
            if (wifiStateExtra == WifiManager.WIFI_STATE_ENABLED) {
                wifi = true;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        //create toolbar
        Toolbar my_toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);

        //add upwards navigation
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //get intent from ImageList
        Intent intent = getIntent();

        //get book number and lesson number from intent
        String book = intent.getStringExtra(ImageAdapter.EXTRA_BOOK);
        String lesson = intent.getStringExtra(ImageAdapter.EXTRA_LESSON);

        //set activity title in toolbar
        getSupportActionBar().setTitle("Picture " + lesson);

        //get activity context
        Context c = getApplicationContext();

        //get ImageView in layout and set it to selected photo
        imgDisplay = findViewById(R.id.imgDisplay);
        imgDisplay.setImageDrawable(c.getResources().getDrawable(c.getResources().getIdentifier("book_" + book + "_lesson_" + lesson, "drawable", c.getPackageName())));

        int book_I = Integer.parseInt(book);
        String book_S = booklink[Integer.parseInt(book)];

        int lesson_I = Integer.parseInt(lesson);
        String lesson_S;
        if (lesson_I < 10 && book_I != 2) {
            lesson_S = "00" + lesson;
        } else {
            lesson_S = "0" + lesson;
        }

        link = link + book_S + "/low/" + book_S + "-" + lesson_S + ".mp3";

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiStateReceiver, intentFilter);
    }

    //create overflow menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(wifi)
        {
            getMenuInflater().inflate(R.menu.mp3_view_menu, menu);
        }
        return true;
    }

    //handle menu item pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_play:
                play();

                return true;

            case R.id.action_restart:
                restart();
                return true;


            default:
                //If we got here, the user's action was not recognized.
                //Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    public void onStop() {
        if (!enter || go)
        {
            enter = true;
            go = false;
            mediaPlayer.release();
        }
        unregisterReceiver(wifiStateReceiver);
        super.onStop();
    }

    public void play() {
        if (enter) {
            enter = false;
            go = true;
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(link);

                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
                mediaPlayer.prepareAsync();

            } catch (IOException e) {
                e.printStackTrace();
            }

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.release(); // finish current activity
                    enter = true;
                    go = false;
                }
            });
        }
        else {
            if (go) {
                go = false;
                mediaPlayer.pause();
            }
            else {
                go = true;
                mediaPlayer.start();
            }
        }
    }

    public void restart() {
        if (!enter)
        {
            enter = true;
            go = true;
            mediaPlayer.release();
            play();
        }
    }

    int location = 0;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!enter) {
            location = mediaPlayer.getCurrentPosition();
        }
        outState.putInt("spot", location);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        location = savedInstanceState.getInt("spot");

        if (location > 0) {
            enter = false;
            go = true;
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(link);

                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.seekTo(location);
                        mp.start();
                    }
                });
                mediaPlayer.prepareAsync();

            } catch (IOException e) {
                e.printStackTrace();
            }

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.release(); // finish current activity
                    enter = true;
                    go = false;
                }
            });
        }
    }
}
