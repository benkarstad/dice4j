package dev.thearcticgiant.dice4j;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Roll implements Rollable{
	private static Matcher matcher = Pattern.compile("(?:(?<count>[+-]?\\d++)?d(?<sides>[+-]?\\d++))?(?<bonus>[+-]?\\d++)?").matcher("");

	public final List<Rollable> rolls;
	private boolean locked = false;

	public Roll(Rollable... rolls){
		this.rolls = List.of(rolls);
	}
	public Roll(int count, int sides, int bonus){
		this(new Dice(count, sides), new Bonus(bonus));
	}
	public Roll(int count, int sides){
		this(new Dice(count, sides));
	}

	public static Roll of(String exp){
		if(!matcher.reset(exp).matches()) throw new RuntimeException("invalid dice expression");
		String  countStr = matcher.group("count"),
				sidesStr = matcher.group("sides"),
				bonusStr = matcher.group("bonus");

		if(countStr == null){
			if(sidesStr == null) countStr = sidesStr = "0";
			else countStr = "1";
		}

		if(bonusStr == null) bonusStr = "0";

		final int
				count = Integer.parseInt(countStr),
				sides = Integer.parseInt(sidesStr),
				bonus = Integer.parseInt(bonusStr);
		if(count <= 0 || sides <= 0) return new Roll(new Bonus(bonus));
		else if(bonus == 0) return new Roll(count, sides);
		return new Roll(count, sides, bonus);
	}

	public static Roll of(int count, int sides, int bonus){
		return new Roll(count, sides, bonus);
	}

	public static Roll of(int count, int sides){
		return new Roll(count, sides);
	}

	@Override
	public Rollable roll(){
		for(Rollable r : rolls)r.roll();
		return this;
	}

	@Override
	public int read(){
		int total = 0;
		for(Rollable r : rolls) total += r.read();
		return total;
	}

	@Override
	public void lock(){
		locked = true;
		for(Rollable r : rolls) r.lock();
	}

	@Override
	public boolean isLocked(){
		return locked;
	}

	@Override
	public String getName(){
		StringBuilder builder = new StringBuilder();
		for(Iterator<Rollable> i=rolls.iterator();i.hasNext();){
			builder.append(i.next().getName());
			if(i.hasNext()) builder.append('+');
		}

		return builder.toString();
	}

	@Override
	public String getMarkdownName(){
		StringBuilder builder = new StringBuilder();
		for(Iterator<Rollable> i=rolls.iterator();i.hasNext();){
			builder.append(i.next().getMarkdownName());
			if(i.hasNext()) builder.append('+');
		}

		return builder.toString();
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		for(Iterator<Rollable> i=rolls.iterator(); i.hasNext();){
			builder.append(i.next().toString());
			if(i.hasNext()) builder.append('+');
		}

		return builder.toString();
	}

	@Override
	public String toMarkdownString(){
		StringBuilder builder = new StringBuilder();
		for(Iterator<Rollable> i=rolls.iterator(); i.hasNext();){
			builder.append(i.next().toMarkdownString());
			if(i.hasNext()) builder.append('+');
		}

		return builder.toString();
	}
}
