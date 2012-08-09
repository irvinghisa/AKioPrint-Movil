package kpmovil.app;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;



import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.CheckBox;



public class ver_archivos extends Activity {

private CheckBox a1;
private CheckBox a2;
private CheckBox a3;
private CheckBox a4;
private CheckBox a5;
private CheckBox a6;
private CheckBox a7;
private CheckBox a8;
private CheckBox a9;
private CheckBox a10;
private Button eli;
String comodinStr="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_archivos);
        
      
        
        a1=(CheckBox) findViewById(R.id.a1);
        a1.setVisibility(View.INVISIBLE);

        a2=(CheckBox) findViewById(R.id.a2);
        a2.setVisibility(View.INVISIBLE);
        
        a3=(CheckBox) findViewById(R.id.a3);
        a3.setVisibility(View.INVISIBLE);
        
        a4=(CheckBox) findViewById(R.id.a4);
        a4.setVisibility(View.INVISIBLE);
        
        a5=(CheckBox) findViewById(R.id.a5);
        a5.setVisibility(View.INVISIBLE);
        
        a6=(CheckBox) findViewById(R.id.a6);
        a6.setVisibility(View.INVISIBLE);
        Log.e("yo", "Se hizo invisible" );
        a7=(CheckBox) findViewById(R.id.a7);
        a7.setVisibility(View.INVISIBLE);
        
        a8=(CheckBox) findViewById(R.id.a8);
        a8.setVisibility(View.INVISIBLE);
        
        a9=(CheckBox) findViewById(R.id.a9);
        a9.setVisibility(View.INVISIBLE);
        
        a10=(CheckBox) findViewById(R.id.a10);
        a10.setVisibility(View.INVISIBLE);

        eli=(Button) findViewById(R.id.btn_eliminar);
        
        
        
        String xml = XMLfunctions.getXML("http://kp.utch.edu.mx/kmov.php?mat=1111250221&con=11223344");
        
        Document doc = XMLfunctions.XMLfromString(xml);
        
        NodeList nodes = doc.getElementsByTagName("Archivos");
        
        
        
        if((nodes.getLength() <=0))
        {
        	a1.setText("no hay resultados");
        }
        else
        {
        	
        
        	
        	NodeList nodesI = nodes.item(0).getChildNodes();
        
        	//Log.e("yo", "numero de nodos: " + nodesI.getLength() );
        	for (int i = 0; i < nodesI.getLength(); i++)
        	{
        		Element e = (Element)nodesI.item(i);
        	
        		
        		
        		if(e.getNodeName().equals("Archivo1"))
        		{
        			//txt1.append( "Documento 1: " + e.getAttribute("data") + " \n");
        			a1.setVisibility(View.VISIBLE);
        			a1.setText(e.getAttribute("data"));
        		}
        		if(e.getNodeName().equals("Archivo2"))
        		{
        			a2.setVisibility(View.VISIBLE);
        			a2.setText(e.getAttribute("data"));
        		}
        		if(e.getNodeName().equals("Archivo3"))
        		{
        			a3.setVisibility(View.VISIBLE);
        			a3.setText(e.getAttribute("data"));
        		}
        		if(e.getNodeName().equals("Archivo4"))
        		{
        			a4.setVisibility(View.VISIBLE);
        			a4.setText(e.getAttribute("data"));
        		}
        		if(e.getNodeName().equals("Archivo5"))
        		{
        			a5.setVisibility(View.VISIBLE);
        			a5.setText(e.getAttribute("data"));
        		}
        		if(e.getNodeName().equals("Archivo6"))
        		{
        			a6.setVisibility(View.VISIBLE);
        			a6.setText(e.getAttribute("data"));
        			
        			
        		}
        		if(e.getNodeName().equals("Archivo7"))
        		{
        			a7.setVisibility(View.VISIBLE);
        			a7.setText(e.getAttribute("data"));
        		}
        		if(e.getNodeName().equals("Archivo8"))
        		{
        			a8.setVisibility(View.VISIBLE);
        			a8.setText(e.getAttribute("data"));
        		}
        		if(e.getNodeName().equals("Archivo9"))
        		{
        			a9.setVisibility(View.VISIBLE);
        			a9.setText(e.getAttribute("data"));
        		}
        		if(e.getNodeName().equals("Archivo10"))
        		{
        			a10.setVisibility(View.VISIBLE);
        			a10.setText(e.getAttribute("data"));
        		}
        		       		
        		
        	}
        }
        
        eli.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Log.e("Tania", "apenas va a comprobar el if");
				if(a1.isChecked()){ 
					Log.e("Tania", "si reconoce que esta seleccionado");
				try{
					//http://kp.utch.edu.mx/prueba_ftp.php?mat=1111250221&con=11223344&arch=prueba_ftp.docx
					 //URL u = new URL("http://kp.utch.edu.mx/prueba_ftp.php?mat=1111250221&con=11223344&arch=nada en especial.docx");
					 HttpClient client = new DefaultHttpClient();
			            HttpGet request = new HttpGet();
			            
			            String url="http://kp.utch.edu.mx/prueba_ftp.php?mat=1111250221&con=11223344&arch=nada en especial.docx";
			            url=url.replace(" ","%20");
			            
			          
			            request.setURI(new URI(url));
			            HttpResponse response = client.execute(request);
			            

			            Log.e("Tania", "Se terminó la ejecucion de eliminado");
			}catch(Exception e){
			        Log.e("log_tag", "Error in http connection "+e.toString());
			}
		        	
		        	
		        }
				
			}
		});
    }

   
}
