package com.meevii.recycleviewdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyAdapter    mAdapter;
    private int size = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setInitialPrefetchItemCount(0);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }


    public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            Log.d("MyAdapter", "onCreateViewHolder");
            return new MyHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recy, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
            myHolder.textView.setText(String.valueOf(i + 1));
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position, @NonNull List<Object> payloads) {
            super.onBindViewHolder(holder, position, payloads);
        }

        @Override
        public int getItemCount() {
            Log.d("MyAdapter", "getItemCount");
            return size;
        }

        @Override
        public void onViewAttachedToWindow(@NonNull MyHolder holder) {
            super.onViewAttachedToWindow(holder);
            Log.d("onViewAttachedToWindow", "onViewAttachedToWindow");
        }
    }

    private class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;

        MyHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.num);
        }
    }
}
