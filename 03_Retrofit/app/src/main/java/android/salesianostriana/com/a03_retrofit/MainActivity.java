package android.salesianostriana.com.a03_retrofit;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.salesianostriana.com.a03_retrofit.Repo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends ListActivity {


    GitHubService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

//        ListAdapter adapter = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                Arrays.asList("Item 1", "Item 2", "Item 3")
//        );
//
//
//        setListAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(GitHubService.class);

        new LoadDataTask().execute("lmlopezmagana");

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
