package kpmovil.app;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IniciarSesion extends Activity {
Button ver_archivos;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        ver_archivos=(Button) findViewById(R.id.verarchivos);
        
        ver_archivos.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(IniciarSesion.this,ver_archivos.class);
				startActivity(i);
				
			}
		});
    
    }
}
