package com.example.alexr.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.alexr.taskmanager.Services.MessageService;
import com.example.alexr.taskmanager.Services.ServiceFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        MessageService messageService = ServiceFactory.createService(MessageService.class);

        Call<String> call = messageService.getMessage();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                TextView message = (TextView)findViewById(R.id.message);
                message.setText(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                TextView message = (TextView)findViewById(R.id.message);
                message.setText("Could not retrieve latest message.");
            }
        });
    }

    public void GetStarted(View view){
        Intent intent = new Intent(this, IdeaListActivity.class);
        startActivity(intent);
    }
}
