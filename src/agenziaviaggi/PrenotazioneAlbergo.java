package agenziaviaggi;

public class PrenotazioneAlbergo extends Prenotazione{
	private String hotel;
	private String dataCheckIn;
	private int numNotti;
	
	public PrenotazioneAlbergo(String hotel, String dataCheckIn, int numNotti, double importo){
		super(importo);
		this.hotel = hotel;
		this.dataCheckIn = dataCheckIn;
		this.numNotti = numNotti;
	}
	
	public String getHotel(){
		return hotel;
	}
	
	public String getDataCheckIn(){
		return dataCheckIn;
	}
	
	public int getNumNotti(){
		return numNotti;
	}
}
