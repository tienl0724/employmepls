package com.example.tiara.employmepls;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiara on 2018-01-13.
 */

public class JobsAdapter extends ArrayAdapter<Job> {
    private List<Integer> selected = new ArrayList<>();

    public JobsAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(this.getContext()).inflate(R.layout.job_row, parent, false);
        }
        final Job job = getItem(position);
        if (job == null) {
            return view;
        }

        String title = job.getTitle();
        if (job.getType() != null && job.getType().length() > 0) {
            title += " (" + job.getType() + ")";
        }
        ((TextView) view.findViewById(R.id.job_title)).setText(title);
        ((TextView) view.findViewById(R.id.job_company)).setText(job.getCompany());
        ((TextView) view.findViewById(R.id.job_description)).setText(job.getDescription());
        ((TextView) view.findViewById(R.id.job_location)).setText(job.getLocation());
        ((TextView) view.findViewById(R.id.job_requirements)).setText(job.getRequirements());
        ((TextView) view.findViewById(R.id.job_salary)).setText(job.getSalary());
        ((TextView) view.findViewById(R.id.job_start_date)).setText(job.getStartDate());

        return view;
    }
}
