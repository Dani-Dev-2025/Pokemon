package pokemon;

import java.io.Serializable;

/**
 * el interface Ataque contiene el método abstracto atacar() que implementarán
 * los pokémons a través de las clases correspondientes
 * 
 * @author Daniel Pacheco
 * @version 1.2
 * @since 2025/05/05
 */

public interface Ataque extends Serializable {
	
	/**
	 * método abstracto a implementar por las clases correspondientes y que servirá
	 * para que el pokémon compañero (pokemonElegido) ataque a los pokémos salvajes
	 * @param salvaje - el pokémon salvaje al que se ataca
	 */
	public abstract void atacar(Pokemon salvaje);
}
