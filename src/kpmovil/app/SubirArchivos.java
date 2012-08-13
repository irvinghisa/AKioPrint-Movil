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

public class SubirArchivos extends Activity {
	
	Button btnSel; 
	public static Button btnSub;
	public static TextView txtNomArch;
	ListView lstdir;
	String mat, con;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subirarchivos);
        
        mat= getIntent().getExtras().getString("mat");
        con= getIntent().getExtras().getString("con");
        
        txtNomArch= (TextView) findViewById(R.id.archivo);
        txtNomArch.setText("Seleccionar Archivo ");
        
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
        
btnSub.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View arg0) {
				subir sub= new subir();
				sub.uploadFile(txtNomArch.getText().toString(), mat, con);
				
				alerts();
				Ucambio.cambio="Se subió archivo: "+txtNomArch.getText().toString();
				
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