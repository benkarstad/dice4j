package dev.thearcticgiant.dice4j;

import java.util.Random;

public class Die implements Rollable{
	public final int sides;
	private int roll;

	private final Random random;

	/**
	 * Construct and roll a new die with a specific random seed.
	 * @param sides The number of sides.
	 * @param seed The seed used to create the internal Random object.
	 * @throws RuntimeException if sides is non-positive.
	 */
	public Die(int sides, long seed){
		this(sides, new Random(seed));
	}

	/**
	 * Construct and roll a new die.
	 * @param sides The number of sides.
	 * @throws RuntimeException if sides is non-positive.
	 */

	public Die(int sides){
		this(sides, new Random());
	}

	private Die(int sides, Random random){
		this.sides = sides;
		this.random = random;

		if(sides <= 0) throw new RuntimeException("sides must be positive");

		roll();
	}

	/**
	 * Re-roll this die;
	 * @return The new result;
	 */
	public int roll(){
		return roll = random.nextInt(sides)+1;
	}

	public int read(){
		return roll;
	}

	public int fudge(int roll){
		return this.roll = roll;
	}

	public String getName(){
		return String.format("d%d", sides);
	}

	public String toString(){
		return String.format("%s (%d)", getName(), roll);
	}
}
