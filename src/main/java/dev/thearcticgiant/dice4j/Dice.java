package dev.thearcticgiant.dice4j;

import java.util.Iterator;
import java.util.Random;

/**
 * A subclass of Roll that represents a fixed number of identical dice.
 */
public class Dice extends Roll{
	public final int count, sides;

	private static Die[] makeDice(int count, int sides, Random random){
		final Die[] dice = new Die[count];
		for(int i=0; i<count; i++) dice[i] = new Die(sides, random);
		return dice;
	}

	/**
	 * Construct a roll of the format xdy.
	 * Random rolls are determined by the provided Random.
	 * @param count A positive integer indicating number of dice rolled.
	 * @param sides A positive integer indicating the number of sides on each die.
	 * @param random The Random object for making rolls.
	 */
	public Dice(int count, int sides, Random random){
		super(makeDice(count, sides, random));
		this.count = count;
		this.sides = sides;
	}

	/**
	 * Construct a roll of the format xdy+z.
	 * @param count A positive integer indicating number of dice rolled.
	 * @param sides A positive integer indicating the number of sides on each die.
	 * @throws RuntimeException if count or sides is non-positive.
	 */
	public Dice(int count, int sides){
		this(count, sides, new Random());
	}

	/**
	 * Construct a seeded roll of the format xdy+z.
	 * @param count A positive integer indicating number of dice rolled.
	 * @param sides A positive integer indicating the number of sides on each die.
	 * @param seed The seed for the generation of rolls.
	 * @throws RuntimeException if count or sides is non-positive.
	 */
	public Dice(int count, int sides, long seed){
		this(count, sides, new Random(seed));
	}

	@Override
	public int read(){
		int total = 0;
		for(Rollable die : rolls){
			total+=die.read();
		}
		return total;
	}

	@Override
	public Dice roll(){
		if(!isLocked()) for(Rollable die : rolls) die.roll();
		return this;
	}

	/**
	 * The count and sides of this Dice in the format xdy.
	 * @return The name of this Dice.
	 */
	@Override
	public String getName(){
		return String.format("%dd%d", count, sides);
	}

	/**
	 * Equivalent to <code>getName</code> with the "d" bolded.
	 * @return The markdown formatted name of this Dice.
	 */
	@Override
	public String getMarkdownName(){
		return String.format("%d**d**%d", count, sides);
	}

	/**
	 * A string representation of the roll.
	 * The equation is always equal to total().
	 * In the format "[a, b, c, ...] = t".
	 * @return A string representing the rolled dice.
	 */
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append('[');
		for(Iterator<Rollable> i = rolls.iterator(); i.hasNext();){
			builder.append(i.next().read());
			if(i.hasNext()) builder.append(", ");
		}
		builder.append(']');
		return builder.toString();
	}

	/**
	 * Equivalent to toString, with any max rolls bolded.
	 * @return A markdown formatted string representing the rolled dice.
	 */
	@Override
	public String toMarkdownString(){
		StringBuilder builder = new StringBuilder();
		builder.append('[');
		for(Iterator<Rollable> i = rolls.iterator(); i.hasNext();){
			int die = i.next().read();

			if(die == sides)
				builder.append("**")
						.append(die)
						.append("**");
			else builder.append(die);

			if(i.hasNext()) builder.append(", ");
		}
		builder.append(']');
		return builder.toString();
	}
}
