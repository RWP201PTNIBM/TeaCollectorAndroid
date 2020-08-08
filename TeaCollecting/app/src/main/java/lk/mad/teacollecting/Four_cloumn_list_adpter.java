package lk.mad.teacollecting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Four_cloumn_list_adpter extends ArrayAdapter<VisitDetails> {
    private LayoutInflater mInflater;
    private ArrayList<VisitDetails> visitDetails;
    private int mViewResourceId;

    public Four_cloumn_list_adpter(Context context, int textViewResourceId, ArrayList<VisitDetails> visitDetails) {
        super(context, textViewResourceId, visitDetails);
        this.visitDetails = visitDetails;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        VisitDetails visitDetail = visitDetails.get(position);

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
