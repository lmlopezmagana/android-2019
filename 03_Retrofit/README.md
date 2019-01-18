
# 03 - Primer ejemplo de uso de Retrofit

Retrofit es una librería que nos permite crear con mucha facilidad clientes Restful.

Para poder usar retrofit, tenemos que añadir la siguiente dependencia gradle:

```gradle
implementation 'com.squareup.retrofit2:retrofit:2.5.0'
```

En nuestro caso, utilizaremos retrofit junto a Gson, una librería de Google para el tratamiento de JSON. Por tanto, tenemos que añadir dos entradas más:

```gradle
implementation 'com.google.code.gson:gson:2.8.5'
implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
```

Retrofit nos permite transformar un API HTTP en un interfaz Java:

```java
public interface GitHubService {
  @GET("users/{user}/repos")
  Call<List<Repo>> listRepos(@Path("user") String user);
}
```

La clase `Retrofit` es capaz de generar una implementación para esta interfaz:

```java
Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("https://api.github.com")
    .addConverterFactory(GsonConverterFactory.create())
    .build();

GitHubService service = retrofit.create(GitHubService.class);
```

Cada `Call` obtenida desde `GitHubService` puede hacer una petición síncrona o asíncrona.

```java
Call<List<Repo>> repos = service.listRepos("octocat");
```

Los resultados obtenidos los vamos a mostrar en un `ListActivity`:

```java
public class MainActivity extends ListActivity {

}
```

Para ello, asignamos un adaptador que muestre las lista de datos obtenidos:

```java
public class MainActivity extends ListActivity {

    public void cargarDatos(List<Repo> list) {
            setListAdapter(
                    new ArrayAdapter<Repo>(
                            this,
                            android.R.layout.simple_list_item_1,
                            list
                    )
            );
    }
}
```