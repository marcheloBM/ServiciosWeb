package cl.duoc.android.serviciosweb;

import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class RestActivity extends AppCompatActivity {

    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);

        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new CustomAdapter(this, android.R.layout.simple_list_item_2);
        listView.setAdapter(adapter);

        new ProductoTask().execute();

    }

    private class ProductoTask extends AsyncTask<Void, Void, Producto[]> {

        private GsonHttpMessageConverter buildGson() {
            GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            gsonHttpMessageConverter.setGson(gson);
            return gsonHttpMessageConverter;
        }

        @Override
        protected Producto[] doInBackground(Void... voids) {
            //String url = "https://duocdaa.firebaseio.com/productos.json";
            String url = "https://inventario-df6cb.firebaseio.com/Productos.json";
            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();

            // Add the String message converter
            restTemplate.setMessageConverters( Arrays.asList(new HttpMessageConverter<?>[]{buildGson()}) );

            ResponseEntity<Producto[]> exchange = restTemplate.exchange(url, HttpMethod.GET, null, Producto[].class);
            return exchange.getBody();
        }

        @Override
        protected void onPostExecute(Producto[] productos) {
            super.onPostExecute(productos);
            RestActivity.this.adapter.addAll(productos);
            Toast.makeText(RestActivity.this, "cant. Productos:"+productos.length, Toast.LENGTH_LONG).show();
            Toast.makeText(RestActivity.this, productos[0].getNombre(), Toast.LENGTH_LONG).show();
        }
    }
}
