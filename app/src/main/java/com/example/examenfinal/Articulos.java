package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Articulos extends AppCompatActivity {


    RequestQueue queue;
    RecyclerView recyclerRevista;
    AdapterArticulos revistaAdapter;
    List<ModeloArticulo> articulo;
    String url = "https://revistas.uteq.edu.ec/ws/pubs.php?i_id=";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        ConstruirRecycler();

    }



    public void ConstruirRecycler(){
        recyclerRevista = findViewById(R.id.Recycleview_id);
        recyclerRevista.setLayoutManager(new LinearLayoutManager(this));
        jsonArrayRequest();

    }

    private void jsonArrayRequest(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int size = response.length();
                        articulo = new ArrayList<>();
                        for (int i = 0; i < size; i++){
                            try {
                                JSONObject jsonObject = new JSONObject(response.get(i).toString());

                                String title = jsonObject.getString("title");
                                String resumen = jsonObject.getString("abstract");
                                String doi = jsonObject.getString("doi");
                                String submission_id = jsonObject.getString("submission_id");


                                articulo.add(new ModeloArticulo(title,resumen,doi,submission_id));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        revistaAdapter = new AdapterArticulos(articulo,getApplicationContext());
                        recyclerRevista.setAdapter(revistaAdapter);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(jsonArrayRequest);
    }
}