package com.neueda.framework

import com.neueda.framework.feature.FeatureFactory
import com.neueda.framework.feature.MindMapParser

import static com.neueda.framework.feature.FeatureFactory.getFeaturePath
import static groovy.io.FileType.FILES

class App {

    static final mindMaps = { new File('mindMaps') }

    public static void main(String... args) {
        cleanFeatureFolder()

        createFeatures()
    }

    private static createFeatures() {
        new FeatureFactory(features()).create()
    }

    private static features() {
        def features = []
        mindMaps().eachFile {
            features << MindMapParser.parse(it.text)
        }
        features
    }

    private static cleanFeatureFolder() {
        new File(featurePath).eachFileRecurse(FILES) { it.delete() }
    }

}
