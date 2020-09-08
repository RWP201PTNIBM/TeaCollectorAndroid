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

public class Four_cloumn_list_adpter extends ArrayAdapter<VisitDetails> implements Filterable {
    private LayoutInflater mInflater;
    private ArrayList<VisitDetails> visitDetails;
    private int mViewResourceId;
    private ArrayList<VisitDetails> mFilteredList;

    public Four_cloumn_list_adpter(Context context, int textViewResourceId, ArrayList<VisitDetails> visitDetails) {
        super(context, textViewResourceId, visitDetails);
        this.visitDetails = visitDetails;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
        mFilteredList = visitDetails;
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredList = visitDetails;
                } else {
                    ArrayList<VisitDetails> filteredList = new ArrayList<>();
                    for (VisitDetails visitDetail : visitDetails) {
                        if ( visitDetail.getSup_Name().contains(charString)) {
                            filteredList.add(visitDetail);
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
                mFilteredList = (ArrayList<VisitDetails>) filterResults.values;
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

        VisitDetails visitDetail = mFilteredList.get(position);

        if (visitDetail != null) {
            TextView sup_id = (TextView) convertView.findViewById(R.id.textSupId);
            TextView sup_Name = (TextView) convertView.findViewById(R.id.textSupName);
            TextView wieght = (TextView) convertView.findViewById(R.id.textwieght);
            TextView noofbags = (TextView) convertView.findViewById(R.id.textteabags);

            if (sup_id != null) {
                sup_id.setText(Integer.toString(visitDetail.getSup_id()));
            }
            if (sup_Name != null) {
                sup_Name.setText((visitDetail.getSup_Name()));
            }
            if (wieght != null) {
                wieght.setText(Double.toString(visitDetail.getWieght()));
            }
            if (noofbags != null) {
                noofbags.setText(Integer.toString(visitDetail.getNoofbags()));
            }
        }

        return convertView;
    }

}
