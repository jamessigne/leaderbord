package com.example.leaderbord.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.leaderbord.R;
import com.example.leaderbord.services.GadsService;
import com.example.leaderbord.services.SubmissionService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmitActivity extends AppCompatActivity {

    private Button btn_submit;
    private EditText first_name;
    private EditText last_name;
    private EditText email;
    private EditText github_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initView();
    }
    void initView(){
        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(v -> showConfirmation());
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        github_link = findViewById(R.id.github_link);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    void showConfirmation(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = ((android.app.Activity) this).getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.popup_confirmation,null);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        ImageView cancel_btn = dialogView.findViewById(R.id.close_btn);
        cancel_btn.setOnClickListener(v -> alertDialog.dismiss());
        Button valid_btn = dialogView.findViewById(R.id.confirm_button);
        valid_btn.setOnClickListener(v -> {
            sendData();
            alertDialog.dismiss();
        });
    }
    void sendData(){
        if (first_name.getText().toString().length() >2 && last_name.getText().toString().length() >2 &&
                email.getText().toString().length() >2 && github_link.getText().toString().length() >2){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SubmissionService.url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            SubmissionService service = retrofit.create(SubmissionService.class);
            service.submitForm(first_name.getText().toString(),
                    last_name.getText().toString(),
                    email.getText().toString(),
                    github_link.getText().toString()).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    showSuccess();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    showFail();
                    t.getStackTrace();
                }
            });
        }else {
            showFail();
        }
    }
    void showSuccess(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = ((android.app.Activity) this).getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.popup_sucess,null);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setOnDismissListener(dialog -> finish());
    }
    void showFail(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = ((android.app.Activity) this).getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.popup_fail,null);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}