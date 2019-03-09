package com.risakokato.zerosupport.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.risakokato.zerosupport.Model.Belongings;
import com.risakokato.zerosupport.R;

import java.util.List;

import io.realm.Realm;

public class MainAdapter extends ArrayAdapter<Belongings> {

    private LayoutInflater layoutinflater;
    private Realm realm;


    public MainAdapter(Context context, int textViewResourceId, List<Belongings> objects) {
        super(context, textViewResourceId, objects);
        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        realm = Realm.getDefaultInstance();
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {

        final Belongings belo = getItem(position);

        if (contentView == null) {
            contentView = layoutinflater.inflate(R.layout.layout_item_today, null);

        }

        CheckBox checkBox = (CheckBox) contentView.findViewById(R.id.check);
        checkBox.setText(" " + belo.belongings);

        if(belo.check == true){
            checkBox.setChecked(true);
        } else if (belo.check == false){
            checkBox.setChecked(false);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true) {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            belo.check = true;

                        }
                    });

                } else {

                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            belo.check = false;

                        }
                    });
                }
            }
        });

        return contentView;
    }
}
