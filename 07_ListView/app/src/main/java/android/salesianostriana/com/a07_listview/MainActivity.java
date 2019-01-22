package android.salesianostriana.com.a07_listview;

import android.salesianostriana.com.a07_listview.model.Owner;
import android.salesianostriana.com.a07_listview.model.Repo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.listView);


//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                Arrays.asList("Item 1", "Item 2", "Item 3")
//        );

        MyAdapter adapter =
                new MyAdapter(this,
                        Arrays.asList(
                                new Repo(166039059,"android-2019",
                                        "lmlopezmagana/android-2019",
                                        new Owner("https://avatars2.githubusercontent.com/u/34097584?v=4")),
                                new Repo(166039059,"android-2019",
                                        "lmlopezmagana/android-2019",
                                        new Owner("https://avatars2.githubusercontent.com/u/34097584?v=4")),
                                new Repo(166039059,"android-2019",
                                        "lmlopezmagana/android-2019",
                                        new Owner("https://avatars2.githubusercontent.com/u/34097584?v=4")),
                                new Repo(166039059,"android-2019",
                                        "lmlopezmagana/android-2019",
                                        new Owner("https://avatars2.githubusercontent.com/u/34097584?v=4"))



                        ));

        listView.setAdapter(adapter);

    }
}
