package dev.thearcticgiant.dice4j;

import java.util.Comparator;

public class Keep implements Rule{
	public final int count;
	private final Comparator<Rollable> comparator;


	public Keep(int count, Comparator<Rollable> comparator){
		this.count = count;
		this.comparator = comparator;
	}
	@Override
	public Roll apply(Roll roll){
		if(count == 0) return new Roll(); //None are kept
		if(roll.rolls.size() <= count) return roll; //All are kept
		Rollable[] kept = new Rollable[count];

		for(Rollable r : roll.rolls){
			for(int i=0; i<kept.length; i++){
				if(kept[i]==null || comparator.compare(r, kept[i]) > 0){
					kept[i] = r;
					break;
				}
			}
		}

		return new Roll(kept);
	}
}
