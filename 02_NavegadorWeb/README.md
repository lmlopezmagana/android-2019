# 02 - Navegador Web

Segundo ejemplo de utilización de AsyncTask. En este caso, para crear un navegador web mínimo.

El layout del ejercicio incluye la barra de direcciones, un botón de carga y un `WebView`

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <EditText
        android:id="@+id/editUrl"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:ems="10"
        android:inputType="textPersonName"
        android:text="Name"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/btn_ir"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:text="Ir"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@+id/editUrl"
        app:layout_constraintTop_toTopOf="parent" />

    <WebView
        android:id="@+id/webView"
        android:layout_width="368dp"
        android:layout_height="413dp"
        android:layout_marginStart="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginBottom="8dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/btn_ir" />
</android.support.constraint.ConstraintLayout>
```

Para descargar el contenido html, utilizamos una clase auxiliar, que mediante flujos y la clase `URL`se descarga el contenido:

```java
public class Downloader {

    public static String downloadURL(String strUrl) {

        String result = null;

        try {
            URL url = new URL(strUrl);
            BufferedInputStream bin = new BufferedInputStream(
                    url.openStream());
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int cantidad;
            while ((cantidad = bin.read(buffer, 0, 1024)) != -1) {
                baos.write(buffer, 0, cantidad);
            }

            bin.close();

            result = new String(baos.toByteArray(),"UTF-8");

            baos.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
```

Con esta clase, el AysncTask resultante es sencillo:

```java
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
```