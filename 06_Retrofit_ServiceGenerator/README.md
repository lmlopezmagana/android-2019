
# 06 - Segundo ejemplo de uso de Retrofit

En este tutorial nos vamos a centrar en crear un generador de servicio para Retrofit. Está basado en el tutorial de la siguiente url: https://futurestud.io/tutorials/retrofit-2-creating-a-sustainable-android-client

En lugar de generar el objeto `Retrofit` cada `Activity` o `Fragment` donde lo vayamos a utilizar, lo vamos a generar en un lugar común (haciendo uso del patrón _Singleton_).

Creamos una nueva clase, llamada `ServiceGenerator`

```java
public class ServiceGenerator {

    private static final String BASE_URL = "https://api.github.com/";

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = null;

    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass) {

        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }
        return retrofit.create(serviceClass);
    }


}

```

Los pasos a seguir son:

1. Establecemos una URL base para todas la peticiones creadas con este generador de servicios:

```java
private static final String BASE_URL = "https://api.github.com/";
```

2. Creamos un objeto de tipo `Retrofit.Builder()`, añadiendo la URL base, el conversor, y todas las opciones que queramos establecer.

```java
private static Retrofit.Builder builder =
    new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create());
```

3. Por defecto, vamos a añadir la opción de _logging_ de peticiones y respuestas. Esto lo conseguimos a través de un interceptor. Para poder hacerlo, primero añadimos la librería correspondiente:

```gradle
dependencies {
    //...
    compile 'com.squareup.okhttp3:logging-interceptor:3.8.0'
}

```

Posteriormente, en la clase `ServiceGenerator` creamos el objeto que será capaz de interceptar peticiones y respuestas, y escribir en el log su contenido.

```java
private static HttpLoggingInterceptor logging =
    new HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY);
```

4. Creamos el _builder_ para el cliente de `OkHttpClient` que vamos a utilizar.

```java
private static OkHttpClient.Builder httpClient =
    new OkHttpClient.Builder();
```

5. Definimos la referencia al objeto `Retrofit` que vamos a crear después, _encajando todas las piezas_.

```java
private static Retrofit retrofit = null;
```

6. Por último, la implementación del método `createService`, que será capaz de crear el servicio (una sola instancia para todas las invocaciones que se hagan a este método, con la función de _logging_ añadida):


```java
public static <S> S createService(Class<S> serviceClass) {

    if (!httpClient.interceptors().contains(logging)) {
        httpClient.addInterceptor(logging);
        builder.client(httpClient.build());
        retrofit = builder.build();
    }
    return retrofit.create(serviceClass);
}
```


Para utilizarlo, el código en el `Activity` o `Fragment` correspondiente sería:

```java
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.github.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        service = retrofit.create(GitHubService.class);

        service = ServiceGenerator.createService(GitHubService.class);

```
