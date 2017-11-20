package com.cloudaware.cloudmine.amazon.glue;

import com.amazonaws.services.glue.model.Classifier;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ClassifiersResponse extends AmazonResponse {

    private List<Classifier> classifiers;

    public List<Classifier> getClassifiers() {
        return classifiers;
    }

    public void setClassifiers(final List<Classifier> classifiers) {
        this.classifiers = classifiers;
    }
}
