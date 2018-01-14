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

        TextView feedbackText = ((TextView)view.findViewById(R.id.feedback));
        String feedbackString = feedback.getFeedback();
        if (feedbackString != null && feedbackString.trim().length() > 0){
            feedbackText.setVisibility(View.VISIBLE);
            feedbackText.setText(feedbackString.trim());
        } else {
            feedbackText.setVisibility(View.GONE);
        }

        String feedbackComment = "";
        if (feedback.getComment() == null || feedback.getComment().trim().length() == 0){
            feedbackComment = "";
        } else {
            feedbackComment = '"' + feedback.getComment().trim() + '"';
        }
        TextView commentsText = ((TextView)view.findViewById(R.id.comments));
        if (feedbackComment.length() > 0){
            commentsText.setVisibility(View.VISIBLE);
            commentsText.setText(feedbackComment);
        } else {
            commentsText.setVisibility(View.GONE);
        }

        ((TextView)view.findViewById(R.id.job_name)).setText(feedback.getCompanyName());

        return view;
    }
}
