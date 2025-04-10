package pokemon;

/**
 * la clase PokemonPlanta simula un pokémon de tipo planta como puede ser
 * Bulbasaur. Es hija de la clase Pokemon, por lo que hereda sus atributos y sus
 * métodos, sobreescribe el método toString() e implementa la interface Ataque,
 * sobreescribiendo atacar() personalizando el daño que realiza teniendo
 * encuenta que el pokémon elegido es de tipo planta.
 * 
 * @author Daniel Pacheco
 * @version 1.0
 * @since 2025/04/08
 */

public class PokemonPlanta extends Pokemon implements Ataque {

	/**
	 * constructor por defecto que hereda los atributos de la clase padre Pokemon
	 * crea un pokémon de tipo planta con los valores por defecto
	 */

	public PokemonPlanta() {
		super();
	}

	/**
	 * Constructor con parámetros
	 * 
	 * @param nombre     - nombre del pokémon
	 * @param nivel      - nivel del pokémon
	 * @param vida       - valor de la vida actual del pokémon
	 * @param vidaMaxima - la vida máxima que puede tener el pokémon inicialmente
	 */

	public PokemonPlanta(String nombre, int nivel, double vida, double vidaMaxima) {
		super(nombre, nivel, vida, vidaMaxima);
	}

	/**
	 * sobreescribe el método toString(), sobreescrito a su vez en la clase padre,
	 * para mostrar también el tipo de pokémon además del resto de atributos
	 */

	@Override
	public String toString() {
		return super.toString() + " · clase: " + getClass();
	}

	/**
	 * sobreescribe el método abstracto sonido() de la clase Pokemon. Este método
	 * simula el rugido que hace el pokémon de tipo planta.
	 */

	public void sonido() {
		System.out.println(getNombre() + " dice: flaffffffffff \n");
	}

	/**
	 * implementa el método atacar(). Este método simula el ataque que el pokémon de
	 * tipo planta realiza. Primero muestra los nombres de los pokémons que se
	 * enfrentan. El ataque tiene un daño base de 10 puntos. Pero si nuestro pokémon
	 * ataca a un pokémon de tipo fuego, el valor del daño se verá reducido un 20%
	 * (multiplicado por 0.8) y si ataca a un pokémon de tipo agua el ataque será
	 * aumentado un 20% (multiplicado por 1.2). Si ataca a un pokémon del mismo
	 * tipo, el valor del daño será el daño base. Una vez finalizado el ataque,
	 * muestra los puntos de vida del pokémon.
	 * 
	 * @param salvaje - el pokémon salvaje contra el que nos enfrentamos
	 */

	@Override
	public void atacar(Pokemon salvaje) {

		System.out.println(this.getNombre() + " ataca a " + salvaje.getNombre()); // obtiene y muestra el nombre de
																					// atacante y el del atacado

		double danio = 10; // daño base de 10 puntos

		if (salvaje.getClass() == PokemonFuego.class) {
			salvaje.setVida(salvaje.getVida() - (danio * 0.8)); // si el pokémon salvaje es una instancia de
																// PokemonFuego, le resta el daño base menos un 20%
			System.out.println(
					salvaje.getNombre() + " recibe -20% de daño · le quedan " + salvaje.getVida() + " puntos de vida");

		} else if (salvaje.getClass() == PokemonAgua.class) {
			salvaje.setVida(salvaje.getVida() - (danio * 1.2)); // si el pokémon salvaje es una instancia de
																// PokemonAgua, le resta el daño base más un 20%
			System.out.println(
					salvaje.getNombre() + " recibe +20% de daño · le quedan " + salvaje.getVida() + " puntos de vida");
		} else {
			salvaje.setVida(salvaje.getVida() - danio); // si el pokémon salvaje no es una instancia de PokemonFuego o
														// PokemonAgua le resta el daño base
			System.out.println(
					salvaje.getNombre() + " recibe daño normal · le quedan " + salvaje.getVida() + " puntos de vida");
		}
	}
}
