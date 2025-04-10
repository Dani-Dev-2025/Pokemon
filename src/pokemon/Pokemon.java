package pokemon;

/**
 * la clase Pokemon simula un Pokémon sin definir, por ello es abstracta, ya que
 * no queremos instanciarla directamente, sino a través de sus clases hijas
 * PokemonAgua, PokemonFuego y PokemonPlanta. Cada Pokemon tiene un nombre, un
 * nivel, una vida inicial y una vida máxima. Sus métodos son el constructor por
 * defecto, un constructor parametrizado, sobreescribe el método toString para
 * mostrar los atributos del pokémon y el método atacar() del interface para que
 * cualquier pokémon pueda atacar. Este método será sobreescrito en sus clases
 * hijas, ya que cada pokémon inflije más o menos daño dependiendo de su tipo y
 * el de su rival.
 * 
 * @author Daniel Pacheco
 * @version 1.0
 * @since 2025/04/08
 * 
 */

public abstract class Pokemon {

	private String nombre; // el nombre del pokémon
	private int nivel; // su nivel
	private double vida; // su vida actual
	private double vidaMaxima; // la vida máxima que puede llegar a tener

	/**
	 * constructor por defecto
	 */

	public Pokemon() {
		nombre = "Pokemon";
		nivel = 30;
		vida = 30;
		vidaMaxima = 30;
	}

	/**
	 * constructor parametrizado
	 * 
	 * @param nombre     - el nombre del pokémon, ej: Bulbasaur
	 * @param nivel      - el nivel actual del pokémon
	 * @param vida       - la vida actual del pokémon
	 * @param vidaMaxima - la vida máxima que puede llegar a tener el pokémon
	 */

	public Pokemon(String nombre, int nivel, double vida, double vidaMaxima) {
		this.nombre = nombre;
		this.nivel = nivel;
		this.vida = vida;
		this.vidaMaxima = vidaMaxima;
	}

	/**
	 * sobreescribe el método toString() para mostrar de forma clara los atributos
	 * del pokémon
	 * 
	 * @return devuelve las características del pokémon
	 */

	@Override
	public String toString() {
		return "Nombre: " + nombre + " · nivel: " + nivel + " · vida: " + vida + " · vidaMaxima: " + vidaMaxima;
	}

	/**
	 * método abstracto sonido() que no devuelve nada y que será sobreescrito por
	 * las clases hijas con el objetivo de que cada tipo de pokémon haga un sonido
	 * diferente
	 */
	public abstract void sonido();

	/**
	 * método getter que muestra el nombre del pokémon
	 * 
	 * @return devuelve el nombre del pokémon
	 */

	public String getNombre() {
		return nombre;
	}

	/**
	 * método setter que modifica el nombre del pokémon
	 * 
	 * @param nombre - el nombre que le queremos poner al pokémon
	 */

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * método getter que obtiene el nivel del pokémon
	 * 
	 * @return devuelve el nivel del pokémon
	 */

	public int getNivel() {
		return nivel;
	}

	/**
	 * método setter que modifica el nivel del pokémon
	 * 
	 * @param nivel del pokémon
	 */

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	/**
	 * método getter que obtiene la vida actual del pokémon
	 * 
	 * @return devuelve el valor de la vida actual del pokémon
	 */

	public double getVida() {
		return vida;
	}

	/**
	 * método setter que modifica la vida actual del pokémon
	 * 
	 * @param vida - el valor que queremos que el pokémon tenga de vida
	 */

	public void setVida(double vida) {
		this.vida = vida;
	}

	/**
	 * método getter que obtiene la vida máxima que puede tener el pokémon
	 * 
	 * @return devuelve la vida máxima que puede tener el pokémon
	 */

	public double getVidaMaxima() {
		return vidaMaxima;
	}

	/**
	 * método setter que modifica la vida máxima que puede tener el pokémon
	 * 
	 * @param vidaMaxima - la vida máxima que puede tener el pokémon
	 */

	public void setVidaMaxima(double vidaMaxima) {
		this.vidaMaxima = vidaMaxima;
	}

	/**
	 * método abstracto atacar() del interface que sobreescriben sus clases hijas
	 * 
	 * @param salvaje - el pokémon salvaje al que atacamos
	 */

	public abstract void atacar(Pokemon salvaje);

}
