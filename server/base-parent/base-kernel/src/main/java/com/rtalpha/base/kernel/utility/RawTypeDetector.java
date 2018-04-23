package com.rtalpha.base.kernel.utility;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import com.google.common.reflect.TypeToken;

/**
 * @author Ricky
 * @since Apr 1, 2018
 *
 */
public class RawTypeDetector {

	@SuppressWarnings("unchecked")
	public static <T> Class<T> getRawType(@Nonnull Class<?> declaringClass) {
		checkNotNull(declaringClass, "declaringClass cannot be null");

		TypeToken<T> typeToken = new TypeToken<T>(declaringClass) {
			private static final long serialVersionUID = 1L;
		};

		return (Class<T>) typeToken.getRawType();
	}
}
