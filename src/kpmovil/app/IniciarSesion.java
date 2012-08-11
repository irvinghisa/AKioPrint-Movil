package kpmovil.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IniciarSesion extends Activity {
Button ver_archivos;
private EditText mat;
private EditText con;
String str ="";
private TextView mensaje; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        ver_archivos=(Button) findViewById(R.id.btnIniciar);
        mat=(EditText) findViewById(R.id.txtMat);
        con=(EditText) findViewById(R.id.txtCon);
       mensaje=(TextView) findViewById(R.id.mensaje);
        ver_archivos.setOnClickListener(new OnClickListener() {
			
        	
        	 private StringBuilder inputStreamToString(InputStream is) {
        	     String line = "";
        	     StringBuilder total = new StringBuilder();
        	     // Wrap a BufferedReader around the InputStream
        	     BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        	     // Read response until the end
        	     try {
        	      while ((line = rd.readLine()) != null) { 
        	        total.append(line); 
        	      }
        	     } catch (IOException e) {
        	      e.printStackTrace();
        	     }
        	     // Return full string
        	     return total;
        	    }
        	 
			public void onClick(View v) {
				try {
					
				HttpClient client = new DefaultHttpClient();
	            HttpGet request = new HttpGet();
	            String url="http://kp.utch.edu.mx/loginmov.php?mat="+mat.getText().toString()+"&con="+con.getText().toString()+"";
	            Log.e("Tania", "Lo que trae lo que voy a solicitar"+url);
	            request.setURI(new URI(url));
	            HttpResponse response = client.execute(request);
	            
	            
	           
	            		str =inputStreamToString(response.getEntity().getContent()).toString();
	            Log.e("Tania", "Imprimiendo lo que tiene response"+str);
				
				} catch (Exception e) {
					Log.e("Tania", "Error al conectarse"+e.getMessage());
				}
	            if(str.equals("ok")){
	            Intent i = new Intent(IniciarSesion.this,Menu.class);
				i.putExtra("mat", mat.getText().toString());
				i.putExtra("con", con.getText().toString());
				mensaje.setText("");
				startActivity(i);
				
				finish();
	            }
	            else{
	            	mensaje.setText("Información incorrecta");
	            	
	            }
			}
		});
    }
}
