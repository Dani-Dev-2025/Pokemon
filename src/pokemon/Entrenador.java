package pokemon;

import java.io.Serializable;
// importamos las librerías que necesitamos
import java.util.ArrayList;
import java.util.Scanner;

/**
 * la clase Entrenador simula un entrenador de Pokémon, como por ejemplo Ash Ketchum.
 * Tiene un nombre, una cantidad de pokeballs, un equipo (un ArrayList) que puede llegar
 * a tener 6 pokémons como máximo, pero que inicialmente no tiene ninguno) y un compañero 
 * (cuando lo capture y lo elija, el pokemonElegido, que se necesita para atacar a otros
 * pokémons).
 * Aparte de los constructores, los getter y los setters, el Entrenador puede elegir un
 * pokémon mediante el método elegirPokemon(), puede capturar pokémos salvajes con el método
 * capturar() y puede escapar con escapar().
 *
 * 
 * @author Daniel Pacheco
 * @version 1.2
 * @since 2025/05/05
 */

public class Entrenador implements Serializable {

	static final int MAX_POKEMON = 6; // cantidad máxima de pokémos que puede haber en el equipo
	private String nombre; // nombre del entrenador
	private int pokeballs; // pokeballs que tiene o le quedan
	private ArrayList<Pokemon> equipo; // lista de pokémon que forman el equipo del entrenador
	private Pokemon pokemonElegido; // el pokémon compañero actual, con el que ataca a los salvajes

	/**
	 * constructor por defecto, con nombre Entrenador, 30 pokeballs, sin ningún
	 * pokémon en su equipo y por lo tanto ningún compañero (pokemonElegido = null)
	 */

	public Entrenador() {
		this.nombre = "Entrenador";
		this.pokeballs = 30;
		this.equipo = new ArrayList<>();
		this.pokemonElegido = null;
	}

	/**
	 * Constructor con parámetros
	 * 
	 * @param nombre    - nombre que le pondremos al Entrenador
	 * @param pokeballs - la cantidad de pokeballs que tendrá inicialmente
	 * @param equipo    - el equipo de pokémons del entrenador
	 */

	public Entrenador(String nombre, int pokeballs, ArrayList<Pokemon> equipo) {
		this.nombre = nombre;
		this.pokeballs = pokeballs;
		this.equipo = equipo;
	}

	/**
	 * el método getter getNombre() obtiene y devuelve el nombre del Entrenador
	 *
	 * @return - devuelve el nombre del Entrenador
	 */

	public String getNombre() {
		return nombre;
	}

	/**
	 * el método setter setNombre() establece el nombre del Entrenador
	 * 
	 * @param nombre - nombre del entrenador
	 */

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * el método getPokeballs obtiene y devuelve la cantidad de pokeballs
	 * 
	 * @return la cantidad de pokeballs que le quedan al entrenador
	 */

	public int getPokeballs() {
		return pokeballs;
	}

	/**
	 * el método setPokeballs() establece o modifica la cantidad de pokeballs
	 * 
	 * @param pokeballs - las pokeballs que tiene el entrenador
	 */

	public void setPokeballs(int pokeballs) {
		this.pokeballs = pokeballs;
	}

	/**
	 * el método getter getEquipo devuelve un ArrayList con los pokémons que hay en
	 * el equipo
	 * 
	 * @return - los pokémos que hay en el equipo
	 */

	public ArrayList<Pokemon> getEquipo() {
		return equipo;
	}

	/**
	 * el método setter setEquipo() establece o modifica los pokémons que hay en el
	 * equipo
	 * 
	 * @param equipo los pokemons que hay en el ArrayList equipo
	 */

	public void setEquipo(ArrayList<Pokemon> equipo) {
		this.equipo = equipo;
	}

	/**
	 * el método getter getPokemonElegido() obtiene y devuelve el pokémon que el
	 * entrenador tiene de compañero
	 * 
	 * @return - devuelve el pokémon que tiene el entrenador como compañero
	 */

	public Pokemon getPokemonElegido() {
		return pokemonElegido;
	}

	/**
	 * el método setter setPokemonElegido() establece o modifica el pokémon
	 * compañero del entrenador
	 * 
	 * @param pokemonElegido - el pokémon compañero del entrenador
	 */

	public void setPokemonElegido(Pokemon pokemonElegido) {
		this.pokemonElegido = pokemonElegido;
	}

	/**
	 * el método vacío elegirPokemon() permite que el usuario (entrenador) elija un
	 * pokémon como compañero. El pokemon debe estar en su equipo, por lo que
	 * previamente debe haber sido capturado llamando al método capturar(). el flujo
	 * es el siguiente: - si el equipo no tiene ningún pokémon lo comunica por
	 * pantalla - si tiene, el bucle for recorre el ArrayList mostrando los pokémons
	 * que forman el equipo y le pide al usuario que elija uno - la elección se
	 * realiza mediante entrada por teclado, ajustando la misma al índice del
	 * ArrayList restándole 1. Con un try - catch, si el usuario introduce un número
	 * fuera del rango, lo comunica.
	 * 
	 * @param scanner - lee el pokémon elegido por el usuario
	 */

