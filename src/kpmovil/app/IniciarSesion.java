package kpmovil.app;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class IniciarSesion extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_iniciar_sesion, menu);
        return true;
    }
}
