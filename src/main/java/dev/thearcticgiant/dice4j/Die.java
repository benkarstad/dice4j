package dev.thearcticgiant.dice4j;

import java.util.Random;

public class Die implements Rollable{
	public final int sides;
	private boolean locked;
	private int roll;

	private final Random random;

	/**
	 * Construct a new die, using a provided Random object.
	 * @param sides The number of sides.
	 * @param random The Random used to generate rolls.
	 */
	public Die(int sides, Random random){
		this.sides = sides;
		this.random = random;

		roll();
	}

	/**
	 * Construct and roll a new die with a specific random seed.
	 * @param sides The number of sides.
	 * @param seed The seed used to create the internal Random object.
	 */
	public Die(int sides, long seed){
		this(sides, new Random(seed));
	}

	/**
	 * Construct a new die.
	 * @param sides The number of sides.
	 */
	public Die(int sides){
		this(sides, new Random());
	}

	@Override
	public Die roll(){
		if(!locked) roll = random.nextInt(sides)+1;
		return this;
	}

	@Override
	public int read(){
		return roll;
	}

	@Override
	public final void lock(){
		locked = true;
	}

	@Override
	public final boolean isLocked(){
		return locked;
	}

	@Override
	public String getName(){
		return String.format("1d%d", sides);
	}

	@Override
	public String getMarkdownName(){
		return String.format("1**d**%d", sides);
	}

	@Override
	public String toString(){
		return Integer.toString(roll);
	}

	@Override
	public String toMarkdownString(){
		StringBuilder builder = new StringBuilder();

		if(roll >= sides) builder.append("**").append(roll).append("**");
		else builder.append(roll);

		return builder.toString();
	}
}
