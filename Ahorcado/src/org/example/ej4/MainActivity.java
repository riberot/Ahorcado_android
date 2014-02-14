package org.example.ej4;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView salida;// para informar

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		salida = (TextView) findViewById(R.id.info);
		salida.setText("Bienvenido. Juega una partida, a ver qué tal te va.");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void jugar(View view) {
		Intent in = new Intent(this, Ahorcado.class);
		startActivityForResult(in, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1 && resultCode == RESULT_OK) {
			salida.setText("¿otra partida?");
		}
	}

	public void salir(View view) {
		finish();
	}

}