	public void elegirPokemon(Scanner scanner) {

		if (equipo.isEmpty()) { // si el equipo está vacío, lo comunica
			System.out.println("actualmente no tienes pokémons en tu equipo \n");
			return;
		}

		System.out.println("tienes estos Pokemon en tu equipo: \n"); // muestra los pokémons del equipo
		for (int i = 0; i < equipo.size(); i++) {
			System.out.println((i + 1) + (", ") + equipo.get(i).toString());
		}

		System.out.println("elige uno");

		try {
	
			int eleccion = scanner.nextInt() - 1; // se ajusta la elección al índice del ArrayList
			scanner.nextLine(); // limpiar buffer
			if (eleccion < equipo.size()) { // si la elección es menor que el tamaño del equipo,se continúa
				Juego.pokemonElegido = equipo.get(eleccion);
				System.out.println("has elegido a " + Juego.pokemonElegido.getNombre());
			} else {
				System.out.println("No has introducido un valor válido \n");
			}
		} catch (Exception e) {
			System.out.println("No has introducido un valor válido \n");
			scanner.nextLine(); // limpiar buffer
		}

	}

	/**
	 * el método capturar() intenta capturar un pokémon salvaje. El éxito de la
	 * captura depende de una probabilidad aleatoria que tendrá la pokeball y la
	 * vida actual del pokémon salvaje. Si al entrenador no le quedan pokeballs, lo
	 * comunica y no podrá capturarlo. Si la vida del pokémon salvaje es igual o
	 * menor a 0, utiliza y resta una pokeball y la captura será exitosa. Si el
	 * equipo tiene menos de 6 pokémons (el máximo permitido) se realiza el intento
	 * de captura, restando una pokeball. Si la captura es exitosa, el pokémon
	 * salvaje se añade al equipo del entrenador y se elimina de la lista de
	 * pokémons salvajes. Si la captura no resulta exitosa, el pokémon salvaje
	 * escapa. Si el equipo del entrenador está completo, lo comunica y no se puede
	 * realizar la captura.
	 * 
	 * @param salvaje - el pokémon salvaje al que se intenta capturar
	 * @return - devuelve si la captura es exitosa o no
	 */

	public boolean capturar(Pokemon salvaje) {

		if (pokeballs <= 0) { // si no hay pokeballs, lo comunica y no se puede capturar
			System.out.println("Lo siento, no te quedan pokeballs \n");
			return false;
		}

		if (salvaje.getVida() <= 0) { //si el pokémon salvaje tiene 0 o menos de vida
			pokeballs--; // utiliza y resta una pokeball
			equipo.add(salvaje); // añade el pokémon al equipo
			Juego.listaSalvajes.remove(salvaje); // se elimina de la lista de salvajes
			System.out.println("Gastas una pokeball. Te quedan " + pokeballs + "\n"); // informa de las pokeballs que quedan
			System.out.println(salvaje.getNombre() + " ¡ha sido capturado! \n");
			return true;
		}
		// calcula la probabilidad de captura del pokémon salvaje:
			// genera una probabilidad aleatoria para la pokeball
		double probabilidadPokeball = (Math.random() * 100); 
			// calcula la probabildad de captura, multiplicando la probabilidad de la pokeball por
			// la proporción entre la vida actual y la máxima
		double probabilidadCaptura = probabilidadPokeball * (salvaje.getVida() / salvaje.getVidaMaxima());
			// para que cada pokémon tenga un ratio de captura diferente, generamos un número aleatoria
			// para que el éxito de captura sea más incierto
		double numeroAleatorio = Math.random() * 100;

		if (equipo.size() < MAX_POKEMON) { // si el equipo tiene menos de 6 pokémons, se intenta la captura

			pokeballs--; // resta una pokeball
			System.out.println("Gastas una pokeball. Te quedan " + pokeballs + "\n");
			
			// Si el número aleatorio es menor que la probabilidad de captura, la captura será exitosa
			if (numeroAleatorio < probabilidadCaptura) { 

				equipo.add(salvaje); // se añade el pokémon al equipo
				Juego.listaSalvajes.remove(salvaje); // se elimina de la lista de salvajes
				System.out.println(salvaje.getNombre() + " ¡ha sido capturado con éxito!");
				return true;

			} else { // si la captura no es exitosa, el pokémon se escapa
				System.out.println(salvaje.getNombre() + " ha escapado...");
				return false;
			}

		} else { // si el equipo tiene 6 pokémons, no se pueden capturar más
			System.out.println("Lo siento, tu equipo está completo \n");
			return false;
		}
	} // fin capturar()

	/**
	 * si el entrenador quiere escapar del combate, llama al método escapar() y se
	 * muestra el mensaje correspondiente por pantalla
	 */
	public void escapar() {
		System.out.println("has decidido escapar \n");
	}

	/**
	 * sobreescribe el método toString(), devolviendo una cadena de texto con los datos del 
	 * entrenador: nombre, número de pokeball y pokémons en el equipo
	 * @return devuelve un String con la información del entrenador
	 */
	@Override
	public String toString() {
		return "Nombre Entrenador: " + nombre + " · Número de pokeballs: " + pokeballs + " · Equipo" + equipo;
	}

}
