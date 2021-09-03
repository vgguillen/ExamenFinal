package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue;
    RecyclerView recyclerRevista;
    Adapter revistaAdapter;
    List<ModeloRevista> revistam;
    String url = "https://revistas.uteq.edu.ec/ws/journals.php";
    Intent intent = new Intent(MainActivity.this, RevistasConDetalles.class);
    Bundle bundle = new Bundle();



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
                        revistam = new ArrayList<>();
                        for (int i = 0; i < size; i++){
                            try {
                                JSONObject jsonObject = new JSONObject(response.get(i).toString());

                                String journal_id = jsonObject.getString("journal_id");
                                String portada = jsonObject.getString("portada");
                                String abbreviation = jsonObject.getString("abbreviation");
                                String description = jsonObject.getString("description");
                                String journalThumbnail = jsonObject.getString("journalThumbnail");
                                String name = jsonObject.getString("name");

                                revistam.add(new ModeloRevista("Journal ID: "+journal_id,portada,abbreviation,description,journalThumbnail,name));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        revistaAdapter = new Adapter(revistam,getApplicationContext());
                        recyclerRevista.setAdapter(revistaAdapter);
                        revistaAdapter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Log.d("hola", revistam.get(recyclerRevista.getChildAdapterPosition(v)).getJournal_id());
                                bundle.putString("id",revistam.get(recyclerRevista.getChildAdapterPosition(v)).getJournal_id());
                                intent.putExtras(bundle);
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