package pokemon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * La clase Juego contiene la lógica del juego. Contiene un ArrayList de
 * pokémons salvajes, el pokémon que el entrenador elija como compañero una vez
 * lo capture, el método main (que inicia el juego) y los métodos
 * salvajeAleatorio(), que hace que aparezca un pokémon salvaje de la lista, el
 * método acciones() que corresponde al menú de acciones que puede realizar el
 * entrenador cuando se encuentra con un pokémon salvaje. Por otro lado, están
 * los métodos para ejecutar el menú de inicio, iniciar nueva partida, guardar
 * partida o cargar partida.
 * 
 * @author Daniel Pacheco
 * @version 1.2
 * @since 2025/05/05
 */

public class Juego {

	static ArrayList<Pokemon> listaSalvajes = new ArrayList<>(); // la lista de Pokemons salvajes
	static Pokemon pokemonElegido; // almacena el pokemon que el entrenador ha elegido como compañero con el método
									// elegirPokemon();
	static long horaDeInicio = System.currentTimeMillis(); // captura el tiempo en cuanto iniciamos el programa
	static int partidasJugadas = 0; // contador que almacena la cantidad de partidas jugadas
	static long horaDeGuardado = System.currentTimeMillis(); // Captura el tiempo actual nuevamente
	static double tiempoTranscurrido; // almacena el tiempo de juego en la partida
	static double tiempoTotalJugado = 0; // contador para el tiempo total de juego
	static int minutos = 0; // contador total de minutos
	static int horas = 0; // contador total de horas
	static LocalDate fechaPartida = LocalDate.now(); // fecha de la partida actual

	/**
	 * el método main comienza dando la bienvenida al usuario (el entrenador
	 * pokémon) y le solicita elija entre iniciar una nueva partida o carga una
	 * guardada. Si elige comenzar una nueva, le pide al usuario que ingrese su
	 * nombre de entrenador por teclado. Con ese nombre, se crea un objeto
	 * Entrenador llamando al constructor parametrizado. Si decide cargar una
	 * partida, cargará los datos. Si lo intenta y no hay nada guardado, comenzará
	 * una nueva partida. El programa le saluda y ejecuta el menú principal (que
	 * está dentro de un bucle do-while: mientras el usuario no elija la opción de
	 * volver a casa, el menú se seguirá ejecutando, solicitando así que ingrese una
	 * de las opciones contempladas en el switch). El programa le pregunta al
	 * entrenador a dónde quiere dirigirse, pudiendo elegir entre ir al bosque
	 * (donde aparecerá un pokémon de tipo planta), alrededor del lago (aparecerá
	 * uno de tipo agua), por el desierto (tipo fuego), a "donde le lleve el
	 * viento", un lugar incierto (con lo que podrá aparecer cualquier tipo de
	 * pokémon), al hotel a descansar (guardar partida), volver al menú de inicio o
	 * a ir a casa (fin partida).
	 */

	public static void main(String[] args) {

		cargarPartida(null, true); // lo llamamos para cargar el valor de partidas jugadas y contar desde ahí
		Scanner scanner = new Scanner(System.in); // creamos el objeto Scanner que necesitamos
		Entrenador entrenador1 = ejecutarMenuInicial(scanner); // creamos el entrenador llamando al método que ejecuta
																// el menú inicial

		int menuPrincipal; // almacena la opción elegida del menú principal

		do {

			System.out.println( // muestra el menú principal y solicita que se introduzca la elección
					"\n ¿Qué te apetece hacer? \n\n 1. Ir por el bosque \n 2. Ir alrededor del lago \n 3. Ir por el desierto \n 4. Ir a donde me lleve el viento \n 5. Ir al Hotel (guardar partida) \n 6. Volver al menú de inicio \n 7. A casa a Pueblo Paleta, estoy reventado (fin partida)");
			menuPrincipal = scanner.nextInt();
			scanner.nextLine(); // limpia el buffer
			switch (menuPrincipal) {
			case 1:
				System.out.println("vas de paseo por el bosque... y de repente, aparece un pokémon: \n");
				PokemonPlanta bulbasaur = new PokemonPlanta("Bulbasaur", 35, 45, 45); // crea un pokémon
				System.out.println(bulbasaur); // muestra sus detalles
				bulbasaur.sonido(); // simula su sonido (o rugido)
				acciones(entrenador1, bulbasaur, scanner); // llama al método que pregunta al entrenador qué quiere
															// hacer
				break;
			case 2:
				System.out.println("vas de paseo alrededor del lago... y de repente, aparece un pokémon: \n");
				PokemonAgua squirtle = new PokemonAgua("Squirtle", 30, 35, 40);
				System.out.println(squirtle);
				squirtle.sonido();
				acciones(entrenador1, squirtle, scanner);
				break;
			case 3:
				System.out.println("vas de paseo por el desierto... y de repente, aparece un pokémon: \n");
				PokemonFuego charmander = new PokemonFuego("Charmander", 40, 30, 35);
				System.out.println(charmander);
				charmander.sonido();
				acciones(entrenador1, charmander, scanner);
				break;
			case 4:
				System.out
						.println("vas de paseo sin rumbo fijo... ¿qué pasará? de repente... ¡aparece un pokémon!: \n");
				Pokemon pokemonAleatorio = salvajeAleatorio(); // crea un pokémon, eligiendo al azar uno de la lista de
																// salvajes
				acciones(entrenador1, pokemonAleatorio, scanner); // pregunta al usuario qué quiere hacer con él
				break;
			case 5:
				System.out.println("Vas a la habitación del Hotel a planchar la oreja...");
				guardarPartida(entrenador1); // guarda la partida llamando al método correspondiente
				break;
			case 6:
				System.out.println("Ok, volvemos al menú de inicio \n");
				entrenador1 = ejecutarMenuInicial(scanner); // vuelve al menú llamando al método correspondiente
				break;
			case 7: // finaliza el juego con un mensaje de despedida
				System.out.println("Vuelves a casa a Pueblo Paleta y finalizas el viaje... ¡hasta la próxima!");
				break;
			default:
				System.out.println("Opción no válida \n");
				break;
			}

		} while (menuPrincipal != 7);

		scanner.close(); // cierra el scanner
	} // fin del main

