package com.example.android.bluetoothlegatt;

/**
 * Created by user on 2016-10-19.
 */

  import android.content.Context;
        import android.graphics.drawable.Drawable;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

        import java.util.ArrayList;

class MyAdapter extends BaseAdapter {

    Context context;
    int layoutRes;
    ArrayList<School> list;

    LayoutInflater inflater;

    public MyAdapter(Context context,
                     int layoutRes, ArrayList<School> list){
        this.context=context;
        this.layoutRes=layoutRes;
        this.list=list;

        inflater=LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return list.size() ;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, parent, false);
        }




        TextView name = (TextView) convertView.findViewById(R.id.name);

        School mem=list.get(position);

        name.setText(mem.getName());

        return convertView;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public Object getItem(int position) {
        return list.get(position) ;
    }




}
