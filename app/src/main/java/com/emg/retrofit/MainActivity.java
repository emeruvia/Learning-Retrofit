package com.emg.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.emg.retrofit.adapter.GitHubRepoAdapter;
import com.emg.retrofit.interfaces.GitHubClient;
import com.emg.retrofit.models.GitHubRepo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.repositories_lv)
    ListView repositoryLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //Retrofit builder
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        GitHubClient client = retrofit.create(GitHubClient.class);
        //user you want to search for
        Call<List<GitHubRepo>> call = client.reposForUser("emeruvia");
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                List<GitHubRepo> repos = response.body();
                for (GitHubRepo repo : repos) {
                    Log.d("Repository", repo.getName());
                }
                repositoryLV.setAdapter(new GitHubRepoAdapter(MainActivity.this, repos));
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error parsing api", Toast.LENGTH_SHORT).show();
                Log.d("Status", "failed");
            }
        });
    }
}
