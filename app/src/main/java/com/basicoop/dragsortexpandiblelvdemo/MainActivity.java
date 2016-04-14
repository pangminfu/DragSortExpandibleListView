package com.basicoop.dragsortexpandiblelvdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.basicoop.dragsortexpandiblelv.DragSortController;
import com.basicoop.dragsortexpandiblelv.DragSortExListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DragSortController mController;
    private DragSortExListView dslv;

    private List<ItemType> itemTypes = new ArrayList<ItemType>();
    private ExAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        dslv = (DragSortExListView) findViewById(R.id.dslv);

        mController = buildController(dslv);
        dslv.setFloatViewManager(mController);
        dslv.setOnTouchListener(mController);
        dslv.setDragEnabled(true);

        ItemType itemType= new ItemType();
        itemType.itemType = "type 1";
        itemType.itemName.add("item 1");
        itemType.itemName.add("item 2");
        itemType.itemName.add("item 3");
        itemType.itemName.add("item 4");
        itemTypes.add(itemType);

        ItemType itemType2= new ItemType();
        itemType2.itemType = "type 2";
        itemType2.itemName.add("item 1");
        itemType2.itemName.add("item 2");
        itemType2.itemName.add("item 3");
        itemType2.itemName.add("item 4");
        itemTypes.add(itemType2);

        ItemType itemType3= new ItemType();
        itemType3.itemType = "type 3";
        itemType3.itemName.add("item 1");
        itemType3.itemName.add("item 2");
        itemType3.itemName.add("item 3");
        itemType3.itemName.add("item 4");
        itemTypes.add(itemType3);

        adapter = new ExAdapter(itemTypes, this);
        dslv.setDropListener(onDrop);

        dslv.setAdapter(adapter);
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

    private DragSortExListView.DropListener onDrop = new DragSortExListView.DropListener() {
        @Override
        public void drop(int from, int to) {
            if (from != to) {
                ItemType itemType = adapter.getGroup(from);
                adapter.removeByPos(from);
                adapter.insert(itemType, to);
                adapter.notifyDataSetChanged();
            }
        }
    };

    public DragSortController buildController(DragSortExListView dslv) {
        // defaults are
        // dragStartMode = onDown
        // removeMode = flingRight
        DragSortController controller = new DragSortController(dslv);
        controller.setDragHandleId(R.id.iv_drag_handle);
        controller.setSortEnabled(true);
        controller.setDragInitMode(DragSortController.ON_DOWN);
        return controller;
    }
}
