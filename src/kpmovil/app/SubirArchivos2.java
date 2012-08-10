package kpmovil.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SubirArchivos2 extends Activity {
	
	Button btnSel, btnSub;
	TextView txtNomArch;
	ListView lstdir;
	String mat, con, arch;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subirarchivos);
        
        txtNomArch= (TextView) findViewById(R.id.archivo);
        
        mat = getIntent().getExtras().getString("mat");
        con= getIntent().getExtras().getString("con");
        arch= getIntent().getExtras().getString("arc");
        
        txtNomArch.setText(arch);
        
        lstdir= (ListView) findViewById(R.id.lstdir);
        
        btnSel= (Button) findViewById(R.id.btnsel);
        btnSub= (Button) findViewById(R.id.btnsubir);
        
        btnSel.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try{
					try {
						Intent i= new Intent(SubirArchivos2.this, OtroFileBrowserActivity.class);
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
        
        btnSub.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View arg0) {
				subir sub= new subir();
				sub.uploadFile(arch, mat, con);
				
				alerts();
				
				Intent i= new Intent(SubirArchivos2.this, SubirArchivos.class);
				i.putExtra("mat", mat);
				i.putExtra("con", con);
				startActivity(i);
			}
		});
        
    }
	
	public void alerts(){
		new AlertDialog.Builder(this)

	       .setTitle("Archivo Subido")
	       .setPositiveButton("OK", 

	         new DialogInterface.OnClickListener() {
	         
	          
	          public void onClick(DialogInterface dialog, int which) {

	          }
	          }).show();
	}

}