package com.ml.v2.flow.repository.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import com.ml.v2.flow.event.api.Measurable;
import com.ml.v2.flow.event.impl.HttpEvent;
import com.ml.v2.flow.repository.api.AbstractSlottedRepository;
import com.ml.v2.flow.repository.api.SlotKeyGenerationStrategy;
import com.ml.v2.flow.slot.impl.HttpEventSlot;

/**
 * Created by gsantiago on 1/17/15.
 */
public class HttpEventsRepository extends AbstractSlottedRepository<HttpEvent, HttpEventSlot> {
    private SlotKeyGenerationStrategy slotKeyGenerationStrategy;

    public HttpEventsRepository(SlotKeyGenerationStrategy slotKeyGenerationStrategy) {
        super(slotKeyGenerationStrategy);
    }

    @Override
    protected HttpEventSlot createSlot(String slotKey, HttpEvent firstEvent) {
        return new HttpEventSlot(slotKey, firstEvent);
    }

    public void printSlotEvents() throws IOException {
        File outputDir = new File("/tmp/slots/events/");
        if(outputDir.exists())
            outputDir.delete();

        for (String slotKey : slots.keySet()) {
            PrintWriter writer = new PrintWriter(new File(outputDir, "slot_" + slotKey));
            for (HttpEvent event : slots.get(slotKey).getEvents()) {
                writer.println(event);
            }
            writer.flush();
            writer.close();
        }
    }

    public void printSlotMetrics() throws IOException {
        File outputDir = new File("/tmp/slots/metrics/");
        if(outputDir.exists()){
            outputDir.delete();
        }
        outputDir.mkdir();

        for (String slotKey : slots.keySet()) {
            PrintWriter writer = new PrintWriter(new File(outputDir, "slot_" + slotKey));
            HttpEventSlot slot = slots.get(slotKey);
            for (Measurable measurablePieces : slot.getAllMeasurables()) {
                writer.println(String.format("%s\t%d", measurablePieces, slot.getMetric(measurablePieces)));
            }
            writer.flush();
            writer.close();
        }
    }
}
