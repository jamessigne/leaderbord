package com.example.leaderbord.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.leaderbord.R;
import com.example.leaderbord.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainActivity extends AppCompatActivity {

    // tab titles
    String[] titles = new String[]{"Learning Leaders", "Skill IQ Leaders", "Tickets"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }
    private void initView(){


        TabLayout tab_layout = findViewById(R.id.tab_layout);
        final ViewPager2 pager = findViewById(R.id.pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        pager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tab_layout, pager,
                (tab, position) -> tab.setText(titles[position])).attach();

        TextView submit = findViewById(R.id.submit);
        submit.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,SubmitActivity.class));
        });

    }
}