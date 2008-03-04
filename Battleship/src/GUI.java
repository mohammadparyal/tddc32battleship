import javax.swing.*;
import java.awt.*;

public class GUI {

	//Parametrar
	
	//Ram
	//MenyRam
	private JButton startKnapp;
	private JButton avslutaKnapp;
	private JButton nyttSpelKnapp; //(anropar konstruktorn till Spelkontroll)
	private JButton anslutTillSpelKnapp; //(anropar konstruktorn till Spelkontroll)
	private JTextField statusFält;
	
	
	//SpelRam
	private JTextField dinTurSkylt;
	
	//ChatRam
	private JButton chattKnapp;
	private JTextField chattfält;
	
	//2 Rutnät (skapas utifrån objekt av klassen rutnät)
	
	
		
	//konstruktor
	public GUI(){	}
	
	//Metoder
	public void ritaSkepp(){}
	
	public void ritaBomb(){}

	public void sättTräff(){}
	
	//Metoder direkt från knappar
	//anropar 1:a konstruktorn i nätverkshanteraren
	public void nyttSpel() {}
	
	//anropar 2:a konstruktorn i nätverkshanteraren. Statusfältet måste visa att det finns ett spel att ansluta till
	public void anslutTillSpel(){}
	
	
	
}
