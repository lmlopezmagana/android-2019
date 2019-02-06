package android.salesianostriana.com.a14_upload;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.salesianostriana.com.a14_upload.model.LoginResponse;
import android.salesianostriana.com.a14_upload.retrofit.generator.ServiceGenerator;
import android.salesianostriana.com.a14_upload.retrofit.services.LoginService;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

public class MainActivity extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 42;
    ImageView imageView;
    Uri uriSelected;
    Button btn_upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView = findViewById(R.id.imageView);
        btn_upload = findViewById(R.id.btn_upload);

        btn_upload.setEnabled(false);
        uriSelected = null;

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uriSelected != null) {

                    LoginService service = ServiceGenerator.createService(LoginService.class);

                    try {
                        InputStream inputStream = getContentResolver().openInputStream(uriSelected);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                        int cantBytes;
                        byte[] buffer = new byte[1024*4];

                        while ((cantBytes = bufferedInputStream.read(buffer,0,1024*4)) != -1) {
                            baos.write(buffer,0,cantBytes);
                        }


                        RequestBody requestFile =
                                RequestBody.create(
                                        MediaType.parse(getContentResolver().getType(uriSelected)), baos.toByteArray());


                        MultipartBody.Part body =
                            MultipartBody.Part.createFormData("avatar", "avatar", requestFile);


                        RequestBody email = RequestBody.create(MultipartBody.FORM, "luismi.lopez@salesianos.es");
                        RequestBody password = RequestBody.create(MultipartBody.FORM, "12345678");

                        Call<LoginResponse> callRegister = service.doRegister(body, email, password);

                        callRegister.enqueue(new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                if (response.isSuccessful()) {
                                    Log.d("Uploaded", "Éxito");
                                    Log.d("Uploaded", response.body().toString());
                                } else {
                                    Log.e("Upload error", response.errorBody().toString());
                                }
                            }

                            @Override
                            public void onFailure(Call<LoginResponse> call, Throwable t) {
                                Log.e("Upload error", t.getMessage());
                            }
                        });


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }
        });



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performFileSearch();
            }
        });
    }

    public void performFileSearch() {

        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.setType("image/*");

        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                Log.i("Filechooser URI", "Uri: " + uri.toString());
                //showImage(uri);
                Glide
                        .with(this)
                        .load(uri)
                        .into(imageView);
                uriSelected = uri;
                btn_upload.setEnabled(true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
