package android.salesianostriana.com.a11_calculadoratest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText op1, op2;
    Button btn_suma, btn_resta;
    TextView result;
    Calculadora calculadora;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        op1 = findViewById(R.id.operando1);
        op2 = findViewById(R.id.operando2);
        btn_suma = findViewById(R.id.btn_suma);
        btn_resta = findViewById(R.id.btn_resta);
        result = findViewById(R.id.resultado);

        calculadora = new Calculadora();


        btn_suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(calculadora.suma(
                        op1.getText().toString(),
                        op2.getText().toString()));
            }
        });

        btn_resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(calculadora.resta(
                        op1.getText().toString(),
                        op2.getText().toString()
                ));
            }
        });


    }
}