	/**
	 * selecciona y devuelve un pokémon salvaje aleatoriamente de los que hay en la
	 * lista de pokémos salvajes. Estos pokémons son creados y añadidos en este
	 * método.
	 * 
	 * @return devuelve un pokémon aleatorio de la lista de salvajes
	 */

	public static Pokemon salvajeAleatorio() {

		// se crean pokémos de diferentes tipos y se añaden al ArrayList de pokémons
		// salvajes
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
		int numeroAleatorio = (int) (Math.random() * listaSalvajes.size()); // selecciona aleatoriamente un pokémon de
																			// la lista
		Pokemon pokemonAleatorio = listaSalvajes.get(numeroAleatorio);
		System.out.println(pokemonAleatorio + "\n ¡¡es enorme!! \n");
		return pokemonAleatorio; // devuelve el pokémon que se ha seleccionado aleatoriamente

	} // fin salvajeAleatorio()

	/**
	 * el método acciones muestra un menú (un switch) con las acciones que puede
	 * realizar un entrenador cuando se encuentra con un pokémon salvaje, pudiendo
	 * elegir un pokémon del equipo, atacar con él, capturar al salvaje o escapar.
	 * Se seguirá ejecutando mientras el entrenador no escape o capture al salvaje o
	 * se escape.
	 * 
	 * @param entrenador - el entrenador que va a realizar la acción
	 * @param salvaje    - el pokémon salvaje con el que interactúa
	 * @param scanner    - leer la elección del usuario por teclado
	 */

