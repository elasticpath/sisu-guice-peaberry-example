## An example of Sisu, Peaberry, and Guice in an OSGI application
This is an example project that uses the following technolgies to create a dymanic OSGI application:
* Sisu - to auto bootstrap the dependency injection using Guice in each module based on the JSR-330 @Named and @Inject annotations present
* Peaberry - to manage the exporting and importing of services to the OSGI registry
* Peaberry-Code-Generator - To auto-generate the peaberry classes from annotations at compile time
* Guice - To define custom injection type listeners
* maven-remote-resources-plugin - To centrally configure the custom Guice Type Listeners
* bnd-maven-plugin - To build the OSGI bundles and generate the manifest files
* OSGI Release 6 Metatype Annotations - To auto-generate metatype XML for our ManagedService's configuration admin entry

### To build
mvn clean install

### To launch the example in a Felix container (with the old text console installed)
mvn exec:exec -pl main

### To debug
port 8000

### Expected Output
Every 5 seconds a Service Consumer will generate some console output from dynamically injected services.
* Two separate WordGenerator services should appear that generate random words
  * ThreeWordGenerator is a ManagedService. Its output can be changes using the Config Admin in the [Felix Webconsole]
  (http://localhost:8080/system/console/configMgr)
  * TwoWordGenerator is a simple service that generates two words.
* A UserGenerator service should be injected that generates random users. It makes use of Guice Custom Injections to do so dynamically.

##### Sample Output
````
Test Services
    Word Generators:
        [Two, Words]
        [Hey, Ho, Let's Go] generated at time: 2015/08/21 16:30:04
    User Generator: Tadgh likes OSGI!
````



