package com.qttx.myfloatingbar;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);


        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));


        RecyclerView.Adapter adapter = new MyRecyclerviewAdapter();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);
    }

    //==================================================内部类

    private class MyRecyclerviewAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View myItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.communitydetails_item,parent,false);
            return new MyViewHolder(myItem);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
        }

        @Override
        public int getItemCount() {
            return 30;
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View myItem) {
            super(myItem);
        }
    }
}
