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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class OrariFragment extends Fragment{
	
	public OrariFragment(){}
	
	private Spinner spinnerLinee;
	private Button btnScarica;
	private TextView testoPercorso;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    	 
        View rootView = inflater.inflate(R.layout.fragment_orari, container, false);
        
    	spinnerLinee = (Spinner) rootView.findViewById(R.id.spinnerLinee);
    	btnScarica = (Button) rootView.findViewById(R.id.button);
    	testoPercorso = (TextView) rootView.findViewById(R.id.textView2);
    	
    	
    	
    	spinnerLinee.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				
	  		   if(spinnerLinee.getSelectedItemPosition() != -1)
	  		    	percorsoSelezionato(spinnerLinee.getSelectedItemPosition());
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// 
				
			}
		});
    	
    	btnScarica.setOnClickListener(new OnClickListener() {

    		  @Override
    		  public void onClick(View v) {

    		    if(spinnerLinee.getSelectedItemPosition() != -1)
    		    	scaricaSelezionato(spinnerLinee.getSelectedItemPosition());
    		  }

    		});
    	
    	
        return rootView;
        
        
    }
    
    public void scaricaSelezionato(int percorso) {
    	
    	switch(percorso) {
    		case 0:
    			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://storage.aicod.it/portale/teppr/file/Linea_1.pdf")));
    			break;
    		case 1:
    			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://storage.aicod.it/portale/teppr/file/Linea_2-1.pdf")));
    			break;
    		case 2:
    			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://storage.aicod.it/portale/teppr/file/Linea_3.pdf")));
    			break;
    		case 3:
    			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://storage.aicod.it/portale/teppr/file/Linea_4.pdf")));
    			break;
    		case 4:
    			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://storage.aicod.it/portale/teppr/file/Linea_5.pdf")));
    			break;
    		case 5:
    			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://storage.aicod.it/portale/teppr/file/Linea_6_2014-2.pdf")));
    			break;
    		case 6:
    			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://storage.aicod.it/portale/teppr/file/Linea_7-1.pdf")));
    			break;
    		case 7:
    			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://storage.aicod.it/portale/teppr/file/Linea_8.pdf")));
    			break;
    		case 8:
    			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://storage.aicod.it/portale/teppr/file/Linea_9-1.pdf")));
    			break;
    		case 9:
    			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://storage.aicod.it/portale/teppr/file/Linea_11-1.pdf")));
    			break;
    		case 10:
    			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://storage.aicod.it/portale/teppr/file/Linea_12-1.pdf")));
    			break;
    		case 11:
    			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://storage.aicod.it/portale/teppr/file/Linea_13-1.pdf")));
    			break;
    		case 12:
    			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://storage.aicod.it/portale/teppr/file/Linea_14-1.pdf")));
    			break;
    		case 13:
    			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://storage.aicod.it/portale/teppr/file/Linea_15-1.pdf")));
    			break;
    		case 14:
    			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://storage.aicod.it/portale/teppr/file/Linea_20-1.pdf")));
    			break;
    		case 15:
    			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://storage.aicod.it/portale/teppr/file/Linea_21-1.pdf")));
    			break;
    		case 16:
    			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://storage.aicod.it/portale/teppr/file/Linea_23-2.pdf")));
    			break;
    		case 17:
    			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://storage.aicod.it/portale/teppr/file/Linea_2N-1.pdf")));
    			break;
    		case 18:
    			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://storage.aicod.it/portale/teppr/file/Linea_4N-1.pdf")));
    			break;
    			
    	}
    }
    public void percorsoSelezionato(int percorso) {
    	String linea1= "LINEA 1 \n\nBarriera Garibaldi - via Garibaldi - Teatro Regio - via Mazzini - ponte di Mezzo - p.za Corridoni - via Bixio - barriera Bixio - v.le Villetta - Cimitero della Villetta - via Taro - sede TEP - via Baganza - str. Farnese.";
    	String linea2 ="LINEA 2 \n\nLargo 8 Marzo - via Aleotti - via Montanara - via Langhirano - ponte Dattaro - v.le Montebello - via Bizzozero - v.le Solferino - barriera Farini - v.le Basetti - v.le Toscanini - via Mazzini - Teatro Regio - via Garibaldi - barriera Garibaldi - via Trento - via S.Leonardo - Centro Torri - Case Nuove - Parcheggio Nord.\n\nProlungamento a fasce orarie:\n via Colorno - S.Polo di Torrile - Colorno";
    	String linea3 ="LINEA 3 \n\nCrocetta - via Emilia Ovest - p.le Caduti del Lavoro - via Gramsci - Ospedale - barriera Santa Croce - via D'Azeglio - ponte di Mezzo - via Mazzini - p.za Garibaldi - Municipio - str. della Repubblica - Prefettura - barriera Repubblica - via Emilia Est - Strada Elevata - via Emilio Lepido - S.Lazzaro.\n\nProlungamenti festivi invernali:\nCrocetta - S.Pancrazio\nS. Lazzaro - S.Prospero";
    	String linea4 ="LINEA 4 \n\nVia Mordacci - via Emilia Ovest - p.le Caduti del Lavoro - via Gramsci - Ospedale - barriera Santa Croce - via D'Azeglio - ponte di Mezzo - via Mazzini - p.za Garibaldi - Municipio - str. della Repubblica - Prefettura - barriera Repubblica - via Emilia Est - Strada Elevata - via Emilio Lepido - S.Lazzaro - via Passo del Lagastrello - via Passo del Bratello - via Passo della Colla - str. Quarta - via Parigi.";
    	String linea5 ="LINEA 5 \n\nVia Chiavari - Marinelli - via Spezia - via Pellico - Palazzetto dello Sport - via Fleming - via Colli - str. Abbeveratoia - Ospedale - via Gramsci - barriera Santa Croce - via D'Azeglio - ponte di Mezzo - via Mazzini - p.za Garibaldi - Municipio - str. della Repubblica - Prefettura - barriera Repubblica - via Emilia Est - Strada Elevata - via Zarotto - via Sidoli - via Muratori - via Giovenale - via Orazio.";
    	String linea6 ="LINEA 6 \n\nCornocchio - via Savani/via Lanfranco - v.le Piacenza - ponte delle Nazioni - Stazione FS - v.le Toschi - Pilotta - v.le Mariotti - p.za Ghiaia - ponte di Mezzo –via Bixio - barriera Bixio - via Spezia - Marinelli - Cavagnari - Baccanelli.\n\nProlungamenti:\nCornocchio - str. dei Mercati - Baganzola;\nCornocchio - str. dei Mercati - via del Taglio - Aeroporto.\nBaccanelli - Stradella - Collecchio - Gaiano - Ozzano - Fornovo;\nBaccanelli - Stradella - Sala Baganza - Felino;";
    	String linea7 ="LINEA 7 \n\nUniversità Sud - q.re Calzetti - via Langhirano - via Enza - via Po - Casa di Cura Piccole Figlie - p.le Fiume - via Solari/v.le Villetta - barriera Bixio - v.le Vittoria/v.le dei Mille - b.ra S.Croce - via D'Azeglio - ponte di Mezzo - p.za Ghiaia - v.le Mariotti - Pilotta - v.le Toschi - Stazione FS - barriera Garibaldi - Direzionale Uff. Comunali - v.le Mentana - barriera Saffi - via Toscana - via Strobel - via Colombo - q.re Benedetta.";
    	String linea8 ="LINEA 8 \n\nVia Palermo - via Trieste - via Venezia - via Trento - Stazione FS - v.le Toschi - Pilotta - v.le Mariotti - p.za Ghiaia - via Mazzini - p.za Garibaldi - Municipio - str. della Repubblica - Prefettura - barriera Repubblica - v.le S.Michele/v.le P.M. Rossi - Stadio - v.le Duca Alessandro - v.le Sette F.lli Cervi - v.le Martiri di Cefalonia - via Montebello - via Cella - p.le Maestri - Casa di Cura Città di Parma - via Zanardi - via Traversetolo - Parcheggio Sud-Est - via Pertini - via Terracini - via Nenni.";
    	String linea9 ="LINEA 9 \n\nP.le S.Ilario - via Bocchi/str Buffolara – ponte delle Nazioni - Stazione FS - v.le Toschi - Pilotta – v.le Mariotti - p.za Ghiaia - v.le Toscanini – v.le Basetti – barriera Farini - Stadio - v.le Partigiani d'Italia - via Puccini - via Bolzoni - via Zarotto - via Casa Bianca - via 24 Maggio - via Picasso - q.re S.Lazzaro Est - via De Chirico.\n\nProlungamento: via 24 Maggio - str. del Lazzaretto - Marore - cimitero di Marore/p.le S.Ilario – q.re Artigianale Crocetta.";
    	String linea11 ="LINEA 11 \n\nVia Volturno - barriera Bixio - via Bixio - p.le Corridoni - p.za Ghiaia - Pilotta - v.le Toschi - Stazione FS - barriera Garibaldi - Direzionale Uff. Comunali - v.le Mentana - barriera Saffi - v.le Tanara/v.le Mentana - barriera Repubblica - v.le S.Michele /v.le P.M. Rossi - Stadio -  via Torelli - via Traversetolo - sede IREN.\n\nProlungamenti:\nvia Traversetolo - Botteghino - Pilastrello - Monticelli - Montechiarugolo - Tortiano - Montecchio;\nvia Traversetolo - Botteghino - Pilastrello - Marano - Piazza - Traversatolo;\nvia Volturno - Cà Peschiera.";
    	String linea12 ="LINEA 12 \n\nFognano - via Cremonese - Crocetta - via Emilia Ovest -  via Jenner - via Colli - str. Abbeveratoia - Ospedale - v.le Piacenza - ponte delle Nazioni - Stazione FS - v.le Toschi - Pilotta - v.le Mariotti - p.za Ghiaia - v.le Toscanini - v.le Basetti - barriera Farini - v.le Solferino - via Bizzozero - via Pastrengo - str. Argini Parma.\n\nProlungamenti:\nFognano - Eia;\nFognano - Roncopascolo;\nstr. Argini Parma - Porporano - Basilicanova - Mamiano - S. Maria del Piano - Lesignano Bagni- Stadirano;\nstr. Argini Parma - Porporano - Basilicanova - Mamiano - Bannone - Traversetolo;\nstr. Argini Parma - via Langhirano - Alberi - Vigatto - Corcagnano - Panocchia;\nstr. Argini Parma - via Langhirano - Corcagnano - Pilastro - Torrechiara - Langhirano.";
    	String linea13 ="LINEA 13 \n\nParcheggio Nord - via Paradigna - q.re S.Elisabetta - via Mazzacavallo - Centro Torri - via S.Leonardo - via Trento - Stazione FS - v.le Toschi - Pilotta - v.le Mariotti - p.za Ghiaia - v.le Toscanini - v.le Basetti - v.le Rustici - ponte Dattaro - via Langhirano - via Montanara - via Atleti Azzurri - q.re Cinghio Sud - l.go De Coubertin.\n\nProlungamento:\nq.re Cinghio Sud - Gaione - S.Ruffino - Carignano - Corcagnano\nq.re Cinghio Sud - Gaione - S.Ruffino - Carignano - Casale di Felino - Felino\ncoincidenza con navetta: Parcheggio Nord - via Forlanini - Zona industriale S.P.I.P.";
    	String linea14 ="LINEA 14 \n\n(feriale escluso sabato)servizio dalle 16.30 alle 19\n\nUniversità Sud - Parcheggio Sud - via Langhirano - via Enza - via Po - Casa di Cura Piccole Figlie - p.le Fiume - via Solari/v.le Villetta - barriera Bixio - v.le Toscanini - v.le Mariotti - p.za Ghiaia - Pilotta - v.le Toschi - Stazione FS";
    	String linea15 ="LINEA 15 (feriale)\n\nVia Pastrengo - q.re Santa Eurosia - p.za Maestri - Casa di Cura Città di Parma - via Brambilla - v.le Martiri di Cefalonia - via Sette F.lli Cervi - v.le Duca Alessandro - v.le Solferino - barriera Farini - str. Farini - str. Ponte Caprazucca/p.le Corte d'Appello - via Mazzini/via Cavestro - Teatro Regio - via Garibaldi - barriera Garibaldi - via Trento - via S.Leonardo.\n\nProlungamenti:\nvia S.Leonardo - p.le Salsi - via Paradigna - str. Nuova Naviglio;\nvia S.Leonardo - via Cagliari - via Milano - via Moletolo - q.re artigianale di Moletolo.";
    	String linea20 ="LINEA 20 \n\nVia Vasari - AUSL - via Reggio - Stazione FS - viale Toschi - Pilotta - viale Mariotti - piazza Ghiaia - ponte di Mezzo - Via Bixio – b.go S.Giuseppe/str Costituente - p.le Picelli - via Rasori - Ospedale Maggiore - via Volturno - via Pellico - Palazzetto dello Sport - via Riomaggiore - via Sanremo - via Portofino - str. Manara - via Borsari - q.re artigianale di Scarzara.\n\nProlungamento:\nq.re artigianale di Scarzara - via Pontasso – Baccanelli";
    	String linea21 ="LINEA 21 \n\nUniversità Sud - Parcheggio Sud - via Langhirano - ponte Dattaro - v.le Rustici - v.le Basetti - v.le Toscanini - p.za Ghiaia - v.le Mariotti - Pilotta - v.le Toschi - Stazione FS - barriera Garibaldi - Direzionale Uff. Comunali - v.le Mentana - barriera Saffi - v.le Mentana/v.le Tanara - barriera Repubblica - via Emilia Est - Strada Elevata - via Mantova.\n\nProlungamenti:\nvia Mantova - str. del Paullo - via Maestri del Lavoro – via Burla;\nvia Mantova - Vicopò - stabilimento Barilla - Chiozzola - Bogolese - Sorbolo.";
    	String linea23 ="LINEA 23 (feriale) \n\nParcheggio Est - via Emilia Est - S.Lazzaro - Strada Elevata - via Emilia Est -  barriera Repubblica - v.le Tanara/v.le Mentana - Direzionale Uff. Comunali - barriera Garibaldi - via Garibaldi - Teatro Regio - via Mazzini - ponte di Mezzo - via D'Azeglio - Ospedale - Crocetta - via Emilia Ovest - Parcheggio Ovest - S.Pancrazio.\n\nProlungamento a fasce orarie:\nS.Pancrazio - Pontetaro - Noceto;\nS.Pancrazio - Viarolo - Roncocampocanneto - Trecasali - S.Secondo;\nS.Pancrazio - Vigolante - Vicofertile;\nParcheggio Est - S.Prospero - S.Ilario d'Enza.";
    	String linea2N ="SERVIZIO NOTTURNO \n\ndalle 20 alle 22\n\nLINEA 2N\n\nVia Serao - via S.Leonardo - Centro Torri - via Trento - Stazione FS - barriera Garibaldi - via Garibaldi - Teatro Regio - via Mazzini - p.za Corridoni - via Bixio - barriera Bixio - v.le Villetta/via Solari - p.le Fiume - Casa di Cura Piccole Figlie - via Po - via Enza - via Langhirano - via Montanara - via Aleotti - largo 8 Marzo.";
    	String linea4N ="SERVIZIO NOTTURNO \n\ndalle 20 alle 22\n\nLINEA 4N\n\nVia Chiavari - via Spezia Marinelli - via Pellico - Palazzetto dello sport - via Fleming - via Colli - via Abbeveratoia - Ospedale - via Gramsci - barriera Santa Croce - via D'Azeglio - ponte di Mezzo - via Mazzini - p.za Garibaldi - Municipio - str. della Repubblica - Prefettura - barriera Repubblica - via Emilia Est - Strada Elevata - via Zarotto - via Sidoli - via Muratori - via Giovanale - via Orazio.";
    	switch(percorso) {
    		case 0:
    			testoPercorso.setText(linea1);
    			break;
    		case 1:
    			testoPercorso.setText(linea2);
    			break;
    		case 2:
    			testoPercorso.setText(linea3);
    			break;
    		case 3:
    			testoPercorso.setText(linea4);
    			break;
    		case 4:
    			testoPercorso.setText(linea5);
    			break;
    		case 5:
    			testoPercorso.setText(linea6);
    			break;
    		case 6:
    			testoPercorso.setText(linea7);
    			break;
    		case 7:
    			testoPercorso.setText(linea8);
    			break;
    		case 8:
    			testoPercorso.setText(linea9);
    			break;
    		case 9:
    			testoPercorso.setText(linea11);
    			break;
    		case 10:
    			testoPercorso.setText(linea12);
    			break;
    		case 11:
    			testoPercorso.setText(linea13);
    			break;
    		case 12:
    			testoPercorso.setText(linea14);
    			break;
    		case 13:
    			testoPercorso.setText(linea15);
    			break;
    		case 14:
    			testoPercorso.setText(linea20);
    			break;
    		case 15:
    			testoPercorso.setText(linea21);
    			break;
    		case 16:
    			testoPercorso.setText(linea23);
    			break;
    		case 17:
    			testoPercorso.setText(linea2N);
    			break;
    		case 18:
    			testoPercorso.setText(linea4N);
    			break;
    			
    	}
    }
    
   
    
   



}
