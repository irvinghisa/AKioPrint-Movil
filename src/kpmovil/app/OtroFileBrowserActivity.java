package kpmovil.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class OtroFileBrowserActivity extends ListActivity  {
    
	 private List<String> item = null;
	 private List<String> path = null;
	 private String root="/";
	 private TextView myPath;
	 String mat, con;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mat= getIntent().getExtras().getString("mat");
        con= getIntent().getExtras().getString("con");
        
        myPath = (TextView)findViewById(R.id.path);
        getDir(root);
    }
    
    
    private void getDir(String dirPath)
    {
     myPath.setText("Directorio: " + dirPath);
   
     item = new ArrayList<String>();
     path = new ArrayList<String>();

     File f = new File(dirPath);
     File[] files = f.listFiles();

     

     if(!dirPath.equals(root))
     {

      item.add(root);
      path.add(root);

      item.add("../");
      path.add(f.getParent());

     }

     for(int i=0; i < files.length; i++)
     {

       File file = files[i];
       path.add(file.getPath());

       if(file.isDirectory())
        item.add(file.getName() + "/");

       else
        item.add(file.getName());
     }

     ArrayAdapter<String> fileList =
      new ArrayAdapter<String>(this, R.layout.row, item);

     setListAdapter(fileList);
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    
     File file = new File(path.get(position));

     if (file.isDirectory())
     {

      if(file.canRead())
       getDir(path.get(position));

      else
      {
    	  new AlertDialog.Builder(this)

    	  //.setIcon(R.drawable.icon)
    	  .setTitle("[" + file.getName() + "] la carpeta no puede ser leida!")
    	  .setPositiveButton("OK", 

    	  new DialogInterface.OnClickListener() {
         
    		  public void onClick(DialogInterface dialog, int which) {


    		  }
    	  	}).show();

      }
     }

     else

     {

    	 String nomf= file.getAbsoluteFile().toString();
    	 String ext= nomf.substring(nomf.lastIndexOf(".")+1, nomf.length());
    	 if(ext.equals("doc") || ext.equals("docx") || ext.equals("pdf") || ext.equals("PDF")){
    	 	SubirArchivos.txtNomArch.setText(file.getAbsolutePath());
    	 	SubirArchivos.btnSub.setClickable(true);

    	 	finish();
    	 	
    	 }else{
    		 new AlertDialog.Builder(this)
       	  .setTitle("El archivo no es valido")
       	  .setPositiveButton("OK", 

       	  new DialogInterface.OnClickListener() {
            
       		  public void onClick(DialogInterface dialog, int which) {


       		  }
       	  	}).show();
    	 }
     }
    }
    
}