	public static void acciones(Entrenador entrenador, Pokemon salvaje, Scanner scanner) {

		int eleccionAccion; // almacena la acción elegida

		do {
			System.out.println( // muestra el menú de acciones
					"¿qué quieres hacer? \n\n 1. Elegir un Pokémon \n 2. Atacar \n 3. Capturar \n 4. Escapar \n");
			eleccionAccion = scanner.nextInt();
			scanner.nextLine(); // limpiar buffer
			switch (eleccionAccion) {
			case 1: // elegir un pokémon de compañero
				entrenador.elegirPokemon(scanner);
				break;
			case 2: // atacar al pokémon salvaje
				if (pokemonElegido == null) { // si no ha elegido un compañero, tendrá que hacerlo
					System.out.println("para atacar, primero deber elegir un pokémon");

				} else { // si el entrenador ha elegido un compañero, este ataca, si la vida del salvaje
							// es > 0

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

	} // fin menu de acciones()

	/**
	 * El método ejecutarMenuInicial() muestra el primer menú que aparece al
	 * arrancar el juego. Permite elegir entre empezar una nueva partida o cargar
	 * una guardada previamente, según la opción que el usuario introduzca por
	 * teclado. Esto se hace llamando a otros métodos: nuevapartida() y
	 * cargarPartidaInicial().
	 * 
	 * @param scanner - la elección del usuario que introduce por teclado
	 * @return - devuelve una nueva partida o carga una guardada previamente.
	 */
	public static Entrenador ejecutarMenuInicial(Scanner scanner) { // ejecuta el menu inicial

		System.out.println(
				"Bienvenido a Pokémon Java \n    ¡Hazte con todos! \n\n    Selecciona: \n\n 1. Nueva Partida \n 2. Cargar Partida");
		int eleccionMenuInicial = scanner.nextInt();
		scanner.nextLine(); // limpiar buffer
		switch (eleccionMenuInicial) {
		case 1:
			return nuevaPartida(scanner);
		case 2:
			return cargarPartida(scanner, false);
		default:
			System.out.println("opción no válida. Comenzamos una nueva aventura.");
			return nuevaPartida(scanner);
		}
	} // fin ejecutarMenuInicial()

	/**
	 * El método nuevaPartida() permite comenzar una nueva partida. Solicita al
	 * usuario que ingrese su nombre de entrenador y crea un nuevo objeto de tipo
	 * Entrenador con el nombre introducido, 30 pokéballs, incremente en 1 el
	 * contador de partidas jugadas y pone el contador de horas y minutos a 0.
	 * Termina con un saludo y devuelve el entrenador creado.
	 * 
	 * @param scanner - una cadena de texto con el nombre que el usuario introduce
	 *                como entrenador.
	 * @return - devuelve el entrenador creado.
	 */

	public static Entrenador nuevaPartida(Scanner scanner) {

		System.out.println("introduce tu nombre de entrenador: \n");
		String nombre = scanner.nextLine(); // solicita al usuario la entrada del nombre a asignar al entrenador
		Entrenador entrenador = new Entrenador(nombre, 30, new ArrayList<>()); // crea el entrenador con el nombre
		partidasJugadas++; // cada vez que empecemos una nueva partida, se incrementa en 1 el contador //
							// introducido
		horas = 0;
		minutos = 0;
		System.out.println("\n Hola " + nombre);
		return entrenador;

	} // fin nuevaPartida();

	/**
	 * El método guardarPartida() guarda los datos de la partida (entrenador, tiempo de juego transcurrido de la
	 * partida actual, minutos, horas, fecha de la partida y tiempo total acumulado en el archivo "datosdeguardado.dat"
	 * mostrando al usuario dichos datos por pantalla. En caso de que se produzca un error en el proceso de guardado,
	 * se captura la excepción y se avisa por pantalla.
	 * 
	 * @param entrenador - datos del entrenador creado
	 */

	public static void guardarPartida(Entrenador entrenador) {
		
		/* creea un ObjectOutputStream para escribir datos en el archivo de salida especificado.
		 * Gracias al Try-With-Resources el flujo se cierra y se captura la excepción que se pueda producir.
		 */
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("datosdeguardado.dat"))) {
			horaDeGuardado = System.currentTimeMillis(); // Captura el tiempo actual nuevamente
			tiempoTranscurrido = (horaDeGuardado - horaDeInicio) / 1000.0; // Calcula el tiempo transcurrido en
																			// segundos
			minutos = (int) (tiempoTranscurrido / 60.0); // Convierte el tiempo transcurrido de segundos a minutos
			horas = (int) (minutos / 60.0); // Convierte el tiempo transcurrido de minutos a horas
			fechaPartida = LocalDate.now(); // almacena la fecha de la partida actual
			tiempoTotalJugado = tiempoTranscurrido + tiempoTotalJugado; // acumula el tiempo total de juego
			
			// escribe los datos en el archivo
			oos.writeObject(entrenador); // los datos del entrenador
			oos.writeDouble(tiempoTranscurrido); // tiempo de  la partida actual
			oos.writeInt(minutos); // minutos jugados
			oos.writeInt(horas); // horas jugadas
			oos.writeDouble(tiempoTotalJugado); // tiempo total acumulado
			oos.writeObject(fechaPartida); // fecha actual
			oos.writeInt(partidasJugadas); // total de partidas jugadas
			
			// muestra por pantalla los datos del entrenador (nombre, un for recorre el equipo)
			System.out.println("\n** ¡Partida guardada con éxito! **\n");
			System.out.println("Entrenador: " + entrenador.getNombre());
			for (int i = 0; i < entrenador.getEquipo().size(); i++) {
				System.out.println((i + 1) + (", ") + entrenador.getEquipo().get(i).toString());
			}
			// muestra los datos de la partida
			System.out.println("Tiempo total de juego: " + horas + " horas y " + minutos + " minutos \n"
					+ "Partidas jugadas: " + partidasJugadas + "\nFecha: " + fechaPartida);

		} catch (IOException e) {
			System.out.println("se ha producido un error al guardar la partida. Inténtalo de nuevo. " + e.getMessage());
		}

	} // fin guardarPartida()

	/**
	 * El método cargarPartida() carga una partida que hayamos guardado previamente. Primero verifica que el archivo de 
	 * guardado exista.  En el caso de que no haya ninguna partida guardada, comienza una nueva llamando
	 * al método nuevaPartida(), iniciando los contadores de tiempo y demás a 0.
	 * @param scanner - lee entrada por teclado si se necesita crear una nueva partida
	 * @param inicial - indica si la carga es al iniciar el programa (true) o desde el menú (false), es decir, si
	 * estamos empezando el juego recién arrancado (main) o si el usuario pidió empezar una nueva partida (nuevaPartida()).
	 * @return - devuelve el entrenador que había guardado o null si se carga al inicio y no se necesita aún un Entrenador
	 */
	
	public static Entrenador cargarPartida(Scanner scanner, boolean inicial) {
		
		// crea un objeto File para verificar si existe el archivo de guardado
		File f = new File("datosdeguardado.dat");
		// si el archivo no existe, inicia los contadores o crea una nueva partida llamando al método
		if (!f.exists()) {
			if (inicial) { // si se acaba de arranzar el programa, inicia contadores a 0
				partidasJugadas = 0;
				tiempoTranscurrido = 0;
				horas = 0;
				minutos = 0;
				tiempoTotalJugado = 0;
				fechaPartida = LocalDate.now();
				return null; // en modo inicial no hay que devolver un Entrenador de momento
			} else {
				System.out.println("actualmente no hay ninguna partida guardada. ¡Comenzamos una nueva aventura!");
				return nuevaPartida(scanner);
			}
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
			Entrenador entrenadorGuardado = (Entrenador) ois.readObject();
			tiempoTranscurrido = ois.readDouble();
			minutos = ois.readInt();
			horas = ois.readInt();
			tiempoTotalJugado = ois.readDouble();
			fechaPartida = (LocalDate) ois.readObject();
			partidasJugadas = ois.readInt();
			if (!inicial) { // si se inicia nueva partida desde el menú y no al arrancar el programa
				partidasJugadas++; // incrementamos las partidas en 1
			}

			if (inicial) { // si acabamos de arranar el programa
				return null; // no tiene que devolver un Entrenador de momento
			}
			// muestra pro pantalla los datos cargados, entrenador, equipo (recorrido con for) y datos de juego
			System.out.println("** partida cargada con éxito **\n");
			System.out.println("Entrenador: " + entrenadorGuardado.getNombre() + "\nEquipo: ");
			for (int i = 0; i < entrenadorGuardado.getEquipo().size(); i++) {
				System.out.println((i + 1) + (", ") + entrenadorGuardado.getEquipo().get(i).toString());
			}
			System.out.println("Tiempo total de juego: " + horas + " horas y " + minutos + " minutos \n"
					+ "Partidas jugadas: " + partidasJugadas + "\nFecha: " + fechaPartida);

			return entrenadorGuardado; // devuelve el objeto Entrenador que había guardado
		
		/*
		 * captura y maneja las excepciones que puedan surgir dependiendo de cuando se producen. Si
		 * surgen al inicio del programa, inicia los contadores a 0 y devuelve null, con lo que el 
		 * juego comienza de cero. Si urgen al cargar el menú, llamará a nuevaPartida.
		 */
			
		} catch (IOException e) { // captura y maneja errores de lectura del archivo
			System.out.println("Error al cargar la partida." + e.getMessage());
			if (inicial) { // si se acaba de arrancar el programa, contadores a 0:
				partidasJugadas = 0;
				horas = 0;
				minutos = 0;
				fechaPartida = LocalDate.now();
				return null; // no hay que devolver entrenador, se acaba de arrancar el programa
			} else { // si ya habíamos jugado alguna partida, se llama al método nuevaPartida()
				System.out.println("Comenzamos una nueva aventura");
				return nuevaPartida(scanner);
			}

		} catch (ClassNotFoundException e) { // captura y maneja errores de clase no encontrada
			System.out.println("error al cargar la partida." + e.getMessage());
			if (inicial) { // si se acaba de arrancar el programa, contadores a 0:
				partidasJugadas = 0;
				horas = 0;
				minutos = 0;
				fechaPartida = LocalDate.now();
				return null; // no hay que devolver entrenador, se acaba de arrancar el programa
			} else { // si ya habíamos jugado alguna partida, se llama al método nuevaPartida()
				System.out.println("Comenzamos una nueva aventura");
				return nuevaPartida(scanner);
			}
		}

	} // fin cargarPartida()

} // fin Juego
