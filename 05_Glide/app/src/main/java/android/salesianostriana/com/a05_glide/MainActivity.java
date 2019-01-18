package android.salesianostriana.com.a05_glide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView = findViewById(R.id.imageView);

        Glide
                .with(this)
                .load("https://www.visitaubedaybaeza.com/wp-content/uploads/2017/10/SALVADOR-UBEDA.jpg")
                .into(imageView);


    }
}
