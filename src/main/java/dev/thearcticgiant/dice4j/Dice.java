package dev.thearcticgiant.dice4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dice{
	private static Matcher matcher = Pattern.compile("(?:(?<count>[+-]?\\d++)?d(?<sides>[+-]?\\d++))?(?<bonus>[+-]?\\d++)?").matcher("");
	public static Roll roll(String exp){
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
		return roll(count, sides, bonus);
	}
	public static Roll roll(int count, int sides, int bonus){
		return new Roll(count, sides, bonus);
	}
	public static Roll roll(int count, int sides){
		return new Roll(count, sides);
	}
}
