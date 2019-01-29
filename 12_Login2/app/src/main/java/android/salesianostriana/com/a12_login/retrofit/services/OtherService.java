package android.salesianostriana.com.a12_login.retrofit.services;

import android.salesianostriana.com.a12_login.model.ResponseContainer;
import android.salesianostriana.com.a12_login.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OtherService {


    @GET("/users")
    Call<ResponseContainer<User>> listUsers();

    @GET("/users/{id}")
    Call<User> getUser(@Path("id") Long id);

}
