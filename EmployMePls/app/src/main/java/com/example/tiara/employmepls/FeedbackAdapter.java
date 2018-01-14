package com.example.tiara.employmepls;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tiara on 2018-01-14.
 */

public class FeedbackAdapter extends ArrayAdapter<Feedback> {


    public FeedbackAdapter(Context context, int resource, List<Feedback> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(this.getContext()).inflate(R.layout.feedback_row, parent, false);
        }

        Feedback feedback = getItem(position);
        if (feedback == null){
            return view;
        }

        ((TextView)view.findViewById(R.id.feedback)).setText(feedback.getFeedback());
        ((TextView)view.findViewById(R.id.job_name)).setText(feedback.getJobName());

        return view;
    }
}
