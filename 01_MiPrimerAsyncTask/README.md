
# Ejemplo de creación de un `AsyncTask`

En este proyecto de ejemplo se crean dos clases que extienden a `AsyncTask`:

```java
private class MiPrimerAsyncTaskHulio extends AsyncTask<Integer, Void, List<Integer>> {


        @Override
        protected List<Integer> doInBackground(Integer... integers) {
            int cantidad = integers[0];
            List<Integer> result = new ArrayList<>();
            if (cantidad < 1)
                cantidad = 1;
            else if (cantidad > 5)
                cantidad = 5;
            Random r = new Random();

            for(int i=0; i < cantidad; i++)
                result.add(Integer.valueOf(r.nextInt(100)));

            return result;
        }

        @Override
        protected void onPostExecute(List<Integer> result) {
            numero.setText(result.get(0).toString() + ", ");
            for(int i = 1; i < result.size(); i++) {
                numero.setText(numero.getText().toString() + result.get(i).toString() + ", ");
            }
        }


    }
```

Para instanciar dicha clase, invocamos al método `execute()`:

```java
new MiPrimerAsyncTaskHulio().execute(n);
```