package android.salesianostriana.com.miprimerasynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView numero;
    Button boton;
    EditText cantidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero = findViewById(R.id.numero);
        boton = findViewById(R.id.button);
        cantidad = findViewById(R.id.editCantidad);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //int n = Integer.parseInt(cantidad.getText().toString());
                //new MiPrimerAsyncTaskHulio().execute(n);

                new OtroAsyncTask().execute();
            }
        });



    }

    private class MiPrimerAsyncTaskHulio extends AsyncTask<Integer, Void, List<Integer>> {


        @Override
        protected List<Integer> doInBackground(Integer... integers) {
            int cantidad = integers[0];
            List<Integer> result = new ArrayList<>();
            if (cantidad < 1)
                cantidad = 1;
            else if (cantidad > 5)
                cantidad = 5;
            Random r = new Random();

            for(int i=0; i < cantidad; i++)
                result.add(Integer.valueOf(r.nextInt(100)));

            return result;
        }

        @Override
        protected void onPostExecute(List<Integer> result) {
            numero.setText(result.get(0).toString() + ", ");
            for(int i = 1; i < result.size(); i++) {
                numero.setText(numero.getText().toString() + result.get(i).toString() + ", ");
            }
        }


    }

    private class OtroAsyncTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected void onPostExecute(Integer integer) {
            numero.setText(integer.toString());
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            try {
                URL url = new URL("https://triana.salesianos.edu/wp-content/uploads/2017/09/Logo-horizontal-segundo-nivel.png");
                int tam = url.openConnection().getContentLength();
                return tam;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
