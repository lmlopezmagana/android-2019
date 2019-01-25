package android.salesianostriana.com.a12_login.retrofit.services;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginService {

    @POST("/auth")
    Call<LoginReponse> doLogin(@Query("access_token") String access_token,
                               @Header("Authorization") String authorization);


}
