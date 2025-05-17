package pokemon;

import java.io.Serializable;

/**
 * la clase PokemonFuego simula un pokémon de tipo fuego como puede ser
 * Charmander. Es hija de la clase Pokemon, por lo que hereda sus atributos y
 * sus métodos, sobreescribe el método toString() e implementa la interface
 * Ataque, sobreescribiendo atacar() personalizando el daño que realiza
 * teniendo encuenta que el pokémon elegido es de tipo fuego
 * 
 * @author Daniel Pacheco
 * @version 1.2
 * @since 2025/05/05
 */

public class PokemonFuego extends Pokemon implements Ataque, Serializable {
	
	/**
	 * constructor por defecto que hereda los atributos de la clase padre Pokemon
	 * crea un pokémon de tipo fuego con los valores por defecto
	 */

	public PokemonFuego() {
		super();
	}
	
	/**
	 * Constructor con parámetros
	 * 
	 * @param nombre - nombre del pokémon
	 * @param nivel - nivel del pokémon
	 * @param vida - valor de la vida actual del pokémon
	 * @param vidaMaxima - la vida máxima que puede tener el pokémon inicialmente
	 */

	public PokemonFuego(String nombre, int nivel, double vida, double vidaMaxima) {
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
	 * simula el rugido que hace el pokémon de tipo Fuego.
	 */
	
	@Override
	public void sonido() {
		System.out.println(getNombre() + " dice: aaaaaaarrrrrrr \n");
	}
	
	/**
	 * implementa el método atacar(). Este método simula el ataque que el pokémon
	 * de tipo fuego realiza. Primero muestra los nombres de los pokémons que se enfrentan.
	 * El ataque tiene un daño base de 10 puntos. Pero si nuestro pokémon ataca a un pokémon de 
	 * tipo agua, el valor del daño se verá reducido un 20% (multiplicado por 0.8) y si ataca 
	 * a un pokémon de tipo planta el ataque será aumentado un 20% (multiplicado por 1.2). 
	 * Si ataca a un pokémon del mismo tipo, el valor del daño será el daño base.
	 * Una vez finalizado el ataque, muestra los puntos de vida del pokémon.
	 * 
	 * @param salvaje - el pokémon salvaje contra el que nos enfrentamos
	 */

	@Override
	public void atacar(Pokemon salvaje) {

		System.out.println(this.getNombre() + " ataca a " + salvaje.getNombre()); // obtiene y muestra el nombre de atacante y el del atacado

		double danio = 10; // daño base de 10 puntos

		if (salvaje.getClass() == PokemonAgua.class) { // si el pokémon salvaje es una instancia de PokemonAgua, le resta el daño base menos un 20%
			salvaje.setVida(salvaje.getVida() - (danio * 0.8));
			System.out.println(
					salvaje.getNombre() + " recibe " + danio + "  -20% de daño · le quedan " + salvaje.getVida() + " puntos de vida");

		} else if (salvaje.getClass() == PokemonPlanta.class) { // si el pokémon salvaje es una instancia de PokemonPlanta, le resta el daño base más un 20%
			salvaje.setVida(salvaje.getVida() - (danio * 1.2));
			System.out.println(
					salvaje.getNombre() + " recibe " + danio + "  +20% de daño · le quedan " + salvaje.getVida() + " puntos de vida");
		} else {
			salvaje.setVida(salvaje.getVida() - danio); // si el pokémon salvaje no es una instancia de PokemonAgua o PokemonPlanta le resta el daño base
			System.out.println(
					salvaje.getNombre() + " recibe " + danio + " de daño · le quedan " + salvaje.getVida() + " puntos de vida");
		}
	}

}
