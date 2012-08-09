package kpmovil.app;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Menu extends Activity {
Button ver_archivos;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        ver_archivos=(Button) findViewById(R.id.btnIniciar);
        
        ver_archivos.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(Menu.this,ver_archivos.class);
				startActivity(i);
				
			}
		});
    
    }
}
