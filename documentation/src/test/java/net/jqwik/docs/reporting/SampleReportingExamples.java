package net.jqwik.docs.reporting;

import java.util.*;

import org.assertj.core.api.*;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

class SampleReportingExamples {

	@Property(afterFailure = AfterFailureMode.RANDOM_SEED)
	void reportFalsifiedSamples(
		@ForAll int anInt,
		@ForAll List<Integer> listOfInts,
		@ForAll @Size(min = 3) Map<@AlphaChars @StringLength(3) String, Integer> aMap
	) {
		Assertions.assertThat(anInt).isLessThan(10);
	}

	@Property(afterFailure = AfterFailureMode.RANDOM_SEED)
	void reportFalsifiedArrays(
		@ForAll int anInt,
		@ForAll int[] arrayOfInts,
		@ForAll @Size(min = 2) @AlphaChars @StringLength(3) String[] arrayOfStrings
	) {
		Assertions.assertThat(anInt).isLessThan(10);
	}
}