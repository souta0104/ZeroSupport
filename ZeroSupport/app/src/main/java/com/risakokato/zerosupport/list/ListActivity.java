
package com.risakokato.zerosupport.list;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.risakokato.zerosupport.model.Belongings;
import com.risakokato.zerosupport.R;
import com.risakokato.zerosupport.new_content.NewContentActivity;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ListActivity extends AppCompatActivity {

    public Realm realm;

    public ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        realm = Realm.getDefaultInstance();

        listView = (ListView) findViewById(R.id.listView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


                Intent intent = new Intent(ListActivity.this, NewContentActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
                builder.setMessage("削除しますか？")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ListView list = (ListView) listView;
                                BelongingsAdapter adapter = (BelongingsAdapter) list.getAdapter();

                                Belongings belongings = (Belongings) list.getItemAtPosition(position);
                                adapter.remove(belongings);

                                realm.beginTransaction();

                                Belongings belongings1 = realm.where(Belongings.class).equalTo("updateDate", belongings.updateDate).findFirst();
                                belongings1.deleteFromRealm();

                                realm.commitTransaction();

                            }
                        })
                        .setNegativeButton("キャンセル", null)
                        .setCancelable(true);
                // show dialog
                builder.show();
                return false;
            }
        });
    }
    public void setListView(){

        RealmResults<Belongings> results = realm.where(Belongings.class).findAll();
        results = results.sort("date");
        List<Belongings> items = realm.copyFromRealm(results);

        BelongingsAdapter adapter = new BelongingsAdapter(this, R.layout.layout_item_list, items);

        listView.setAdapter(adapter);
    }

    @Override
    protected  void onResume(){
        super.onResume();

        setListView();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();

        realm.close();
    }
}
