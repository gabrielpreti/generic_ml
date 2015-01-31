package com.ml.v2.flow.slot.api;

import com.ml.v2.flow.event.api.Event;
import com.ml.v2.flow.event.api.Measurable;

/**
 * Slots que armazenam métricas para cada Measurable relacionada ao conjunto de eventos.
 * 
 * @author gsantiago
 *
 * @param <N>
 *            Tipo da métrica, deve ser numérica
 * @param <E>
 *            Tipo do evento armazenado.
 */
public interface MeasurableMetricsSlot<N extends Number, E extends Event> extends MeasurableSlot<E> {

    N getMetric(Measurable m);

    N getDefaultMetricValue();
}
