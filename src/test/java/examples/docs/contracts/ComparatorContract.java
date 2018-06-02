package examples.docs.contracts;

import net.jqwik.api.*;
import org.assertj.core.api.*;

import java.util.*;

import static java.lang.Integer.signum;

/**
 * Example is "borrowed" from junit-quickcheck.
 * See http://pholser.github.io/junit-quickcheck/site/0.8/usage/contract-tests.html
 */
public interface ComparatorContract<T> {
	Comparator<T> subject();

	@Property
	default void symmetry(@ForAll T x, @ForAll T y) {
		Comparator<T> subject = subject();

		Assertions.assertThat(signum(subject.compare(x, y))).isEqualTo(-signum(subject.compare(y, x)));
	}
}