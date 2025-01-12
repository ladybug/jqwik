package net.jqwik.api.domains;

import java.time.*;
import java.util.*;

import net.jqwik.api.*;

import static org.assertj.core.api.Assertions.*;

@Group
class DomainContextBaseTests {

	@Group
	@PropertyDefaults(tries = 20)
	class ArbitraryProviders {

		@Property
		@Domain(ContextWithProviderMethods.class)
		void useProviderFromMethodWithTypedArbitraryInterface(@ForAll char aChar) {
			assertThat(aChar).isBetween('0', '9');
		}

		@Property
		@Domain(ContextWithProviderMethods.class)
		void useProviderFromMethodWithSubtypeOfArbitraryInterface(@ForAll String aString) {
			assertThat(aString).hasSize(2);
			assertThat(aString).containsOnlyDigits();
		}

		@Property
		@Domain(ContextWithProviderMethods.class)
		void useProviderFromMethodWithTargetTypeAndSubtypeProvider(@ForAll List<String> listOfStrings) {
			assertThat(listOfStrings).hasSize(3);
			listOfStrings.forEach(aString -> {
				assertThat(aString).isInstanceOf(String.class);
				assertThat(aString).hasSize(2);
				assertThat(aString).containsOnlyDigits();
			});
		}

		@Property(generation = GenerationMode.RANDOMIZED)
		@Domain(ContextWithProviderMethods.class)
		void useProviderFromMethodWithPotentiallyConflictingType(@ForAll List<LocalDate> listOfDates) {
			assertThat(listOfDates).hasSize(1);
		}

		@Property
		@Domain(ContextWithDependentProviders.class)
		void flatmapOverInjectedForAllParameter(@ForAll List<Integer> listOf5Ints) {
			assertThat(listOf5Ints).hasSize(5);
			int first = listOf5Ints.get(0);
			assertThat(first).isBetween(1, 10);
			assertThat(listOf5Ints).allMatch(i -> i == first);
		}

		@Property
		@Domain(ContextWithDependentProviders.class)
		void flatmapOverInjectedForAllParameterWithValue(@ForAll String aString) {
			assertThat(aString).isEqualTo("aa");
		}
	}

}

// private NumberStringContext() {
// 	registerConfigurator(new ArbitraryConfiguratorBase() {
// 		public Arbitrary<String> configure(Arbitrary<String> arbitrary, AbstractDomainContextBaseTests.DoubleString ignore) {
// 			return arbitrary.map(s -> s + s);
// 		}
// 	});
// }
