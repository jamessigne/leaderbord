package com.example.leaderbord.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.leaderbord.R;
import com.example.leaderbord.adapters.HoursAdapter;
import com.example.leaderbord.models.Hours;
import com.example.leaderbord.services.GadsService;
import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LearningFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearningFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View layout;
    private List<Hours> hoursList;
    private HoursAdapter adapter;
    private Skeleton skeleton;

    public LearningFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LearningFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LearningFragment newInstance(String param1, String param2) {
        LearningFragment fragment = new LearningFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_learning, container, false);
        return layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        RecyclerView recycler = layout.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        hoursList = new ArrayList<>();
        adapter = new HoursAdapter(getContext(),hoursList);
        recycler.setAdapter(adapter);
        skeleton = SkeletonLayoutUtils.applySkeleton(recycler, R.layout.item_hours, 5);
        skeleton.showSkeleton();

        getHoursList();

    }
    private void getHoursList(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GadsService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GadsService service = retrofit.create(GadsService.class);

        service.getHoursLeaders().enqueue(new Callback<List<Hours>>() {
            @Override
            public void onResponse(Call<List<Hours>> call, Response<List<Hours>> response) {
                assert response.body() != null;
                hoursList.addAll(response.body());
                adapter.notifyDataSetChanged();
                skeleton.showOriginal();
            }

            @Override
            public void onFailure(Call<List<Hours>> call, Throwable t) {
                Toast.makeText(getContext(), R.string.error_occured, Toast.LENGTH_SHORT).show();
            }
        });
    }
}