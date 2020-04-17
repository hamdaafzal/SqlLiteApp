package com.example.statusupdatingapp;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

abstract class FirestoreRecyclerAdapter<T, T1> {
    public FirestoreRecyclerAdapter(FirestoreRecyclerOptions<StatusModelClass> requireNonNull) {
        
    }

    protected abstract void onBindViewHolder(@NonNull StatusAdapter.StatusView statusView, int i, @NonNull StatusModelClass statusModelClass);

    @NonNull
    public abstract StatusAdapter.StatusView onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    public abstract void stopListening();
}
