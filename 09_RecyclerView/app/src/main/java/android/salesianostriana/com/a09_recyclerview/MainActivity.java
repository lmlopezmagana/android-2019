package android.salesianostriana.com.a09_recyclerview;

import android.os.AsyncTask;
import android.salesianostriana.com.a09_recyclerview.model.Repo;
import android.salesianostriana.com.a09_recyclerview.retrofit.generator.ServiceGenerator;
import android.salesianostriana.com.a09_recyclerview.retrofit.services.GitHubService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.util.List;
import java.util.ServiceConfigurationError;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    GitHubService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.listado);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        service = ServiceGenerator.createService(GitHubService.class);

        new LoadDataTask().execute("lmlopezmagana");

    }


    private class LoadDataTask extends AsyncTask<String, Void, List<Repo>> {

        @Override
        protected List<Repo> doInBackground(String... strings) {

            List<Repo> result = null;


            Call<List<Repo>> callRepos = service.listRepos(strings[0]);

            Response<List<Repo>> responseRepos = null;

            try {
                responseRepos = callRepos.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (responseRepos.isSuccessful()) {
                result = responseRepos.body();
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<Repo> repos) {
            if (repos != null) {
                //cargarDatos(repos);
                recyclerView.setAdapter(
                        new ListadoRepositoriosAdapter(MainActivity.this, repos)
                );
            }
        }


    }




}
