package dev.thearcticgiant.dice4j;

/**
 * Modifiers that can be sequentially applied to a Roll to achieve a more complex result.
 * Some examples could be:
 * <ul>
 *     <li>Keeping only the highest five results,</li>
 *     <li>Rolling an additional die for each maximum rolled,</li>
 *     <li>Re-rolling results lower than some threshold.</li>
 * </ul>
 */
public interface Rule{

	/**
	 * Applies this rule to the passed Roll.
	 * @param roll The Roll to which the rule will be applied.
	 * @return A new Roll.
	 */
	Roll apply(Roll roll);
}
