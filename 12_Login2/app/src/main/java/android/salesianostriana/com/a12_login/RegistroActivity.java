package android.salesianostriana.com.a12_login;

import android.salesianostriana.com.a12_login.model.LoginResponse;
import android.salesianostriana.com.a12_login.model.Registro;
import android.salesianostriana.com.a12_login.retrofit.generator.ServiceGenerator;
import android.salesianostriana.com.a12_login.retrofit.services.LoginService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {

    EditText editFullname, editEmail, editPassword;
    Button btn_registro, btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editFullname = findViewById(R.id.editNombre);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btn_registro = findViewById(R.id.btn_registro);

        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Recoger los datos del formulario
                String fullname = editFullname.getText().toString().trim();
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                Registro registro = new Registro(fullname, email, password);

                LoginService service = ServiceGenerator.createService(LoginService.class);

                Call<LoginResponse> loginReponseCall = service
                        .doRegister("lNeTI8waAqmpUZa7QSiLv53rqSnlsldv",
                                registro);

                loginReponseCall.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });

            }
        });


    }
}