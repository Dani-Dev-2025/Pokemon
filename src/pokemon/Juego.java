package pokemon;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * La clase Juego contiene la lógica del juego. Contiene un ArrayList de pokémons salvajes, el
 * pokémon que el entrenador elija como compañero una vez lo capture, el método main (que inicia
 * el juego) y los métodos salvajeAleatorio(), que hace que aparezca un pokémon salvaje de la lista,
 * y el método acciones() que corresponde al menú de acciones que puede realizar el entrenador cuando
 * se encuentra con un pokémon salvaje.
 * 
 * @author Daniel Pacheco
 * @version 1.0
 * @since 2025/04/08
 */

public class Juego {

	static ArrayList<Pokemon> listaSalvajes = new ArrayList<>(); // la lista de Pokemons salvajes
	static Pokemon pokemonElegido; // almacena el pokemon que el entrenador ha elegido como compañero con el método
									// elegirPokemon();

	/**
	 * el método main comienza dando la bienvenida al usuario (el entrenador
	 * pokémon) y le solicita que ingrese su nombre por teclado. Con ese nombre, se
	 * crea un objeto Entrenador llamando al constructor parametrizado.
	 * Posteriormente, el programa le saluda y ejecuta el menú principal (que está
	 * dentro de un bucle do-while: mientras el usuario no elija la opción de volver
	 * a casa, el menú se seguirá ejecutando, solicitando así que ingrese una de las
	 * opciones contempladas en el switch). El programa le pregunta al entrenador a
	 * dónde quiere dirigirse, pudiendo elegir entre ir al bosque (donde aparecerá
	 * un pokémon de tipo planta), alrededor del lago (aparecerá uno de tipo agua),
	 * por el desierto (tipo fuego), a "donde le lleve el viento", un lugar incierto
	 * (con lo que podrá aparecer cualquier tipo de pokémon) o a casa a descansar
	 * (lo que sería el fin del juego).
	 */

	public static void main(String[] args) {

		System.out.println("Bienvenido a Pokémon Java. ¡Hazte con todos! \n introduce tu nombre de entrenador: \n");
		Scanner scanner = new Scanner(System.in);
		String nombre = scanner.nextLine(); // solicita al usuario la entrada del nombre a asignar al entrenador
		Entrenador entrenador1 = new Entrenador(nombre, 30, new ArrayList<>()); // crea el entrenador con el nombre
																				// introducido

		System.out.println("\n Hola " + nombre);
		int menuPrincipal; // almacena la opción elegida del menú principal

		do {

			System.out.println( // muestra el menú principal y solicita que se introduzca por teclado la
								// elección
					"\n ¿A dónde quieres ir? \n\n 1. Por el bosque \n 2. Alrededor del lago \n 3. Por el desierto \n 4. A donde me lleve el viento \n 5. A casa a descansar \n");
			menuPrincipal = scanner.nextInt();
			switch (menuPrincipal) {
			case 1:
				System.out.println("vas de paseo por el bosque... y de repente, aparece un pokémon: \n");
				PokemonPlanta bulbasaur = new PokemonPlanta("Bulbasaur", 35, 45, 45); // crea un pokémon
				System.out.println(bulbasaur); // muestra sus detalles
				bulbasaur.sonido(); // simula su sonido (o rugido)
				acciones(entrenador1, bulbasaur); // llama al método que pregunta al entrenador qué quiere hacer
				break;
			case 2:
				System.out.println("vas de paseo alrededor del lago... y de repente, aparece un pokémon: \n");
				PokemonAgua squirtle = new PokemonAgua("Squirtle", 30, 35, 40);
				System.out.println(squirtle);
				squirtle.sonido();
				acciones(entrenador1, squirtle);
				break;
			case 3:
				System.out.println("vas de paseo por el desierto... y de repente, aparece un pokémon: \n");
				PokemonFuego charmander = new PokemonFuego("Charmander", 40, 30, 35);
				System.out.println(charmander);
				charmander.sonido();
				acciones(entrenador1, charmander);
				break;
			case 4:
				System.out.println("vas de paseo sin rumbo fijo... ¿qué pasará?... de repente aparece un pokémon: \n");
				Pokemon pokemonAleatorio = salvajeAleatorio(); // crea un pokémon, eligiendo al azar uno de la lista de salvajes
				acciones(entrenador1, pokemonAleatorio); // pregunta al usuario qué quiere hacer con él
				break;
			case 5:
				System.out.println("Vuelves a casa a descansar. ¡Hasta la próxima!"); // fin del juego
				break;
			default:
				System.out.println("Opción no válida \n");
				break;
			}

		} while (menuPrincipal != 5);

		scanner.close();
	} // fin main

