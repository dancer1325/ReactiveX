* ReactiveX
  * := library for
    * programs
      * composing asynchronous
      * event-based
    * -- via -- observable sequences
      * Reason:  🧠 stream of async events -- are treated -- simple  🧠
  * extends observer pattern
    * allows
      * abstracting away
        * low-level threading
        * synchronization
        * thread-safety
        * concurrent data structures
        * non-blocking I/O
  * != functional reactive programming
    * Reason: 🧠operate | values / change continuously over time != operate | discrete values / emitted over time 🧠
    * Check [here](https://github.com/conal/talk-2015-essence-and-origins-of-frp)
    * may be
      * functional OR
      * reactive
* Observables
  * 👁️ == ideal way to access async sequences of multiple items 👁️
    * ⭐Check the table sync/async - 1/>1 items ⭐
  * vs "java.util.concurrent.Future"
    * if nested async execution -> complex -- [Example](https://gist.github.com/benjchristensen/4671081) --
    * if compose conditional async execution -> complex -- [Example](https://gist.github.com/benjchristensen/4671081#file-futuresb-java-L163)
  * flexible
    * == support the emission of 
      * single scalar values &
      * sequence of values &
      * infinite streams
    * == Iterable | async
      * ⭐Check the table of events⭐
  * less opinionated
    * == NOT focused on particular
      * concurrency or
      * asynchronicity
  * vs Iterable
    * Observable is "push" & Iterable is "pull"
      * Reason: 🧠 🧠
        * "pull" because consumer -- pull the values from the -- producer & thread blocked / values arrive
        * "push" because producer -- push the values to the -- consumer | as soon are available
* Operators
  * allows efficient
    * execution
    * composition
* TODO: