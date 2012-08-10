package kpmovil.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SubirArchivos extends Activity {
	
	Button btnSel, btnSub;
	TextView txtNomArch;
	ListView lstdir;
	String mat, con;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subirarchivos);
        
        mat= getIntent().getExtras().getString("mat");
        con= getIntent().getExtras().getString("con");
        
        txtNomArch= (TextView) findViewById(R.id.archivo);
        txtNomArch.setText("Seleccionar Archivo");
        
        lstdir= (ListView) findViewById(R.id.lstdir);
        
        btnSel= (Button) findViewById(R.id.btnsel);
        btnSub= (Button) findViewById(R.id.btnsubir);
        btnSub.setClickable(false);
        
        btnSel.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try{
					try {
						Intent i= new Intent(SubirArchivos.this, OtroFileBrowserActivity.class);
						i.putExtra("mat", mat);
						i.putExtra("con", con);
						startActivity(i);
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
				catch(Exception e){
					
				}
			}
		});
        
    }

}