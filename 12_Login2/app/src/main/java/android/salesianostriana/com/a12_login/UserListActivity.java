package android.salesianostriana.com.a12_login;

import android.app.ListActivity;
import android.salesianostriana.com.a12_login.model.User;
import android.salesianostriana.com.a12_login.retrofit.generator.ServiceGenerator;
import android.salesianostriana.com.a12_login.retrofit.generator.TipoAutenticacion;
import android.salesianostriana.com.a12_login.retrofit.services.OtherService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OtherService service = ServiceGenerator.createService(OtherService.class,
                ServiceGenerator.jwtToken, TipoAutenticacion.JWT);

        Call<List<User>> callList =  service.listUsers();


        callList.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    cargarDatos(response.body());
                } else {
                    // Toast
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                    // Toast
            }
        });



    }

    public void cargarDatos(List<User> datos) {
        setListAdapter(new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, datos) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                User user = getItem(position);
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
                }
                TextView txtName = convertView.findViewById(android.R.id.text1);
                TextView txtEmail = convertView.findViewById(android.R.id.text2);
                txtName.setText(user.getName());
                txtEmail.setText(user.getEmail());
                return convertView;
            }
        });
    }
}
