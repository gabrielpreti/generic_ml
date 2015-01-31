package com.ml.v2.model.api;

/**
 * Created by gsantiago on 1/31/15.
 */
public interface BatchedModel extends Model{
    ModelOutput runBatchAnalysis();
}
