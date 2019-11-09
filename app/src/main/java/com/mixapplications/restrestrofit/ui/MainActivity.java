package com.mixapplications.restrestrofit.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.mixapplications.restrestrofit.R;
import com.mixapplications.restrestrofit.adapter.UsersListAdapter;
import com.mixapplications.restrestrofit.model.Page;

public class MainActivity extends AppCompatActivity {

    UsersModelView usersModelView ;
    RecyclerView recyclerView;
    UsersListAdapter usersListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        usersModelView = new UsersModelView();
        usersListAdapter = new UsersListAdapter(this , usersModelView);
        usersModelView.pageMutableLiveData.observe(this, new Observer<Page>() {
            @Override
            public void onChanged(Page page) {
                usersModelView.totalPages = page.getTotal_pages();
                usersListAdapter.addUsers(page.getData());
            }
        });
        recyclerView.setAdapter(usersListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
