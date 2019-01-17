package android.salesianostriana.com.a02_navegadorweb;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editUrl;
    Button btn_ir;
    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Rescatamos los componentes de la UI

        editUrl = findViewById(R.id.editUrl);

        btn_ir = findViewById(R.id.btn_ir);

        webView = findViewById(R.id.webView);


        btn_ir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editUrl.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Escriba primero una URL", Toast.LENGTH_LONG);
                } else {
                    // Aquí deberíamos comprobar si la URL es una URL de internet (http[s]://.....)

                    new LoadWebTask().execute(editUrl.getText().toString());
                }
            }
        });


    }


    private class LoadWebTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return Downloader.downloadURL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("Contenido URL", s);
            webView.loadData(s,"text/html", "UTF-8");
        }
    }

}
