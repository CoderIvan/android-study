package com.test.listviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private List<Fruit> fruitList = new ArrayList<Fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFruits() {
        fruitList.add(new Fruit("Apple", R.drawable.apple_pic));
        fruitList.add(new Fruit("Banana", R.drawable.banana_pic));
        fruitList.add(new Fruit("Orange", R.drawable.orange_pic));
        fruitList.add(new Fruit("Watermelon", R.drawable.watermelon_pic));
        fruitList.add(new Fruit("Pear", R.drawable.pear_pic));
        fruitList.add(new Fruit("Grape", R.drawable.grape_pic));
        fruitList.add(new Fruit("Pineapple", R.drawable.pineapple_pic));
        fruitList.add(new Fruit("Strawberry", R.drawable.strawberry_pic));
        fruitList.add(new Fruit("Cherry", R.drawable.cherry_pic));
        fruitList.add(new Fruit("Mango", R.drawable.mango_pic));
    }
}
