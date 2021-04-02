package dev.thearcticgiant.dice4j;

public interface Rollable{

	/**
	 * Generate a new random result and return self.
	 * @return This Rollable;
	 */
	Rollable roll();

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

	/**
	 * As getName, but with markdown formatting.
	 * Should render to the same text as getName with only aesthetic differences,
	 * eg. bolding the 'd' in 1d20.
	 * Returning the exact same as getName() is acceptable.
	 * @return The name of this particular Rollable.
	 */
	String getMarkdownName();

	/**
	 * Return a full, human readable representation of this Rollable's most recent roll.
	 * The total rolled should be unambiguously calculable from the returned String.
	 * It is recommended, but not mandatory to explicitly state the total.
	 * @return The amounts rolled and any modifiers.
	 */
	String toString();

	/**
	 * As toString(), but with markdown formatting.
	 * The returned markdown should render to the same text as toString with only aesthetic changes,
	 * eg. bold-ing any maximum rolls.
	 * Returning the exact same as toString is also acceptable.
	 * @return The amounts rolled and any modifiers.
	 */
	String toMarkdownString();
}
