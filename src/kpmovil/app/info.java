package kpmovil.app;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class info extends Activity {
Button btnVerA;
Button btnSubirA;
TextView nombre;

String mat="";
String con="";
private TextView matricula;
private TextView saldo;
private TextView total;
private ImageView imageView;
private Bitmap loadedImage;
String urlImagen="";
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        
        mat=this.getIntent().getExtras().getString("mat");
        con=this.getIntent().getExtras().getString("con");
        
        
        imageView=(ImageView) findViewById(R.id.foto);
        nombre=(TextView) findViewById(R.id.nombre);
        matricula=(TextView) findViewById(R.id.matricula);
        saldo=(TextView) findViewById(R.id.saldo);
        total=(TextView) findViewById(R.id.total);
        
        btnVerA=(Button) findViewById(R.id.VerA);
        btnSubirA=(Button) findViewById(R.id.SubirA);
       
        
        btnVerA.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(info.this,ver_archivos.class);
				i.putExtra("mat", mat);
				i.putExtra("con", con);
				startActivity(i);
				
			}
		});
        
btnSubirA.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(info.this,SubirArchivos.class);
				i.putExtra("mat", mat);
				i.putExtra("con", con);
				startActivity(i);
				
			}
		});


String xml = XMLfunctions.getXML("http://kp.utch.edu.mx/kmov.php?mat="+mat+"&con="+con+"");

Document doc = XMLfunctions.XMLfromString(xml);

NodeList nodes = doc.getElementsByTagName("Nombre");

if((nodes.getLength() <=0))
{
	nombre.setText("no hay resultados");
}
else
{

	Element e=(Element) nodes.item(0);
	if(e.getNodeName().equals("Nombre"))
	{
		nombre.setText("Nombre: "+e.getAttribute("data"));
	}

  
	} 

String xml1 = XMLfunctions.getXML("http://kp.utch.edu.mx/kmov.php?mat="+mat+"&con="+con+"");
Document doc1 = XMLfunctions.XMLfromString(xml1);
NodeList matricul = doc.getElementsByTagName("Matricula");
Element e1=(Element) matricul.item(0);
matricula.setText("Matrícula: "+e1.getAttribute("data")); 

String xml2 = XMLfunctions.getXML("http://kp.utch.edu.mx/kmov.php?mat="+mat+"&con="+con+"");
Document doc2 = XMLfunctions.XMLfromString(xml2);
NodeList sald = doc.getElementsByTagName("Saldo");
Element e2=(Element) sald.item(0);
saldo.setText("Saldo: "+e2.getAttribute("data")); 



String xml3 = XMLfunctions.getXML("http://kp.utch.edu.mx/kmov.php?mat="+mat+"&con="+con+"");
Document doc3 = XMLfunctions.XMLfromString(xml3);
NodeList tota = doc.getElementsByTagName("Totala");
Element e3=(Element) tota.item(0);
total.setText("Total: "+e3.getAttribute("data")); 

String xml4 = XMLfunctions.getXML("http://kp.utch.edu.mx/kmov.php?mat="+mat+"&con="+con+"");
Document doc4 = XMLfunctions.XMLfromString(xml4);
NodeList ima = doc.getElementsByTagName("Imagen");
Element e4=(Element) ima.item(0);
urlImagen=e4.getAttribute("data");

URL imageUrl = null;
try {
    imageUrl = new URL(urlImagen+".jpg");
    HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
    conn.connect();
    
    loadedImage = BitmapFactory.decodeStream(conn.getInputStream());
    imageView.setImageBitmap(loadedImage);
} catch (IOException e) {
    Toast.makeText(getApplicationContext(), "Error cargando la imagen: "+e.getMessage(), Toast.LENGTH_LONG).show();
    e.printStackTrace();
}

    }
}
