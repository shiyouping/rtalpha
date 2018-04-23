package com.rtalpha.base.kernel.utility;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

/**
 * 
 * Generate a random string with the specific length
 * 
 * @author Ricky
 * @since Apr 17, 2017
 *
 */
public class RandomString {

	private static final List<String> numbers = Lists.newArrayList("0", "1", "2", "3", "4", "5", "6", "7", "9");
	private static final List<String> lowercaseLetters = Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i",
			"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
	private static final List<String> uppercaseLetters = Lists.newArrayList("A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
	private static final Random random = new Random();

	private RandomString() {
	}

	@Nonnull
	public static String generate(@Nonnegative int length, boolean isNumberOnly, boolean isCaseSensitive) {
		checkArgument(length > 0, "length must > 0");
		List<String> seed = Lists.newArrayList();

		if (isNumberOnly) {
			seed.addAll(numbers);
		} else if (isCaseSensitive) {
			seed.addAll(numbers);
			seed.addAll(lowercaseLetters);
			seed.addAll(uppercaseLetters);
		} else {
			seed.addAll(numbers);
			seed.addAll(lowercaseLetters);
		}

		List<String> randomStrings = Lists.newLinkedList();
		for (int i = 0; i < length; i++) {
			String s = seed.get(random.nextInt(seed.size()));
			randomStrings.add(s);
		}

		Collections.shuffle(randomStrings);
		return StringUtils.join(randomStrings, null);
	}
}
