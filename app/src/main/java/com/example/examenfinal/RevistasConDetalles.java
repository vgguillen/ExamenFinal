package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

public class RevistasConDetalles extends AppCompatActivity {
    private String id;
    RequestQueue queue;
    RecyclerView recyclerRevista;
    AdapterArt revistaAdaptador;
    List<RevistaDetalleModelo> detallerevista;
    String url = "https://revistas.uteq.edu.ec/ws/issues.php?j_id=";
    Bundle bundle = this.getIntent().getExtras();
    Bundle b = new Bundle();
    Intent intent = new Intent(RevistasConDetalles.this, Articulos.class);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revistas_con_detalles);
        queue = Volley.newRequestQueue(this);
        ConstruirRecycler();
    }
    public void ConstruirRecycler(){
        recyclerRevista = findViewById(R.id.Recycleview_id);
        recyclerRevista.setLayoutManager(new LinearLayoutManager(this));

        id = bundle.getString("id");
        jsonArrayRequest(id);

    }
    private void jsonArrayRequest(String id){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url+ id,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int size = response.length();
                        detallerevista = new ArrayList<>();
                        for (int i = 0; i < size; i++){
                            try {
                                JSONObject jsonObject = new JSONObject(response.get(i).toString());

                                String issue_id = jsonObject.getString("issue_id");
                                String volume = jsonObject.getString("volume");
                                String number = jsonObject.getString("number");
                                String year = jsonObject.getString("year");
                                String date_published = jsonObject.getString("date_published");
                                String title = jsonObject.getString("title");
                                String doi = jsonObject.getString("doi");
                                String cover = jsonObject.getString("cover");

                                detallerevista.add(new RevistaDetalleModelo("Issue ID: "+issue_id,volume,number,year,date_published,title,doi,cover));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        revistaAdaptador = new AdapterArt(detallerevista, getApplicationContext());
                        recyclerRevista.setAdapter(revistaAdaptador);
                        revistaAdaptador.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Log.d("hola", revistam.get(recyclerRevista.getChildAdapterPosition(v)).getJournal_id());
                                b.putString("id",detallerevista.get(recyclerRevista.getChildAdapterPosition(v)).getIssue_id());
                                intent.putExtras(b);
                                startActivity(intent);
                            }
                        });

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