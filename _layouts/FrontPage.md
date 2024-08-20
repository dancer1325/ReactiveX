* := API for asynchronous programming via Observable streams
  * allows
    * creating easily
      * event streams
      * data streams
    * composing / transforming streams
      * via query-like operators
    * subscribing to any observable stream
      * — to perform — side effects
* === Observer pattern + Iterator pattern + Functional programming
* has inspired other
  * APIs
  * Frameworks
    * Example: [ReaqTor](https://reaqtive.net/)
  * Program Languages
* Uses
  * FE
    * manipulate UI events & API responses
      * [Web] RxJs
      * [Mobile] Rx.NET & RxJava
  * Cross-platform
    * Available for many [different languages](https://reactivex.io/languages.html)
  * BE
    * concurrency
    * implementation independence 
* Main advantages
  * functional
    * intricate stateful programs — are replaced by ⟶ input / output functions over observable streams
  * less is more
    * elaborate challenge — thanks to ReactiveX's operators is addressed by ⟶ few lines of code
  * async error handling
    * ⚠️ `try catch` is powerless for async error handling ⚠️
      * ⟶ is replaced by — other mechanisms
  * concurrency made easy
    * observables & schedulers allows abstract away low-level
      * threading issues
      * synchronization issues
      * concurrency issues