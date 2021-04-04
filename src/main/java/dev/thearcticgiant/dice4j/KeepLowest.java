package dev.thearcticgiant.dice4j;

import java.util.Comparator;

public class KeepLowest extends Keep{
	public KeepLowest(int count){
		super(count, new Comparator<Rollable>(){
			@Override
			public int compare(Rollable r1, Rollable r2){
				return r2.read()-r1.read();
			}
		});
	}
}
