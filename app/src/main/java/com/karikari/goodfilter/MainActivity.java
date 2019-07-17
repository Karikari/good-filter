package com.karikari.goodfilter;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.karikari.goodfilter.model.Item;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        SingleFilterWidget singleFilter = findViewById(R.id.pills);
        MultiFilterWidget multiFilterWidget = findViewById(R.id.multi_filtter);

        List<String> list = new ArrayList<>();
        list.add("Any");
        list.add("1+");
        list.add("2+");
        list.add("3+");
        list.add("4+");
        list.add("5+");
        list.add("6+");
        list.add("7+");
        list.add("8+");
        list.add("9+");
        list.add("10+");
        list.add("11+");
        //pillsWidgets.setStringValues(list);

        List<String> items = new ArrayList<>();
        items.add("All");
        items.add("Related");
        items.add("Water");
        items.add("Phones");
        items.add("Coke Bottles");
        items.add("Table and Chair");
        items.add("Love and Sex");
        items.add("Hate and Money");
        items.add("Mercy with Juctice");
        items.add("Football");
        items.add("Legendary");
        items.add("CAF");
        items.add("All be lies");

        List<Item> list1 = new ArrayList<>();
        list1.add(new Item("Captain America", R.drawable.star_black_hollow));
        list1.add(new Item("Iron Man", R.drawable.check));
        list1.add(new Item("Spider Man", R.drawable.star_black_hollow));
        list1.add(new Item("War Machine", R.drawable.check));
        list1.add(new Item("Black Widow", R.drawable.check));


        //singleFilter.setDefaultValue("All"); //Set a default value
        //singleFilter.setStringValues(items);
        singleFilter.setItemValues(list1);
        singleFilter.setOnFilterChangeListener(new SingleFilterWidget.FilterChangeListener() {
            @Override
            public void onFiltered(String v) {
                Log.d("MainActivity", v);
            }
        });

        singleFilter.setOnItemFilterChangeListener(new SingleFilterWidget.ItemFilterChangeListener() {
            @Override
            public void onFiltered(Item item) {

            }
        });


        //multiFilterWidget.setDefaultValues(itemdefault);
        //multiFilterWidget.setStringValues(items);
        multiFilterWidget.setStringValues(items);
        multiFilterWidget.setOnFilterChangeListener(new MultiFilterWidget.FilterChangeListener() {
            @Override
            public void onFiltered(String v) {
                Log.d("MainActivity", v);
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
