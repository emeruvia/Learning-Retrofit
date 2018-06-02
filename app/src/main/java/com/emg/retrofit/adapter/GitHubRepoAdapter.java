package com.emg.retrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.emg.retrofit.R;
import com.emg.retrofit.models.GitHubRepo;

import java.util.List;

/**
 * Created by emeruvia on 6/2/2018.
 */
public class GitHubRepoAdapter extends ArrayAdapter<GitHubRepo> {
    private Context context;
    private List<GitHubRepo> values;

    public GitHubRepoAdapter(Context context, List<GitHubRepo> values) {
        super(context, R.layout.list_item_adapter, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_adapter, parent, false);
        }
        TextView textView = row.findViewById(R.id.list_item_adapter);
        GitHubRepo item = values.get(i);
        String message = item.getName();
        textView.setText(message);
        return row;
    }
}
