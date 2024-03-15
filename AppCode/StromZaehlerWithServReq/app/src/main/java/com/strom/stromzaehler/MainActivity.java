package com.strom.stromzaehler;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        // Tworzenie obiektu RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);

        // Adres URL serwera, z którego chcemy pobrać tekst
        String url = "http://192.168.103.150:5000/data";

        // Tworzenie żądania typu StringRequest
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Odpowiedź z serwera
                        textView.setText(response); // Wyświetlenie otrzymanego tekstu na ekranie
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Obsługa błędów
                textView.setText("Error: " + error.getMessage());
            }
        });

        // Dodanie żądania do kolejki
        queue.add(stringRequest);
    }
}
