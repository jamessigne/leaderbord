package com.example.leaderbord.services;

import com.example.leaderbord.models.Hours;
import com.example.leaderbord.models.Skill;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GadsService {
    public static String url = "https://gadsapi.herokuapp.com/";

    @GET("api/hours")
    Call<List<Hours>> getHoursLeaders();

    @GET("api/skilliq")
    Call<List<Skill>> getSkillIqLeaders();
}
