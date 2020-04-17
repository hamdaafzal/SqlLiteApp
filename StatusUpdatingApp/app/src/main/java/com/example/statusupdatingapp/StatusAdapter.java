package com.example.statusupdatingapp;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class StatusAdapter extends FirestoreRecyclerAdapter<StatusModelClass, StatusAdapter.StatusView> {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public StatusAdapter(@NonNull FirestoreRecyclerOptions<StatusModelClass> options) {
        super(Objects.requireNonNull(options));
    }

    @Override
    protected void onBindViewHolder(@NonNull StatusView statusView, int i, @NonNull StatusModelClass statusModelClass) {

        statusView.nameTV.setText(statusModelClass.getName());
        statusView.statusTV.setText(statusModelClass.getStatus());
    }

    @NonNull
    @Override
    public StatusView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new StatusView(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_row,parent,false
        ));
    }

    public void startListening() {
    }

    class StatusView extends RecyclerView.ViewHolder
    {
        TextView nameTV,statusTV;
        public StatusView(@NonNull View itemView) {
            super(itemView);

            nameTV=itemView.findViewById(R.id.nameTV);
            statusTV=itemView.findViewById(R.id.statusTV);
        }
    }
}
