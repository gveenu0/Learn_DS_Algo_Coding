package com.softtechnotech.learndsalgocoding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterList extends ArrayAdapter<String> {
    Context context;
    ViewHolder viewHolder;
    ArrayList<String> al_pdf;

    public AdapterList(Context context, ArrayList<String> al_pdf) {

        super(context, R.layout.activity_adapter, al_pdf);
        this.context = context;
        this.al_pdf = al_pdf;

    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (al_pdf.size() > 0) {
            return al_pdf.size();
        } else {
            return 1;
        }
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.activity_adapter, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_filename = (TextView) view.findViewById(R.id.tv);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();

        }

        viewHolder.tv_filename.setText(al_pdf.get(position));
        return view;

    }

    public class ViewHolder {
        TextView tv_filename;
    }

}
