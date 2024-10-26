package cl.duoc.android.serviciosweb;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void irActividad(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    public void irRestWS(View v){
        irActividad(RestActivity.class);
    }

    public void onClick(View v) {
        //String url = "https://duocdaa.firebaseio.com/saludoDelDia.json?print=pretty";
        String url = "https://inventario-df6cb.firebaseio.com/nombre.json";
        new StringTask().execute(url);
    }

    private void mostrarToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    class StringTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0];

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();

            // Add the String message converter
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

            // Make the HTTP GET request, marshaling the response to a String
            String result = restTemplate.getForObject(url, String.class);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }

}
