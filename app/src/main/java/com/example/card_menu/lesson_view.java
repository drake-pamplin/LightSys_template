package com.example.card_menu;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class lesson_view extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LessonAdapter adapter;
    private List<RecyclerItem> listItems;

    private String[][] title = {
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

        getSupportActionBar().setTitle("Book " + book);

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

        for (int i = 0; i < 24; i++) {
            listItems.add(new RecyclerItem(
                    "Lesson " + (i + 1),
                    title[Integer.parseInt(book) - 1][i],
                    c.getResources().getDrawable(c.getResources().getIdentifier("book_" + book + "_lesson_" + (i + 1), "drawable", c.getPackageName())),
                    book,
                    "" + (i + 1)));
        }

        //set adapter
        adapter = new LessonAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
    }
}
