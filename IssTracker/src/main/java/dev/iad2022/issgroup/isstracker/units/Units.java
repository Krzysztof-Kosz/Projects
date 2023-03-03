package dev.iad2022.issgroup.isstracker.units;

import javafx.util.StringConverter;

import java.util.Arrays;
import java.util.Optional;

public enum Units {
    IMPERIAL("Imperial"),
    METRIC("Metric");
    public final String label;

    Units(String label){
        this.label = label;
    }

    public static Optional<Units> retrieveByLabel(String label) {
        return Arrays.stream(Units.values())
                .filter(units -> units.label.equals(label))
                .findFirst();
    }

    public static StringConverter<Units> createStringConverter(){
        return new StringConverter<Units>() {
            @Override
            public String toString(Units object) {
                return object.label;
            }
            @Override
            public Units fromString(String string) {
                Optional<Units> unit = Units.retrieveByLabel(string);
                if (unit.isEmpty()) throw new IllegalArgumentException();
                return unit.get(); }
        };
    }
}
