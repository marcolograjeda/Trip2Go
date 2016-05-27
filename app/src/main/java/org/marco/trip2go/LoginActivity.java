package org.marco.trip2go;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;
import org.marco.trip2go.Volley.WebService;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private TextView txtNick, txtContraseña;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        txtNick=(TextView) findViewById(R.id.txtNick);
        txtContraseña=(TextView) findViewById(R.id.txtContraseña);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, String>params=new HashMap<String, String>();
                params.put("nick",txtNick.getText().toString());
                params.put("contrasena",txtContraseña.getText().toString());

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, WebService.autenticar, new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                            /*if (response.toString().equals("Tu nickname o contraseña no son validos")){
                                Toast.makeText(getApplicationContext(), "Nickname o contraseña no validos", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_LONG).show();
                            }*/
                        }catch (Exception ex){
                            Log.e("Response exeption ", ex.getMessage());
                        }
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("Error: Response ", error.getMessage());
                    }
                });
                WebService.getInstance(v.getContext()).addToRequestQueue(request);
            }
        });
    }
}
