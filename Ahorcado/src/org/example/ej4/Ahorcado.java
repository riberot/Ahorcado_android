package org.example.ej4;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("DefaultLocale")
public class Ahorcado extends Activity {
	private String palabra;
	private StringBuffer palabraMostrada;
	private TextView pantallita;
	private ImageView imagen;
	private int fallos;
	private int aciertos;
	private String[] diccionario = { "MERLUZO", "ZOPENCO", "PUCH",
			"BERBERECHO", "MENEILLOS", "BENDER", "ZOIDBERG", "SNORLAX",
			"HOMER", "EPISODIO", "GUARDIOLA", "RENAULT", "VOLKSWAGEN",
			"SCHWARZENEGGER", "PATATUELA", "MACHAQUITO", "BAILEYS",
			"METACRILATO", "OTORRINO", "OFTALMOLOGO", "PSICOKILLER", "DANKO",
			"BOOGIEMAN", "EFERALGAN", "RIBONUCLEICO", "TUETANO", "PIRUETA",
			"VELOCIRAPTOR", "STALLONE", "CHIMPOKOMON", "KENNY", "EUSTAQUIO",
			"ZOOLANDER", "KLITSCHKO", "PLAUSIBLE", "MILENARIO", "GUADALAJARA",
			"PSICOSOCIAL", "CLAVICORDIO", "SCHUMACHER", "MAUSOLEO", "NOGUERA",
			"ULTRASHOW", "CLOROFORMO", "INTRINSECO", "BOGARDE", "CURVA",
			"MENTALISTA", "RUBALCABA", "CORTICOIDES", "PEYORATIVO",
			"VENTRICULO", "ONOMATOPEYA", "ADN", "GONORREA", "CIRROSIS",
			"ESCALENO", "EXHALAR", "GRAZALEMA", "HACENDADO", "EXHALAR",
			"GRAGEA","HARAPO","HEDONISMO","HIEL","ISOTOPO","HECATOMBE","ANDRAJOSO",
			"LINGOTAZO","RAGATANGA","HIPOTIROIDISMO","MARLBORO"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ahorcado_layout);
		/*
		 * por defecto, cuando salgo en plena partida, es por abandono, a menos
		 * que el programa me eche, en cuyo caso ya se encargará de modificar
		 * esta variable con "victoria" o "derrota"
		 */
		aciertos = 0;
		fallos = 0;
		imagen = (ImageView) findViewById(R.id.imagen);
		palabra = seleccionarDelDiccionario();
		palabraMostrada = new StringBuffer("");
		for (int i = 0; i < palabra.length(); i++) {
			palabraMostrada.append("_ ");
		}
		pantallita = (TextView) findViewById(R.id.palabra);
		pantallita.setText(palabraMostrada);
		// salida = (TextView) findViewById(R.id.info);
		// salida.setText("Bienvenido. Juega una partida, a ver qué tal te va.");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void salirDeLaPartida(View view) {
		salir();
	}

	public void salir() {
		Intent in = new Intent();
		setResult(RESULT_OK, in);
		finish();
	}

	public void sePulsaTecla(View view) {
		boolean hayAciertos = false;
		Button boton = (Button) view;
		boton.setEnabled(false);
		// guardo la letra que contiene el botón pulsado
		char letra = boton.getText().toString().charAt(0);
		for (int i = 0; i < this.palabra.length(); i++) {
			if (letra == this.palabra.toUpperCase().charAt(i)) {
				// this.palabraMostrada[i]=letra;
				palabraMostrada.setCharAt(i * 2, letra);
				pantallita.setText(palabraMostrada);
				aciertos++;
				hayAciertos = true;
				if (aciertos == palabra.length()) {
					Toast.makeText(this, "¡Has Ganado!", Toast.LENGTH_LONG)
							.show();
					salir();
				}
			}
		}
		if (!hayAciertos) {
			fallos++;
			switch (this.fallos) {
			case 1:
				this.imagen.setImageResource(R.drawable.hangman2);
				break;
			case 2:
				this.imagen.setImageResource(R.drawable.hangman3);
				break;
			case 3:
				this.imagen.setImageResource(R.drawable.hangman4);
				break;
			case 4:
				this.imagen.setImageResource(R.drawable.hangman5);
				break;
			case 5:
				this.imagen.setImageResource(R.drawable.hangman6);
				break;
			case 6:
				this.imagen.setImageResource(R.drawable.hangman7);
				Toast.makeText(this,
						"Has perdido... la palabra era: " + palabra,
						Toast.LENGTH_LONG).show();
				salir();
				break;
			}
		}
	}

	private String seleccionarDelDiccionario() {
		return diccionario[(int) (Math.random() * (diccionario.length))];
	}

}
