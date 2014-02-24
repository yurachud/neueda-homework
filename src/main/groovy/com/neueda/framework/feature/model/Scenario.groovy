package com.neueda.framework.feature.model

class Scenario {
    def scenarioDefinition = [:]

    Scenario(testDescriptionNode, requestDescriptionNode) {
        scenarioDefinition << ["scenarioTitle": testDescriptionNode.@TEXT]
        extractVariables(testDescriptionNode)
        extractRequestParameters(requestDescriptionNode)
    }

    private extractRequestParameters(requestDescriptionNode) {
        requestParameters(requestDescriptionNode).each {
            addTo(scenarioDefinition, it)
        }
    }

    private extractVariables(testDescriptionNode) {
        variables(testDescriptionNode).each {
            addTo(scenarioDefinition, it)
        }
    }

    private static addTo(definition, node) {
        String variable = node.@TEXT
        def split = variable.split(':')
        definition.put(split[0].trim(), split[1].trim())
    }

    private static requestParameters(requestDescriptionNode) {
        requestDescriptionNode.node
    }

    private static variables(testDescriptionNode) {
        testDescriptionNode.node
    }
}
