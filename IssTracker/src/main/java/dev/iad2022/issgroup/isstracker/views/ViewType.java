package dev.iad2022.issgroup.isstracker.views;

import javafx.util.StringConverter;

import java.util.Arrays;
import java.util.Optional;

public enum ViewType {
    MAP("World Map"),
    POLAND("Poland View");
    public final String label;

    ViewType(String label){
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
    public static Optional<ViewType> retrieveByLabel(String label) {
        return Arrays.stream(ViewType.values())
                .filter(viewType -> viewType.label.equals(label))
                .findFirst();
    }

    public static StringConverter<ViewType> createStringConverter(){
        return new StringConverter<ViewType>() {
            @Override
            public String toString(ViewType object) {
                return object.getLabel();
            }
            @Override
            public ViewType fromString(String string) {
                Optional<ViewType> unit = ViewType.retrieveByLabel(string);
                if (unit.isEmpty()) throw new IllegalArgumentException();
                return unit.get();
            }
        };
    }
}
