
# 07 - Ejemplo de `ListView` con adaptador personalizado

En este proyecto de ejemplo se va a utilizar código de los anteriores ejemplos para listar el resultado (del supuesto consumo de un endpoint del api de github) en un `Listview` con adaptador personalizado.

En primer lugar añadimos el listado al layout, que podía quedar así:

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <ListView
        android:id="@+id/listView"
        android:layout_width="368dp"
        android:layout_height="495dp"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginBottom="8dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
</android.support.constraint.ConstraintLayout>
```

Lo siguiente es rescatar dicho componente para poder usarlo en la interfaz de usuario. A partir de ahí, nos centramos en el adaptador. Android nos ofrece varias alternativas, y nosotros en este caso escogemos la más sencilla, que es basarnos en `ArrayAdapter<>`.

Creamos una nueva clase, que extienda a `ArrayAdapter<Repo>`, donde `Repo` es una clase de nuestro modelo.

Nos centramos en sobrescribir el método que gestiona la visualización de cada item, así como en la gestión del constructor.


```java
public class MyAdapter extends ArrayAdapter<Repo> {

    public MyAdapter(Context mContext, List<Repo> data) {
        super(mContext, R.layout.list_item, data);
    }

    // Resto de métodos y atributos
}
```

La clase va a recibir dos elementos:

* El contexto, necesario para poder (entre otras cosas) inflar el layout, pintar imágenes con Glide, etc...
* El conjunto de datos con el que se va a rellenar el adaptador.

Dentro del cuerpo, utilizamos el constructor de la clase base, añadiendo como parámetro el layout específico a usar. Dicho layout lo tenemos a continuación:

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        app:layout_constraintEnd_toStartOf="@+id/guideline"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@mipmap/ic_launcher" />

    <TextView
        android:id="@+id/text1"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="16dp"
        android:layout_marginLeft="16dp"
        android:layout_marginTop="24dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:text="TextView"
        android:textSize="18sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="@+id/guideline"
        app:layout_constraintTop_toTopOf="parent" />

    <android.support.constraint.Guideline
        android:id="@+id/guideline"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        app:layout_constraintGuide_percent="0.25" />

    <TextView
        android:id="@+id/text2"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="16dp"
        android:layout_marginLeft="16dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:text="TextView"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="@+id/guideline"
        app:layout_constraintTop_toBottomOf="@+id/text1" />
</android.support.constraint.ConstraintLayout>
```

El método que gestiona la visualización de cada elemento es `getView(...)`:

```java
public class MyAdapter extends ArrayAdapter<Repo> {



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);

        ImageView img = item.findViewById(R.id.imageView);
        TextView text1 = item.findViewById(R.id.text1);
        TextView text2 = item.findViewById(R.id.text2);

        Repo repo = getItem(position);

        text1.setText(repo.getName());
        text2.setText(repo.getFull_name());

        Glide
                .with(getContext())
                .load(repo.getOwner().getAvatar_url())
                .into(img);

        return item;
    }
}

```

Los pasos que se dan son los siguientes:

1. Inflar la vista correspondiente.
2. Una vez inflada la vista, rescatamos los diferentes componentes de la interfaz de usuario que vamos a utilizar.
3. Le asignamos información a estos (la imagen la cargamos usando la librería Glide).


En el `Activity` principal, creamos el adaptador y lo asignamos con una colección de datos _dummy_:

```java
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
```