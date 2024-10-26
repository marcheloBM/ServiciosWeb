package cl.duoc.android.serviciosweb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Producto> {

    public CustomAdapter(Context context, int resource) {
        super(context, resource);
    }

    public CustomAdapter(Context context, int resource, Producto[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null) {
            v = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, null);
        }
        TextView tv1 = (TextView) v.findViewById(android.R.id.text1);
        TextView tv2 = (TextView) v.findViewById(android.R.id.text2);
        Producto p = this.getItem(position);
        tv1.setText(p.getNombre());
        tv2.setText(p.getCodigo());
        return v;
    }
}
