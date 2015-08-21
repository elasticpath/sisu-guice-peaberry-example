## An example of Sisu, Peaberry, and Guice in an OSGI application
This is an example project that uses the following technolgies to create a dymanic OSGI application:
* [Sisu](https://www.eclipse.org/sisu/) - to auto bootstrap the dependency injection using Guice in each module based on the JSR-330 @Named and @Inject annotations present
* [Peaberry](https://github.com/ops4j/peaberry) - to manage the exporting and importing of services to the OSGI registry
* [Peaberry-Code-Generator](https://github.com/elasticpath/peaberry-code-generator) - To auto-generate the peaberry classes from annotations at compile time
* [Guice](https://github.com/google/guice) - To define custom injection type listeners
* [Maven Remote Resources Plugin](http://maven.apache.org/plugins/maven-remote-resources-plugin/) - To centrally configure the custom Guice Type
 Listeners once
* [bnd Maven Plugin](http://njbartlett.name/2015/03/27/announcing-bnd-maven-plugin.html) - To build the OSGI bundles and generate the manifest
files, without having to modify the packaging type
* [OSGI Release 6 Metatype Annotations](https://osgi.org/download/osgi.cmpn-6.0.0-pfd.pdf) - To auto-generate metatype XML for our
ManagedService's ConfigAdmin entry

### To build
mvn clean install

### To launch the example in a Felix container
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



