package android.salesianostriana.com.a10_retrofitcallback;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.salesianostriana.com.a10_retrofitcallback.retrofit.generator.ServiceGenerator;
import android.salesianostriana.com.a10_retrofitcallback.retrofit.services.GitHubService;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends ListActivity {


    GitHubService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        service = ServiceGenerator.createService(GitHubService.class);

        //new LoadDataTask().execute("lmlopezmagana");

        Call<List<Repo>> call = service.listRepos("lmlopezmagana");

        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.isSuccessful()) {
                    cargarDatos(response.body());
                } else {
                    Toast.makeText(MainActivity.this, "Error al realizar la petición", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.e("Network Failure", t.getMessage());
                Toast.makeText(MainActivity.this, "Error al realizar la petición", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void cargarDatos(List<Repo> list) {
        setListAdapter(
                new ArrayAdapter<Repo>(
                        this,
                        android.R.layout.simple_list_item_1,
                        list
                )
        );
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
                cargarDatos(repos);
            }
        }


    }
}
