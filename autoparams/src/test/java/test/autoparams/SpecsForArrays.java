package test.autoparams;

import java.util.Collections;
import java.util.HashSet;

import autoparams.AutoSource;
import autoparams.Repeat;
import org.junit.jupiter.params.ParameterizedTest;

import static org.assertj.core.api.Assertions.assertThat;

class SpecsForArrays {

    @ParameterizedTest
    @AutoSource
    void sut_correctly_creates_array_of_int(int[] array) {
        assertThat(array).hasSize(3);
    }

    @ParameterizedTest
    @AutoSource
    void sut_fills_array_of_int_with_arbitrary_values(int[] array) {
        HashSet<Integer> set = new HashSet<>();
        for (int element : array) {
            set.add(element);
        }

        assertThat(set).hasSize(array.length);
    }

    @ParameterizedTest
    @AutoSource
    void sut_fills_array_of_Integer_with_arbitrary_values(Integer[] array) {
        HashSet<Integer> set = new HashSet<>();
        Collections.addAll(set, array);

        assertThat(set).hasSize(array.length);
    }

    @ParameterizedTest
    @AutoSource
    void sut_fills_array_of_complex_type_with_arbitrary_objects(ComplexObject[] array) {
        HashSet<ComplexObject> set = new HashSet<>();
        Collections.addAll(set, array);

        assertThat(set).hasSize(array.length);
    }

    @ParameterizedTest
    @AutoSource
    @Repeat(10)
    void sut_fills_array_of_enum_type_with_arbitrary_objects(EnumType[] array) {
        HashSet<EnumType> set = new HashSet<>();
        Collections.addAll(set, array);

        assertThat(set.size()).isGreaterThanOrEqualTo(1);
        assertThat(set.size()).isLessThanOrEqualTo(EnumType.values().length);
    }

    @ParameterizedTest
    @AutoSource
    void sut_fills_array_of_generic_element_type(Iterable<String>[] array) {
        assertThat(array).doesNotContainNull();
    }
}
