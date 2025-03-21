package com.springrestandcloud.actuatorendpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

/**
 *
 * {
 *   "_links": {
 *     "self": {
 *       "href": "http://localhost:8080/actuator",
 *       "templated": false
 *     },
 *     "my-custom-actuator": {
 *       "href": "http://localhost:8080/actuator/my-custom-actuator",
 *       "templated": false
 *     },
 *     "beans": {
 *       "href": "http://localhost:8080/actuator/beans",
 *       "templated": false
 *     },
 *     "caches-cache": {
 *       "href": "http://localhost:8080/actuator/caches/{cache}",
 *       "templated": true
 *     },
 *     "caches": {
 *       "href": "http://localhost:8080/actuator/caches",
 *       "templated": false
 *     },
 *     "health": {
 *       "href": "http://localhost:8080/actuator/health",
 *       "templated": false
 *     },
 *     "health-path": {
 *       "href": "http://localhost:8080/actuator/health/{*path}",
 *       "templated": true
 *     },
 *     "info": {
 *       "href": "http://localhost:8080/actuator/info",
 *       "templated": false
 *     },
 *     "conditions": {
 *       "href": "http://localhost:8080/actuator/conditions",
 *       "templated": false
 *     },
 *     "configprops": {
 *       "href": "http://localhost:8080/actuator/configprops",
 *       "templated": false
 *     },
 *     "configprops-prefix": {
 *       "href": "http://localhost:8080/actuator/configprops/{prefix}",
 *       "templated": true
 *     },
 *     "env": {
 *       "href": "http://localhost:8080/actuator/env",
 *       "templated": false
 *     },
 *     "env-toMatch": {
 *       "href": "http://localhost:8080/actuator/env/{toMatch}",
 *       "templated": true
 *     },
 *     "loggers": {
 *       "href": "http://localhost:8080/actuator/loggers",
 *       "templated": false
 *     },
 *     "loggers-name": {
 *       "href": "http://localhost:8080/actuator/loggers/{name}",
 *       "templated": true
 *     },
 *     "heapdump": {
 *       "href": "http://localhost:8080/actuator/heapdump",
 *       "templated": false
 *     },
 *     "threaddump": {
 *       "href": "http://localhost:8080/actuator/threaddump",
 *       "templated": false
 *     },
 *     "metrics-requiredMetricName": {
 *       "href": "http://localhost:8080/actuator/metrics/{requiredMetricName}",
 *       "templated": true
 *     },
 *     "metrics": {
 *       "href": "http://localhost:8080/actuator/metrics",
 *       "templated": false
 *     },
 *     "sbom": {
 *       "href": "http://localhost:8080/actuator/sbom",
 *       "templated": false
 *     },
 *     "sbom-id": {
 *       "href": "http://localhost:8080/actuator/sbom/{id}",
 *       "templated": true
 *     },
 *     "scheduledtasks": {
 *       "href": "http://localhost:8080/actuator/scheduledtasks",
 *       "templated": false
 *     },
 *     "mappings": {
 *       "href": "http://localhost:8080/actuator/mappings",
 *       "templated": false
 *     }
 *   }
 * }
 *
 */

@Component
@Endpoint(id="my-custom-actuator")
public class CustomActuator {

    @ReadOperation
    public String myCustomendpoint() {
        return "This is my actuator";
    }
}
