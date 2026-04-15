package com.gastro.aerolinea.avion;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ModeloAvion {

    AIRBUS_A320("Airbus A320"),
    AIRBUS_A350("Airbus A350"),
    BOEING_737("Boeing 737"),
    BOEING_777("Boeing 777"),
    BOEING_787("Boeing 787 Dreamliner"),
    EMBRAER_E190("Embraer E190");

    private final String value;

    ModeloAvion(String value) {
        this.value = value;
    }

    public static ModeloAvion fromValue(String value) {
        return Arrays.stream(values())
                .filter(model -> model.getValue().equalsIgnoreCase(value)
                        || model.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Valor del modelo de avión inválido: " + value)
                );
    }

}
