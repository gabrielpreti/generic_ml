package com.ml.v2.flow.event.api;

import java.util.Date;

/**
 * Eventos que possuem um tempo específico associado.
 * 
 * @author gsantiago
 *
 */
public interface TimedEvent extends Event {
    Date getTime();
}
