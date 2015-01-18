package com.ml.core;

public abstract class Model<T extends Event<T>, X extends EventAnalysisResult> {

	public abstract void runRepositoryAnalysis();

}
