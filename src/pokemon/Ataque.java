package pokemon;

/**
 * el interface Ataque contiene el método abstracto atacar() que implementarán
 * los pokémons a través de las clases correspondientes
 * 
 * @author Daniel Pacheco
 * @version 1.0
 * @since 2025/04/08
 */

public interface Ataque {
	
	/**
	 * método abstracto a implementar por las clases correspondientes y que servirá
	 * para que el pokémon compañero (pokemonElegido) ataque a los pokémos salvajes
	 * @param salvaje - el pokémon salvaje al que se ataca
	 */
	public abstract void atacar(Pokemon salvaje);
}
