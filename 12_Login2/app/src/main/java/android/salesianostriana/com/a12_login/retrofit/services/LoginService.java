package android.salesianostriana.com.a12_login.retrofit.services;

import android.salesianostriana.com.a12_login.model.LoginResponse;
import android.salesianostriana.com.a12_login.model.Registro;
import android.salesianostriana.com.a12_login.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginService {

    // @POST("/auth")
    // Call<LoginResponse> doLogin(@Query("access_token") String access_token,
    //                            @Header("Authorization") String authorization);

    @POST("/auth")
    Call<LoginResponse> doLogin();


    // @POST("/users")
    // Call<LoginResponse> doRegister(@Query("access_token") String access_token,
    //                               @Body Registro registro);

    @POST("/users")
    Call<LoginResponse> doRegister(@Body Registro registro);




}
