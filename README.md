Spring Boot Experiments
-----------------------

The first experiment was to set up a unit test so that it would start a
`MiniAccumuloCluster`and then inject the configuration of that cluster into a
configuration that was otherwise initialized using `application.yml`

The components are as follows:

src/main/java:
* drew.app.AppMain
* drew.app.conf.AppConfiguration

src/main/resources:
* application.yml

src/test/java
* drew.app.AppTest
* drew.app.util.MiniAccumuloBean

`AppTest` uses the `@ContextConfiguration` to set up test-specific beans via
the inner class `AppTest.TestConfiguration`. This defines the
`MiniAccumuloBean` bean and receives a reference to that bean and the
`AppConfiguration` bean in its `injectMiniAccumuloBeanConfig()` method which
does the work of injecting the configuration information from the
`MiniAccumuloCluster` created by `MiniAccumuloBean` into the `AppConfiguration`
bean.

`AppMain` provides a single REST endpoint that returns a message indicating the
configured Accumulo instancen name. `AppTest`'s test validates that the
instance name is, as expected, that of the `MiniAccumuloCluster` and that the
configuration contains information received from `application.yml`

The test can be run via `mvn clean package`, log4j2 is configured to emit
logging from Spring Boot at `INFO` level.
