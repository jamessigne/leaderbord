package com.example.leaderbord.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.leaderbord.fragments.LearningFragment;
import com.example.leaderbord.fragments.SkillFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LearningFragment();
            case 1:
                return new SkillFragment();
        }
        return new LearningFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
