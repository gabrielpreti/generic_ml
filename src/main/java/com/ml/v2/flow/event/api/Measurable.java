package com.ml.v2.flow.event.api;

/**
 * Indica o que será medido de um evento. Implementações dessa classe devem sobrescrever equals e hashCode
 * 
 * Created by gsantiago on 1/18/15.
 */
public abstract class Measurable {

    public abstract boolean equals(Object other);

    public abstract int hashCode();
}
