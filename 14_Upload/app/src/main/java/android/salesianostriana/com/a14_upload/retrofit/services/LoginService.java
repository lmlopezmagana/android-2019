package android.salesianostriana.com.a14_upload.retrofit.services;


import android.salesianostriana.com.a14_upload.model.LoginResponse;
import android.salesianostriana.com.a14_upload.model.Registro;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface LoginService {

    @POST("/auth")
    Call<LoginResponse> doLogin(@Header("Authorization") String authorization);

    // @POST("/auth")
    // Call<LoginResponse> doLogin();


    // @POST("/users")
    // Call<LoginResponse> doRegister(@Query("access_token") String access_token,
    //                               @Body Registro registro);

    @POST("/users")
    Call<LoginResponse> doRegister(@Body Registro registro);

    @Multipart
    @POST("/users")
    Call<LoginResponse> doRegister(@Part MultipartBody.Part avatar,
                                   @Part("email") RequestBody email,
                                   @Part("password") RequestBody password);




}
