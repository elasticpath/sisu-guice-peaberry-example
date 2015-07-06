
# to build example
mvn clean install

# to launch example on Felix with the old text console
java -jar main/target/main.jar
or
mvn exec:exec -pl main

# you should see scrambled text appear every 2sec

