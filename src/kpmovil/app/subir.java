package kpmovil.app;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

public class subir {

	public void uploadFile(String filename, String matricula, String contrasena){
        try {
            FileInputStream fis = new FileInputStream(filename);
            String filnr= filename.replace("\\", "/");
            String fil= filnr.substring(filnr.lastIndexOf("/")+1,filnr.length());
            HttpFileUploader("http://kp.utch.edu.mx/kpmovupload.php?mat="+matricula+"&cont="+contrasena,"noparamshere", fil);
            doStart(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    URL connectURL;
    String params;
    String responseString;
    String fileName;
    byte[] dataToServer;
    FileInputStream fileInputStream = null;
 
    void HttpFileUploader(String urlString, String params, String fileName ){
        try{
            connectURL = new URL(urlString);
        }catch(Exception ex){
        	Log.e("URL FORMATION","MALFORMATED URL");
        }
        this.params = params+"=";
        this.fileName = fileName;
    }
 
    void doStart(FileInputStream stream){
        fileInputStream = stream;
        thirdTry();
    }
 
    void thirdTry() {
        String exsistingFileName = fileName;
 
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        String Tag="3rd";
        try
        {
            
 
    System.out.println("Starting to bad things");
            
 
            HttpURLConnection conn = (HttpURLConnection) connectURL.openConnection();
 
            
            conn.setDoInput(true);
 
            
            conn.setDoOutput(true);
 
            
            conn.setUseCaches(false);
 
            
            conn.setRequestMethod("GET");
 
            conn.setRequestProperty("Connection", "Keep-Alive");
 
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
 
            DataOutputStream dos = new DataOutputStream( conn.getOutputStream() );
 
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"archivo\";filename=\"" + exsistingFileName +"\"" + lineEnd);
            dos.writeBytes(lineEnd);
 
            Log.e(Tag, "Headers are written");
 
            
 
            int bytesAvailable = fileInputStream.available();
            int maxBufferSize = 1024;
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
            byte[] buffer = new byte[bufferSize];
 
            int bytesRead = fileInputStream.read(buffer, 0, bufferSize);
 
            while (bytesRead > 0)
            {
            dos.write(buffer, 0, bufferSize);
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }
 
            
 
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
 
            
            Log.e(Tag, "File is written");
            fileInputStream.close();
            
            dos.flush();
 
            InputStream is = conn.getInputStream();
            System.out.println("aqui");
            int ch;
 
            StringBuffer b =new StringBuffer();
            while( ( ch = is.read() ) != -1 ){
            b.append( (char)ch );
            }
            String s=b.toString();
            System.out.println("Response" + s);
            dos.close();
 
        }
        catch (MalformedURLException ex)
        {
        	Log.e(Tag, "error: " + ex.getMessage(), ex);
        }
 
        catch (IOException ioe)
        {
        	Log.e(Tag, "error: " + ioe.getMessage(), ioe);
        }
    }
}
