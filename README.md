## An example of Sisu, Peaberry, and Guice in an OSGI application

### To build
mvn clean install

### To launch the example in a Felix container (with the old text console installed)
mvn exec:exec -pl main

### To debug
port 8000

### Expected Output
Every 2 seconds the ServiceTest class will print a line of text to the console containing the following:
* Using an OSGI Service import (Scramble) it should print the following two things:
  * A new TimeStamper instance, which is injected as a Provider<TimeStamper>, should print the current time
  * A Singleton ScrambleDelegate, which is a constructor injected, should print some scrambled text
* Using Guice's CustomInjection functionality the next two things:
  * A userId, which is pulled from a list of userIds at instantiation time
  * A scope, which is pulled from a list of scopes at instantiation time

##### Sample Output
OSGI Service: { TimeStamper[2015/07/06 18:29:31] ScrambleDelegate[C@76a22b9c] }  |  Guice CustomInjections: {UserID[cjbooms] Scope[I'm a store...] }



