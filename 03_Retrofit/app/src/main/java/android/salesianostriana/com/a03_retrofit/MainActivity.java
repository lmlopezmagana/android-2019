package android.salesianostriana.com.a03_retrofit;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.Arrays;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        ListAdapter adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                Arrays.asList("Item 1", "Item 2", "Item 3")
        );


        setListAdapter(adapter);


    }
}
