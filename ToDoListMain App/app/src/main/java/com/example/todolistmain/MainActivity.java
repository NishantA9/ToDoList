package com.example.todolistmain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TodoListFragment.TodoListFragmentListener {

    ArrayList<Items> mItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.rootView, new TodoListFragment(), "list-fragment")
                .commit();
    }

    @Override
    public void AddItem(Items item) {
       // we did some calcuation on data we made it capital
        // shreyahs SHREYASH
        String temp = item.getItemName().toUpperCase();

        item.setItemName(temp);
        Log.d("addItem function", "AddItem: "+item);

        TodoListFragment todoListFragment = (TodoListFragment) getSupportFragmentManager().findFragmentByTag("list-fragment");
        todoListFragment.setItemsList(item);

    }

    @Override
    public void clearItem() {
        //EMPTY THE LIST
        mItems.clear();
    }
}