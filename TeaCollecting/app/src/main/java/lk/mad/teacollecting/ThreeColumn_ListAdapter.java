package lk.mad.teacollecting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mitch on 2016-05-06.
 */
public class ThreeColumn_ListAdapter extends ArrayAdapter<Supplier> {

    private LayoutInflater mInflater;
    private ArrayList<Supplier> suppliers;
    private int mViewResourceId;

    public ThreeColumn_ListAdapter(Context context, int textViewResourceId, ArrayList<Supplier> suppliers) {
        super(context, textViewResourceId, suppliers);
        this.suppliers = suppliers;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        Supplier supplier = suppliers.get(position);

        if (supplier != null) {
            TextView sup_id = (TextView) convertView.findViewById(R.id.textSupId);
            TextView sup_Name = (TextView) convertView.findViewById(R.id.textSupName);
            TextView sup_Add = (TextView) convertView.findViewById(R.id.textSupAdd);
            if (sup_id != null) {
               sup_id.setText(Integer.toString(supplier.getSup_id()));
            }
            if (sup_Name != null) {
                sup_Name.setText((supplier.getSup_Name()));
            }
            if (sup_Add != null) {
                sup_Add.setText((supplier.getSup_Add()));
            }
        }

        return convertView;
    }

}
