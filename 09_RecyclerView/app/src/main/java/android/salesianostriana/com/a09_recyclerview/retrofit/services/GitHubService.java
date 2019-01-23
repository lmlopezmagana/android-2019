package android.salesianostriana.com.a09_recyclerview.retrofit.services;

import android.salesianostriana.com.a09_recyclerview.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);


}
