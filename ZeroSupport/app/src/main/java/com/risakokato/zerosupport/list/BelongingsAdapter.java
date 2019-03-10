package com.risakokato.zerosupport.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.risakokato.zerosupport.model.Belongings;
import com.risakokato.zerosupport.R;

import java.util.List;

public class BelongingsAdapter extends ArrayAdapter<Belongings> {

    private LayoutInflater layoutinflater;

    public BelongingsAdapter(Context context, int textViewResourceId, List<Belongings> objects) {
        super(context, textViewResourceId, objects);
        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Belongings belo= getItem(position);

        if (convertView == null) {
            convertView = layoutinflater.inflate(R.layout.layout_item_list, null);

        }
        TextView dateText = (TextView) convertView.findViewById(R.id.dateText);
        TextView belongingText = (TextView) convertView.findViewById(R.id.belongingsText);

        dateText.setText(belo.date);
        belongingText.setText(belo.belongings);

        return convertView;

    }
}
