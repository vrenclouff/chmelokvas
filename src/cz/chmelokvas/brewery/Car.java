package cz.chmelokvas.brewery;

public class Car {
	/** Typ auta */
	private final CarType type;
	
	/** Soucasny stav vozu */
	private State state;
	
	/** Soucasna instrukce */
	private Instruction currentInstruction;
	
	/**Dopravni uzel ve kterem se prave nachazi*/
	private TransportNode position;
	
	/**
	 * Pocet prazdnych sudu.<br>
	 * V pripade cisterny je zbyvajici misto v nadrzi
	 */
	private int empty;
	
	/**
	 * Pocet plnych sudu.<br>
	 * V pripade cisterny je to pocet hektolitru
	 */
	private int full;
	
	/**
	 * Privatni konstruktor. Vytvori auto na danem miste s danymi specifikacemi.<br>
	 * Konstruktor je privatni, jelikoz chceme zamezit vkladani nesmyslnych hodnot
	 * do atributu jako je kapacita nebo rychlost. Instance se ziskavaji pres metody
	 * {@code getCamion(TransportNode pozice)}, {@code getCistern(TransportNode pozice)} a {@code getTruck(TransportNode pozice)}
	 * @param position Misto kde se instance vytvori. Pozice daneho auta
	 * @param capacity Kapacita ulozneho prostoru auta
	 * @param speed Rychlost jakou auto cestuje silnici
	 * @param reloadingSpeed Pocet minut kolik zabere naklad/vyklad sudu nebo nacerpani/precerpani hl piva
	 */
	private Car(TransportNode position, CarType type){
		this.state = State.WAITING;
		this.currentInstruction = null;
		this.position = position;
		this.type = type;
		this.empty = type.getCapacity();
		this.full = 0;
	}
	
	/**
	 * Tovarni metoda pro vytvareni kamionu
	 * @param position Misto kde se kamion vytvori
	 * @return Instance s vlastnostmi kamionu
	 */
	public static Car getCamion(TransportNode position){
		return new Car(position,CarType.CAMION);
	}
	
	/**
	 * Tovarni metoda pro vytvareni cisterny
	 * @param position Misto kde se cisterna vytvori
	 * @return Instance s vlastnostmi cisterny
	 */
	public static Car getCistern(TransportNode position){
		return new Car(position,CarType.CISTERN);
	}
	
	/**
	 * Tovarni metoda pro vytvareni nakladaku
	 * @param position Misto kde se nakladak vytvori
	 * @return Instance s vlastnostmi nakladaku
	 */
	public static Car getTruck(TransportNode position){
		return new Car(position,CarType.TRUCK);
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Instruction getCurrentInstruction() {
		return currentInstruction;
	}

	public void setCurrentInstruction(Instruction currentInstruction) {
		this.currentInstruction = currentInstruction;
	}

	public TransportNode getPosition() {
		return position;
	}

	public void setPosition(TransportNode position) {
		this.position = position;
	}

	public int getCapacity() {
		return type.getCapacity();
	}

	public float getSpeed() {
		return type.getSpeed();
	}

	public int getReloadingSpeed() {
		return type.getReloadingSpeed();
	}
	
	public int getEmpty() {
		return empty;
	}

	public int getFull() {
		return full;
	}

	/**
	 * Nalozi n sudu (resp. hl) piva
	 * @param n mnozstvi piva
	 */
	public void load(int n){
		this.full += n;
		this.empty -= n;
	}
	
	/**
	 * Vylozi n sudu (resp. hl) piva
	 * @param n mnozstvi piva
	 */
	public void unload(int n){
		this.full -= n;
		this.empty += n;
	}
	
	public String toString(){
		return type + "";
	}
}
