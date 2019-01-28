package android.salesianostriana.com.a12_login;

import android.salesianostriana.com.a12_login.retrofit.generator.ServiceGenerator;
import android.salesianostriana.com.a12_login.model.LoginResponse;
import android.salesianostriana.com.a12_login.retrofit.services.LoginService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    EditText email, password;
    Button btn_login, btn_registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        btn_registro = findViewById(R.id.btn_registro);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email_txt = email.getText().toString();
                String password_txt = password.getText().toString();

                String credentials = email_txt + ":" + password_txt;

                final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);


                LoginService service = ServiceGenerator.createService(LoginService.class);
                Call<LoginResponse> call =
                        service.doLogin("lNeTI8waAqmpUZa7QSiLv53rqSnlsldv",
                                basic);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.code() != 201) {
                          // error
                            Log.e("RequestError", response.message());
                            Toast.makeText(MainActivity.this, "Error de petición", Toast.LENGTH_SHORT).show();
                        } else {
                            // exito
                            Toast.makeText(MainActivity.this, response.body().getToken(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e("NetworkFailure", t.getMessage());
                        Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });



        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
