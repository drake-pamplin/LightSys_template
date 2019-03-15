package com.example.biblelessonviewer;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LessonView extends AppCompatActivity {

    public static final String EXTRA_BOOK ="com.example.biblelessonviewer.BOOK";
    public static final String EXTRA_LESSON ="com.example.biblelessonviewer.LESSON";
    public static final String EXTRA_PHOTOS = "com.example.biblelessonviewer.PHOTOS";
    public static final String EXTRA_ACTIVITY = "com.example.biblelessonviewer.ACTIVITY";

    private RecyclerView recyclerView;
    private LessonAdapter adapter;
    private List<RecyclerItem> listItems;

    private String[][] title = {
            {
                    "The Creation",
                    "God Creates the First Man and Woman",
                    "Sin Comes into the World",
                    "Cain and Abel",
                    "Noah and the Flood",
                    "The Birth of Isaac",
                    "Moses is Given the Ten Commandments",
                    "The Ten Commandments",
                    "A Sacrifice for Sin",
                    "Jesus' Birth is Announced",
                    "Jesus is Born",
                    "Jesus the Teacher",
                    "Jesus the Worker of Miracles",
                    "Jesus is Crucified",
                    "Jesus is Alive",
                    "Jesus Appears to His Disciples",
                    "Jesus Returns to Heaven",
                    "The Way Back to God",
                    "You Must be Born Again",
                    "Coming of the Holy Spirit",
                    "Walk in the Light",
                    "Jesus Gives Us New Life",
                    "Loving Others",
                    "God's Power, not Magic",
                    "God has Power over Evil Spirits",
                    "Satan's Opposition",
                    "The Forgiveness of God",
                    "Christian's Should always Pray",
                    "Life After Death",
                    "What is the Church?",
                    "How to Worship God",
                    "Jesus is Coming Again",
                    "Be Fruitful",
                    "Go and Preach"
            },
            { //book 1
                    "How Everything Began",
                    "Everything Man Needed",
                    "Sin Begins",
                    "The Results of Sin",
                    "Noah, a Righteous Man",
                    "The Flood",
                    "The Rainbow - God's Promise",
                    "The Tower of Babel",
                    "Righteous Job",
                    "A Time of Great Trouble",
                    "Job Suffers",
                    "Job is Restored",
                    "Abraham Obeys God",
                    "Lot's Choice",
                    "Abraham Rescues Lot",
                    "God's Promise to Abraham",
                    "Ishmael is Born",
                    "God's Promise Renewed",
                    "Abraham Prays for Sodom",
                    "God Tests Abraham",
                    "A Wife for Isaac",
                    "God Sends His Promised Son",
                    "Jesus Died for Us",
                    "Thomas Meets the Risen Jesus"
            },
            { //book 2
                    "Jacob the Deceiver",
                    "Jacob's Dream",
                    "Jacob and Laban",
                    "Jacob Meets God",
                    "Joseph's Dream",
                    "Joseph is Sold",
                    "Joseph the Slave in Egypt",
                    "Joseph in Prison",
                    "The King's Dream",
                    "Joseph Rules in Egypt",
                    "Joseph Reveals Himself to his Brothers",
                    "Jacob and Joseph Meet Again",
                    "Baby Moses",
                    "Moses and the Burning Bush",
                    "Moses Returns to Egypt",
                    "The Passover",
                    "God Saves Israelites at the Red Sea",
                    "Manna and Water in the Desert",
                    "The Laws of God",
                    "The Snake in the Desert",
                    "The Prophet like Moses",
                    "Jesus Speaks with Moses",
                    "Jesus Died for Us",
                    "Jesus is Alive Today"
            },
            { //book 3
                    "Joshua Fights the Amalekites",
                    "Twelve Israelites Visit the Promised Land",
                    "Israel Crosses the Jordan into the PRomised Land",
                    "The Walls of Jericho Fall Down",
                    "The Battle of Ai",
                    "The Judgement of Achan",
                    "God Lengthens One Day",
                    "The Leader Joshua Says Goodbye",
                    "Deborah Speaks for God",
                    "God Gives the Israelites Victory over Sisera",
                    "Jael Kills Sisera",
                    "Israel Celebrates",
                    "Gideon and the Angel of God",
                    "Gideon Destroys the Idols",
                    "Gideon's Army Drinks Water",
                    "Gideon's Three Hundred Defeat the Midianites",
                    "Samson the Strong Man",
                    "Samson and the Burning Foxes",
                    "Samson Loses his Gift of Strength",
                    "Samson Destroys the Philistines",
                    "Jesus Drives Out Evil",
                    "Jesus Drives Out Evil Men",
                    "Jesus is Alive after Death",
                    "A Soldier for God"
            },
            { //book 4
                    "A Family Flees from Famine",
                    "Naomi and Ruth Return to Israel",
                    "Ruth in the Harvest Field",
                    "Ruth and Boaz at the Threshing Floor",
                    "Boaz and the Elders of Bethlehem",
                    "Mary and the Angel of God",
                    "Hannah Prays to God",
                    "The Child Samuel in the House of God",
                    "Samuel Prays for Israel",
                    "Samuel Anoints Saul with Oil",
                    "Saul Tears Samuel's Robe",
                    "Jesus in the House of God",
                    "David the Brave Shepherd",
                    "David and the Giant Goliath",
                    "Saul Tries to Kill David",
                    "David Spares Saul's Life",
                    "David is Made King",
                    "David and Bathseba",
                    "A House for God",
                    "Jesus Come to Jerusalem",
                    "The Birds Feed Elijah",
                    "Elijah and the Fire of God",
                    "Elijah Goes to Heaven",
                    "Jesus Appears with Moses and Elijah"
            },
            { //book 5
                    "Naaman Visits Elisha's House",
                    "Naaman is Healed",
                    "Elisha and the Army of God",
                    "Elisha and the Blind Army",
                    "The Siege of Samaria",
                    "The Visit of the Four Lepers to the Syrian Camp",
                    "Jonah Flees from God",
                    "Jonah and the Great Fish",
                    "Jonah at Nineveh",
                    "Esther and the King",
                    "Mordecai Refuses to Bow Down",
                    "Esther's Feast",
                    "Daniel and His Friends",
                    "Daniel and the King of Babylon",
                    "The Image of Gold",
                    "The Furnace of Fire",
                    "Daniel Prays to God",
                    "Daniel in the Lions' Den",
                    "Nehemiah Before the Great King",
                    "Nehemiah Inspects the Ruined City",
                    "Building the Walls",
                    "Ezra Reads the Law",
                    "Jesus on the Cross",
                    "Jesus Shows the Way to Everlasting Life"
            },
            { //book 6
                    "Jesus our Teacher",
                    "The Two Houses",
                    "A Light Should Be Seen",
                    "Revenge",
                    "Praying To God",
                    "Evil In God's World",
                    "Children of God",
                    "The Lost Sheep",
                    "Forgiving Others",
                    "God's Reward",
                    "Be Prepared",
                    "Waiting for Jesus",
                    "Jesus is Baptised",
                    "Jesus Calls Helpers (Disciples)",
                    "A Man is Made Clean",
                    "A Paralysed Man Walks",
                    "A Withered Hand and Hard Hearts",
                    "Jesus Calms the Storm",
                    "A Woman is Healed",
                    "A Dead Child Lives",
                    "The Faith of a Foreigner",
                    "A Deaf and Dumb Man Hears and Speaks",
                    "Jesus Makes The Blind To See",
                    "Jesus' Power Over Satan"
            },
            { //book 7
                    "The Birth of Jesus",
                    "Jesus Turns Water into Wine",
                    "Jesus Speaks to Nicodemus",
                    "A Ruler Kneels Before Jesus",
                    "The Sick Man at the Pool",
                    "Jesus Feeds Five Thousand People",
                    "Jesus Walks on the Water",
                    "Jesus Heals a Blind Man",
                    "Jesus Calls Lazarus from Death",
                    "Jesus Dies on the Cross",
                    "Mary and Jesus at the Tomb",
                    "Jesus Appears to His Friends",
                    "Jesus Teaches Two Friends",
                    "The Son Among the Pigs",
                    "The Lost Son Comes Home",
                    "The Wealth of a Rich Man",
                    "The Beggar and the Rich Man",
                    "The Friend at the Door",
                    "Two Men in God's House",
                    "A Man Sows his Seed",
                    "The Seed Grows",
                    "Help for an Injured Man",
                    "The House Owner Comes Home",
                    "The Man Up a Tree"
            },
            { //book 8
                    "The Lord Jesus Returns to Heaven",
                    "The Coming of the Holy Spirit",
                    "Peter Begins to Preach",
                    "The First Church",
                    "Power to Heal",
                    "Giving to God",
                    "Stephen, Faithful unto Death",
                    "The Good News Spreads to Sudan",
                    "Peter's Vision",
                    "Peter Visits the House of Cornelius",
                    "Peter in Prison",
                    "Power Through Prayer",
                    "Paul is Changed",
                    "Ananias Visits Paul",
                    "The Church Sends Missionaries",
                    "The Missionary Message",
                    "God Guides Paul",
                    "Paul and Silas in Trouble at Philippi",
                    "Paul and the Unknown God",
                    "Conflicts at Corinth",
                    "Paul Faces Death",
                    "A Message for Kings",
                    "With God in Danger",
                    "Paul in Rome"
            }
    };

    String[][][] photos = {
            {
                    {"1","2","3"},
                    {"2","3"},
                    {"4"},
                    {"4","5"},
                    {"6","7"},
                    {"8"},
                    {"9"},
                    {"9","10"},
                    {"10","11","17"},
                    {"12"},
                    {"12","13"},
                    {"11","14"},
                    {"15"},
                    {"15","16","17","21"},
                    {"18"},
                    {"17","19"},
                    {"20"},
                    {"20","22","23"},
                    {"15","22","24"},
                    {"20","24","25"},
                    {"25","26"},
                    {"27","28"},
                    {"29"},
                    {"30"},
                    {"31"},
                    {"32"},
                    {"33"},
                    {"34"},
                    {"4","5","21","35"},
                    {"36"},
                    {"37"},
                    {"20","38"},
                    {"39"},
                    {"40"}
            },
            {
                    {"1"},
                    {"2"},
                    {"3"},
                    {"4"},
                    {"5"},
                    {"6"},
                    {"7"},
                    {"8"},
                    {"9"},
                    {"10"},
                    {"11"},
                    {"12"},
                    {"13"},
                    {"14"},
                    {"15"},
                    {"16"},
                    {"17"},
                    {"18"},
                    {"19"},
                    {"20"},
                    {"21"},
                    {"22"},
                    {"23"},
                    {"24"}
            }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_view);

        //create toolbar
        Toolbar my_toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);

        //add upwards navigation
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String book = intent.getStringExtra(BookAdapter.EXTRA_BOOK);

        if (book.equals("0")) {
            getSupportActionBar().setTitle("Good News");
        }
        else {
            getSupportActionBar().setTitle("Book " + book);
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        Context c = getApplicationContext();

        /* INPUT YOUR CODE BELOW
         * List items need the following:
         * Lesson <NUMBER> ( let the current code handle that )
         * Title ( populate "title" array above )
         * Image ( Download from website, follow naming convention!!! -> book 1 lesson 1 photo = "book_1_lesson_1.jpg"
         * Book number ( let current code handle this )
         * Lesson number ( let current code handle this )
         */

        int max_lessons;
        int array;
        String[][] photo_array;

        if (book.equals("0")) {
            max_lessons = 34;
            array = 0;
            photo_array = photos[array];
        }
        else {
            max_lessons = 24;
            array = 1;
            photo_array = photos[array];
        }

        for (int i = 0; i < max_lessons; i++) {
            listItems.add(new RecyclerItem(
                    "Lesson " + (i + 1),
                    title[Integer.parseInt(book)][i],
                    c.getResources().getDrawable(c.getResources().getIdentifier("book_" + book + "_lesson_" + (photo_array[i][0]), "drawable", c.getPackageName())),
                    book,
                    "" + (i + 1),
                    photo_array[i]));
        }

        //set adapter
        adapter = new LessonAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
    }

    //create overflow menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Intent intent = getIntent();
        String book = intent.getStringExtra(BookAdapter.EXTRA_BOOK);
        int num_lessons;

        if (book.equals("0")) {
            num_lessons = 34;
        }
        else {
            num_lessons = 24;
        }

        for (int i = 0; i < num_lessons; i++) {
            menu.add(Menu.NONE, i, Menu.NONE, "Lesson " + (i + 1));
        }

        return super.onCreateOptionsMenu(menu);
    }

    //handle menu item pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = getIntent();
        String book = intent.getStringExtra(BookAdapter.EXTRA_BOOK);
        int array;
        String[][] photo_array;

        if (book.equals("0")) {
            array = 0;
            photo_array = photos[array];
        }
        else {
            array = 1;
            photo_array = photos[array];
        }

        intent = new Intent(this, PdfView.class);
        intent.putExtra(EXTRA_ACTIVITY, "lesson view");

        switch (item.getItemId()) {
            case 0:
                //user chose the "Lesson1" item, open the about view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "1" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[0]);
                this.startActivity(intent);
                return true;

            case 1:
                //user chose the "Lesson2" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "2" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[1]);
                this.startActivity(intent);
                return true;
            case 2:
                //user chose the "Lesson3" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "3" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[2]);
                this.startActivity(intent);
                return true;
            case 3:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "4" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[3]);
                this.startActivity(intent);
                return true;
            case 4:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "5" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[4]);
                this.startActivity(intent);
                return true;
            case 5:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "6" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[5]);
                this.startActivity(intent);
                return true;
            case 6:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "7" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[6]);
                this.startActivity(intent);
                return true;
            case 7:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "8" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[7]);
                this.startActivity(intent);
                return true;

            case 8:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "9" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[8]);
                this.startActivity(intent);
                return true;
            case 9:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "10" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[9]);
                this.startActivity(intent);
                return true;
            case 10:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "11" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[10]);
                this.startActivity(intent);
                return true;
            case 11:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "12" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[11]);
                this.startActivity(intent);
                return true;
            case 12:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "13" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[12]);
                this.startActivity(intent);
                return true;
            case 13:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "14" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[13]);
                this.startActivity(intent);
                return true;
            case 14:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "15" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[14]);
                this.startActivity(intent);
                return true;
            case 15:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "16" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[15]);
                this.startActivity(intent);
                return true;
            case 16:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "17" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[16]);
                this.startActivity(intent);
                return true;
            case 17:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "18" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[17]);
                this.startActivity(intent);
                return true;
            case 18:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "19" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[18]);
                this.startActivity(intent);
                return true;
            case 19:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "20" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[19]);
                this.startActivity(intent);
                return true;
            case 20:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "21" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[20]);
                this.startActivity(intent);
                return true;
            case 21:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "22" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[21]);
                this.startActivity(intent);
                return true;
            case 22:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "23" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[22]);
                this.startActivity(intent);
                return true;
            case 23:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "24" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[23]);
                this.startActivity(intent);
                return true;
            case 24:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "25" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[24]);
                this.startActivity(intent);
                return true;
            case 25:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "26" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[25]);
                this.startActivity(intent);
                return true;
            case 26:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "27" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[26]);
                this.startActivity(intent);
                return true;
            case 27:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "28" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[27]);
                this.startActivity(intent);
                return true;
            case 28:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "29" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[28]);
                this.startActivity(intent);
                return true;
            case 29:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "30" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[29]);
                this.startActivity(intent);
                return true;
            case 30:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "31" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[30]);
                this.startActivity(intent);
                return true;
            case 31:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "32" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[31]);
                this.startActivity(intent);
                return true;
            case 32:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "33" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[32]);
                this.startActivity(intent);
                return true;
            case 33:
                //user chose the "instructions" item, open instructions view
                intent.putExtra(EXTRA_BOOK, book);
                intent.putExtra( EXTRA_LESSON, "34" );
                intent.putExtra(EXTRA_PHOTOS, photo_array[33]);
                this.startActivity(intent);
                return true;

            default:
                //If we got here, the user's action was not recognized.
                //Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