	/**
	 * selecciona y devuelve un pokémon salvaje aleatoriamente de los que hay en la lista de pokémos salvajes.
	 * Estos pokémons son creados y añadidos en este método.
	 * @return devuelve un pokémon aleatorio de la lista de salvajes
	 */
	
	public static Pokemon salvajeAleatorio() {
		
		// se crean pokémos de diferentes tipos y se añaden al ArrayList de pokémons salvajes
		PokemonFuego charizard = new PokemonFuego("Charizard", 50, 40, 50); // se crean
		PokemonAgua blastoise = new PokemonAgua("Blastoise", 50, 45, 50);
		PokemonPlanta venusaur = new PokemonPlanta("Venusaur", 50, 45, 50);
		PokemonFuego ninetales = new PokemonFuego("Ninetales", 50, 40, 50);
		PokemonAgua vaporeon = new PokemonAgua("Vaporeon", 50, 45, 50);
		PokemonPlanta leafeon = new PokemonPlanta("Leafeon", 50, 45, 50);
		listaSalvajes.add(charizard); // se añaden
		listaSalvajes.add(blastoise);
		listaSalvajes.add(venusaur);
		listaSalvajes.add(ninetales);
		listaSalvajes.add(vaporeon);
		listaSalvajes.add(leafeon);
		int numeroAleatorio = (int) (Math.random() * listaSalvajes.size()); // selecciona aleatoriamente un pokémon de la lista
		Pokemon pokemonAleatorio = listaSalvajes.get(numeroAleatorio);
		System.out.println(pokemonAleatorio + "\n ¡¡es enorme!! \n");
		return pokemonAleatorio; // devuelve el pokémon que se ha seleccionado aleatoriamente
		
	} // fin salvajeAleatorio()
	
	/**
	 * el método acciones muestra un menú (un switch) con las acciones que puede realizar un entrenador cuando 
	 * se encuentra con un pokémon salvaje, pudiendo elegir un pokémon del equipo, atacar con él, capturar al 
	 * salvaje o escapar. Se seguirá ejecutando mientras el entrenador no escape o capture al salvaje o se 
	 * escape. 
	 * 
	 * @param entrenador - el entrenador que va a realizar la acción
	 * @param salvaje - el pokémon salvaje con el que interactúa
	 */

	public static void acciones(Entrenador entrenador, Pokemon salvaje) {

		Scanner scanner = new Scanner(System.in);
		int eleccionAccion; // almacena la acción elegida

		do {
			System.out.println( // muestra el menú de acciones
					"¿qué quieres hacer? \n\n 1. Elegir un Pokémon \n 2. Atacar \n 3. Capturar \n 4. Escapar \n");
			eleccionAccion = scanner.nextInt();

			switch (eleccionAccion) {
			case 1: // elegir un pokémon de compañero
				entrenador.elegirPokemon();
				break;
			case 2: // atacar al pokémon salvaje
				if (pokemonElegido == null) { // si no ha elegido un compañero, tendrá que hacerlo
					System.out.println("para atacar, primero deber elegir un pokémon");

				} else { // si el entrenador ha elegido un compañero, este ataca, si la vida del salvaje es > 0
				
					if (salvaje.getVida() > 0) {
						pokemonElegido.atacar(salvaje);
					} else { // si se le acaba la vida al salvaje, se acaba el combate
						System.out.println("el pokémon " + salvaje.getNombre() + " se ha debilitado. Fin del combate");
					}
					break;
				}
				break;
			case 3: // intenta capturar al salvaje
				if (entrenador.getPokeballs() == 0) { // si no quedan pokeballs, no se podrá capturar
					System.out.println("ya no te quedan más pokeballs");
				} else { // si le quedan pokeball, intenta capturar al salvaje llamando a capturar()
					entrenador.capturar(salvaje);
				}
				return; // sale al menú principal tras el intento de captura
			case 4: // escapa del combate, llamando al método escapar()
				entrenador.escapar();
				return; // sale al menú principal al escapar
			default:
				System.out.println("opción no válida");
				break; 
			}
		} while (eleccionAccion != 4);

		scanner.close();
	} // fin menu de acciones()

}
