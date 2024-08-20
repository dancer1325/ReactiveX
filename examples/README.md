* Check 'documentation.markdown' 's Example1

## How to run locally?
* via
  * Maven
    * `mvn -f pom.xml dependency:copy-dependencies`
  * Binary
    * TODO:
* |
  * Java
    * `javac -cp target/dependency/rxjava-3.1.9.jar java/HelloWorld.java`
    * `java -cp target/dependency/rxjava-3.1.9.jar java/HelloWorld `
      * Problems:
        * Problem1: "Error: Could not find or load main class java.HelloWorld"
          * Attempts:
            * Attempt1: `java -cp :target/dependency/rxjava-3.1.9.jar java/HelloWorld`
            * Attempt2: `java -cp .:target/dependency/rxjava-3.1.9.jar java/HelloWorl`
          * Solution: TODO:
  * Clojure
    * TODO:
  * Groovy
    * TODO: