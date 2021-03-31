package dev.thearcticgiant.dice4j;

public interface Rollable{

	/**
	 * Generate a new random result.
	 * @return The newly rolled result;
	 */
	int roll();

	/**
	 * Return the most recently rolled result;
	 * this function should be guaranteed to return the same result in successive calls until roll() is called again.
	 * @return The most recent roll.
	 */
	int read();

	/**
	 * A human readable string representation of this Rollable's state, irrespective of the result of a given roll.
	 * The result should provide all the necessary information to construct a similar Rollable.
	 * @return The name of this particular Rollable.
	 */
	String getName();
}
