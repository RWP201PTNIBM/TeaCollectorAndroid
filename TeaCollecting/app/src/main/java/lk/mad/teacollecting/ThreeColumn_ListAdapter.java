package lk.mad.teacollecting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;


public class ThreeColumn_ListAdapter extends ArrayAdapter<Supplier> implements Filterable {

    private LayoutInflater mInflater;
    private ArrayList<Supplier> suppliers;
    private ArrayList<Supplier> mFilteredList;

    private int mViewResourceId;

    public ThreeColumn_ListAdapter(Context context, int textViewResourceId, ArrayList<Supplier> suppliers) {
        super(context, textViewResourceId, suppliers);
        this.suppliers = suppliers;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
        mFilteredList = suppliers;
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredList = suppliers;
                } else {
                    ArrayList<Supplier> filteredList = new ArrayList<>();
                    for (Supplier supplier : suppliers) {
                        if ( supplier.getSup_Name().contains(charString)) {
                            filteredList.add(supplier);
                        }
                    }
                    mFilteredList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Supplier>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    @Override
    public int getCount(){
        return mFilteredList!=null ? mFilteredList.size() : 0;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        Supplier supplier = mFilteredList.get(position);

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
