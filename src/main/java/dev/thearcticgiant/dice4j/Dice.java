package dev.thearcticgiant.dice4j;

import java.util.Iterator;
import java.util.Random;
import java.util.List;

public class Dice implements Rollable{
	public final List<Die> dice;
	public final int count, sides;
	private boolean locked = false;

	/**
	 * Construct a roll of the format xdy.
	 * Random rolls are determined by the provided Random.
	 * @param count A positive integer indicating number of dice rolled.
	 * @param sides A positive integer indicating the number of sides on each die.
	 * @param random The Random object for making rolls.
	 * @throws RuntimeException if count or sides is non-positive.
	 */
	public Dice(int count, int sides, Random random){
		if(count <= 0) throw new RuntimeException("count must be positive");
		if(sides <= 0) throw new RuntimeException("sides must be positive");
		this.count = count;
		this.sides = sides;

		final Die[] dice = new Die[count];
		for(int i=0; i<count; i++) dice[i] = new Die(sides, random);
		this.dice = List.of(dice);
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
		for(Die die : dice){
			total+=die.read();
		}
		return total;
	}

	@Override
	public Dice roll(){
		if(!locked) for(Die die : dice) die.roll();
		return this;
	}

	@Override
	public final void lock(){
		locked = true;
		for(Die die : dice) die.lock();
	}

	@Override
	public final boolean isLocked(){
		return locked;
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
		for(Iterator<Die> i = dice.iterator(); i.hasNext();){
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
		for(Iterator<Die> i = dice.iterator(); i.hasNext();){
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
