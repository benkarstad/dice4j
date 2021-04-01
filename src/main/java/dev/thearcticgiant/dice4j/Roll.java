package dev.thearcticgiant.dice4j;

import java.util.Iterator;
import java.util.Set;

public class Roll implements Rollable{
	public final Set<Die> dice;
	public final int count, sides, bonus;

	/**
	 * Construct a roll of the format xdy+z.
	 * @param count A non-negative integer indicating number of dice rolled.
	 * @param sides A positive integer indicating the number of sides on each die.
	 * @param bonus The static bonus applied to the roll.
	 * @throws RuntimeException if count is negative, or sides is non-positive.
	 */
	public Roll(int count, int sides, int bonus){
		if(count < 0) throw new RuntimeException("count cannot be negative");
		if(count > 0 && sides <= 0) throw new RuntimeException("sides must be positive");
		this.count = count;
		this.sides = sides;
		this.bonus = bonus;

		final Die[] dice = new Die[count];
		for(int i=0; i<count; i++) dice[i] = new Die(sides);
		this.dice = Set.of(dice);
	}

	/**
	 * Construct a roll of the format xdy.
	 * Equivalent to Roll(count, sides, 0).
	 * @param count A non-negative integer indicating number of dice rolled.
	 * @param sides A positive integer indicating the number of sides on each die.
	 * @throws RuntimeException if count is negative, or sides is non-positive.
	 */
	public Roll(int count, int sides){
		this(count, sides, 0);
	}

	/**
	 * Construct an empty roll.
	 * Equivalent to Roll(0, 0, 0)
	 */
	public Roll(){
		this(0, 0, 0);
	}

	/**
	 * Calculate the total rolled plus the bonus.
	 * @return The computed total.
	 */
	public int read(){
		int total = bonus;
		for(Die die : dice){
			total+=die.read();
		}
		return total;
	}

	/**
	 * Roll all dice.
	 * @return The total rolled.
	 */
	public int roll(){
		for(Die die : dice) die.roll();
		return read();
	}


	@Override
	public String getName(){
		StringBuilder builder = new StringBuilder();
		boolean hasDice = false;
		if(count > 0){
			hasDice = true;
			builder.append(count).append('d').append(sides);
		}
		if(bonus<0) builder.append(bonus);
		else if(bonus>0){
			if(hasDice) builder.append('+');
			builder.append(bonus);
		} else if(!hasDice) builder.append(0);
		return builder.toString();
	}

	@Override
	public String getMarkdownName(){
		StringBuilder builder = new StringBuilder();
		boolean hasDice = false;
		if(count > 0){
			hasDice = true;
			builder.append(count).append("**d**").append(sides);
		}
		if(bonus<0) builder.append(bonus);
		else if(bonus>0){
			if(hasDice) builder.append("*+*");
			builder.append(bonus);
		} else if(!hasDice) builder.append(0);
		return builder.toString();
	}

	/**
	 * A string representation of the roll.
	 * The equation is always equal to total().
	 * In the format "[a, b, c, ...]+k = t".
	 * @return A string representing the rolled dice.
	 */
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append('[');
		for(Iterator<Die> i=dice.iterator();i.hasNext();){
			builder.append(i.next().read());
			if(i.hasNext()) builder.append(", ");
		}
		builder.append(']');
		if(bonus > 0) builder.append('+').append(bonus);
		else if(bonus < 0) builder.append('-').append(-bonus);
		builder.append(" = ")
				.append(read());
		return builder.toString();
	}

	@Override
	public String toMarkdownString(){
		StringBuilder builder = new StringBuilder();
		builder.append('[');
		for(Iterator<Die> i=dice.iterator();i.hasNext();){
			int die = i.next().read();

			if(die == sides)
				builder.append("**")
						.append(die)
						.append("**");
			else builder.append(die);

			if(i.hasNext()) builder.append(", ");
		}
		builder.append(']');
		if(bonus > 0) builder.append("*+*").append(bonus);
		else if(bonus < 0) builder.append('-').append(-bonus);
		builder.append(" = ")
				.append(read());
		return builder.toString();
	}
}
