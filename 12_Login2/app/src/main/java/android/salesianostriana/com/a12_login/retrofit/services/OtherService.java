package android.salesianostriana.com.a12_login.retrofit.services;

import android.salesianostriana.com.a12_login.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OtherService {


    @GET("/users")
    Call<List<User>> listUsers();

}
