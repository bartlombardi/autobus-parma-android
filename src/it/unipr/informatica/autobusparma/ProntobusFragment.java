/*
 * © Copyright 2014 Bartolomeo Lombardi
	This file is part of AutobusParma.

    AutobusParma is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 1 of the License, or
	any later version.

    AutobusParma is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with AutobusParma.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.unipr.informatica.autobusparma;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProntobusFragment extends Fragment{
	
	private String note;
	private TextView tvNote;
	private ImageButton chiama;
	private ImageButton prenota;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_prontobus, container, false);
        
        tvNote = (TextView) rootView.findViewById(R.id.textView1);
        chiama = (ImageButton) rootView.findViewById(R.id.button1);
        prenota = (ImageButton) rootView.findViewById(R.id.button2);
        
        note = "\nPRONTO BUS\n\nIl ProntoBus è il nuovo servizio di autobus a chiamata attivo di sera in città (Pronto Bus Urbano) e durante il giorno in alcuni comuni della Provincia di Parma (Pronto Bus Extra) allo scopo di integrare il servizio di linea nelle zone e nelle fasce orarie in cui esso funziona a frequenza ridotta."
        		+ "\nLa caratteristica principale di questo servizio è il fatto che esso viaggia in base alle richieste dei singoli utenti. Ciò significa che il viaggiatore può usufruire di un servizio personalizzato in base alle proprie esigenze di trasporto, svincolato dai percorsi predefiniti delle singole linee e dai relativi orari di percorrenza."
        		+ "\nPer prenotare la propria corsa è sufficiente chiamare, comunicando il luogo di partenza, di destinazione e orario richiesti."
        		+ "\nIn alternativa è anche possibile effettuare la prenotazione via Internet."
        		+ "\n\nChiamare questi numeri costa € 0,0975 con lo scatto alla risposta da numero fisso."
        		+ "\nDa cellulare, il costo dello scatto alla risposta dipende dalla tariffa applicata dall'operatore.";
        
        tvNote.setText(note);
        
        chiama.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				chiama();
			}
		});
        
        prenota.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				prOnline();
			}
		});

        return rootView;   
	}
	
	private void chiama(){
		Intent callIntent = new Intent(Intent.ACTION_DIAL);
		callIntent.setData(Uri.parse("tel:840222223"));
		getActivity().startActivity(callIntent);
		
	}
	private void prOnline(){
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://88.44.224.123/InsPrenotazione.asp?tipologia=Parma+%2820%3A15-01%3A15%29&a=1")));

	}
	
}
