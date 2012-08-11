package kpmovil.app;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Menu extends Activity {
Button btnVerA;
Button btnSubirA;
Button btnInfoU;
String mat="";
String con="";
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        mat=this.getIntent().getExtras().getString("mat");
        con=this.getIntent().getExtras().getString("con");
        
        btnVerA=(Button) findViewById(R.id.btnVerA);
        btnSubirA=(Button) findViewById(R.id.btnSubirA);
        btnInfoU=(Button) findViewById(R.id.btnInfoU);
        
        btnVerA.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(Menu.this,ver_archivos.class);
				i.putExtra("mat", mat);
				i.putExtra("con", con);
				startActivity(i);
				
			}
		});
btnSubirA.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(Menu.this,SubirArchivos.class);
				i.putExtra("mat", mat);
				i.putExtra("con", con);
				startActivity(i);
				
			}
		});
btnInfoU.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(Menu.this,info.class);
				i.putExtra("mat", mat);
				i.putExtra("con", con);
				startActivity(i);
				
				
			}
		});
    }
}
