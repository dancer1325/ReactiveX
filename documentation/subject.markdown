---
layout: documentation
title: ReactiveX - Subject
id: subject
---

<h1>Subject</h1>
<p>
 A Subject is a sort of bridge or proxy that is available in some implementations of ReactiveX that acts both as
 an observer and as an Observable. Because it is an observer, it can subscribe to one or more Observables, and
 because it is an Observable, it can pass through the items it observes by reemitting them, and it can also emit
 new items.
</p><p>
 Because a Subject subscribes to an Observable, it will trigger that Observable to begin emitting items (if that
 Observable is “cold” — that is, if it waits for a subscription before it begins to emit items). This can have
 the effect of making the resulting Subject a “hot” Observable variant of the original “cold” Observable.
</p>
<h4>See Also</h4>
<ul>
 <li><a href="http://davesexton.com/blog/post/To-Use-Subject-Or-Not-To-Use-Subject.aspx">To Use or Not to Use Subject</a> from <cite>Dave Sexton&#8217;s blog</cite></li>
 <li><a href="http://www.introtorx.com/Content/v1.0.10621.0/02_KeyTypes.html#Subject"><cite>Introduction to Rx</cite>: Subject</a></li>
 <li><a href="http://rxwiki.wikidot.com/101samples#toc44"><cite>101 Rx Samples</cite>: ISubject&lt;T&gt; and ISubject&lt;T1,T2&gt;</a></li>
 <li><a href="http://akarnokd.blogspot.hu/2015/06/subjects-part-1.html">Advanced RxJava: Subject</a> by Dávid Karnok</li>
 <li><a href="http://xgrommx.github.io/rx-book/content/getting_started_with_rxjs/subjects.html">Using Subjects</a> by Dennis Stoyanov</li>
</ul>
<h2>Concurrent usage</h2>
<p>
  In most ReactiveX implementations, especially those that can run in a multi-threaded environment, subjects on their observer side are <em>not</em> considered thread safe.
  However, the observable side, i.e., <code>Subscribe()</code> is always thread safe.
</p>
<p>
  This means that calling <code>OnNext</code>, <code>OnError</code> or <code>OnCompleted</code> from multiple threads can result in an undefined state.
</p>
<p>
  Most ReactiveX implementations therefore offer a special operator that makes the observer side thread safe as well. Look for the <code>ToSerialized</code> operator.
</p>
<h2>Varieties of Subject</h2>
<p>
 There are four varieties of <code>Subject</code> that are designed for particular use cases. Not all of these
 are available in all implementations, and some implementations use other naming conventions (for example, in
 RxScala, what is called a “PublishSubject” here is known simply as a “Subject”):
</p>
<h3>AsyncSubject</h3>
<figure>
 <img src="{{ site.url }}/documentation/operators/images/S.AsyncSubject.png" style="width:100%;" />
 <figcaption><p>
  An <code>AsyncSubject</code> emits the last value (and only the last value) emitted by the source Observable,
  and only after that source Observable completes. (If the source Observable does not emit any values, the
  <code>AsyncSubject</code> also completes without emitting any values.)
 </p></figcaption>
</figure>
<figure>
 <img src="{{ site.url }}/documentation/operators/images/S.AsyncSubject.e.png" style="width:100%;" />
 <figcaption><p>
  It will also emit this same final value to any subsequent observers. However, if the source Observable
  terminates with an error, the <code>AsyncSubject</code> will not emit any items, but will simply pass along
  the error notification from the source Observable.
 </p></figcaption>
</figure>
<h4>See Also</h4>
<ul>
 <li><a href="http://www.introtorx.com/Content/v1.0.10621.0/02_KeyTypes.html#AsyncSubject"><cite>Introduction to Rx</cite>: AsyncSubject</a></li>
</ul>
<h3>BehaviorSubject</h3>
<figure>
 <img src="{{ site.url }}/documentation/operators/images/S.BehaviorSubject.png" style="width:100%;" />
 <figcaption><p>
  When an observer subscribes to a <code>BehaviorSubject</code>, it begins by emitting the item most recently
  emitted by the source Observable (or a seed/default value if none has yet been emitted) and then continues to
  emit any other items emitted later by the source Observable(s).
 </p></figcaption>
</figure>
<figure>
 <img src="{{ site.url }}/documentation/operators/images/S.BehaviorSubject.e.png" style="width:100%;" />
 <figcaption><p>
  However, if the source Observable terminates with an error, the <code>BehaviorSubject</code> will not emit any
  items to subsequent observers, but will simply pass along the error notification from the source Observable.
 </p></figcaption>
</figure>
<h4>See Also</h4>
<ul>
 <li><a href="http://www.introtorx.com/Content/v1.0.10621.0/02_KeyTypes.html#BehaviorSubject"><cite>Introduction to Rx</cite>: BehaviorSubject</a></li>
</ul>
<h3>PublishSubject</h3>
<figure>
 <img src="{{ site.url }}/documentation/operators/images/S.PublishSubject.png" style="width:100%;" />
 <figcaption><p>
  <code>PublishSubject</code> emits to an observer only those items that are emitted by the source Observable(s)
  subsequent to the time of the subscription.
 </p><p>
  Note that a <code>PublishSubject</code> may begin emitting items immediately upon creation (unless you have
  taken steps to prevent this), and so there is a risk that one or more items may be lost between the time the
  Subject is created and the observer subscribes to it. If you need to guarantee delivery of all items from the
  source Observable, you&#8217;ll need either to form that Observable with
  <a href="operators/create.html"><code>Create</code></a> so that you can manually reintroduce “cold” Observable
  behavior (checking to see that all observers have subscribed before beginning to emit items), or switch to
  using a <code>ReplaySubject</code> instead.
 </p></figcaption>
</figure>
<figure>
 <img src="{{ site.url }}/documentation/operators/images/S.PublishSubject.e.png" style="width:100%;" />
 <figcaption><p>
  If the source Observable terminates with an error, the <code>PublishSubject</code> will not emit any items to
  subsequent observers, but will simply pass along the error notification from the source Observable.
 </p></figcaption>
</figure>
<h3>ReplaySubject</h3>
<figure>
 <img src="{{ site.url }}/documentation/operators/images/S.ReplaySubject.png" style="width:100%;" />
 <figcaption><p>
  <code>ReplaySubject</code> emits to any observer all of the items that were emitted by the source
  Observable(s), regardless of when the observer subscribes.
 </p><p>
  There are also versions of <code>ReplaySubject</code> that will throw away old items once the replay buffer
  threatens to grow beyond a certain size, or when a specified timespan has passed since the items were
  originally emitted.
 </p><p>
  If you use a <code>ReplaySubject</code> as an observer, take care not to call its <code>onNext</code> method
  (or its other <code>on</code> methods) from multiple threads, as this could lead to coincident
  (non-sequential) calls, which violates <a href="contract.html">the Observable contract</a> and creates an
  ambiguity in the resulting Subject as to which item or notification should be replayed first.
 </p></figcaption>
</figure>
<h4>See Also</h4>
<ul>
 <li><a href="http://www.introtorx.com/Content/v1.0.10621.0/02_KeyTypes.html#ReplaySubject"><cite>Introduction to Rx</cite>: ReplaySubject</a></li>
</ul>
<h2>Other subject types</h2>
<p>
  In certain ReactiveX flavors and versions, such as <em>RxJava 3.x</em>, there are a couple of more subject types available, fulfilling some extra common roles.
</p>
<h3>UnicastSubject</h3>
<figure>
 <img src="{{ site.url }}/documentation/operators/images/UnicastSubject.png" style="width:100%;" />
 <figcaption><p>
   A Subject that queues up events until a single Observer subscribes to it, replays those events to it until the Observer catches up 
   and then switches to relaying events live to this single Observer until this UnicastSubject terminates or the Observer disposes.
 </p></figcaption>
</figure>
<h4>See Also</h4>
<ul>
 <li><a href="https://reactivex.io/RxJava/3.x/javadoc/io/reactivex/rxjava3/subjects/UnicastSubject.html"><cite>RxJava 3.x</cite>: UnicastSubject</a></li>
</ul>
<h3>SingleSubject</h3>
<figure>
 <img src="{{ site.url }}/documentation/operators/images/SingleSubject.png" style="width:100%;" />
 <figcaption><p>
   Represents a hot Single-like source and consumer of events similar to Subjects. Since a Single can only ever emit an item or error, a SingleSubject is
   implicitly a replay-like subject.
 </p></figcaption>
</figure>
<h4>See Also</h4>
<ul>
 <li><a href="https://reactivex.io/RxJava/3.x/javadoc/io/reactivex/rxjava3/subjects/SingleSubject.html"><cite>RxJava 3.x</cite>: SingleSubject</a></li>
</ul>
<h3>MaybeSubject</h3>
<figure>
 <img src="{{ site.url }}/documentation/operators/images/MaybeSubject.png" style="width:100%;" />
 <figcaption><p>
   Represents a hot Maybe-like source and consumer of events similar to Subjects. Since a Maybe can only ever emit an item, an error or become completed, a MaybeSubject is
   implicitly a replay-like subject.
 </p></figcaption>
</figure>
<h4>See Also</h4>
<ul>
 <li><a href="https://reactivex.io/RxJava/3.x/javadoc/io/reactivex/rxjava3/subjects/MaybeSubject.html"><cite>RxJava 3.x</cite>: MaybeSubject</a></li>
</ul>
<h3>CompletableSubject</h3>
<figure>
 <img src="{{ site.url }}/documentation/operators/images/CompletableSubject.png" style="width:100%;" />
 <figcaption><p>
   Represents a hot Completable-like source and consumer of events similar to Subjects. Since a Completable can only ever complete or hold an error, a CompletableSubject is
   implicitly a replay-like subject.
 </p></figcaption>
</figure>
<h4>See Also</h4>
<ul>
 <li><a href="https://reactivex.io/RxJava/3.x/javadoc/io/reactivex/rxjava3/subjects/CompletableSubject.html"><cite>RxJava 3.x</cite>: CompletableSubject</a></li>
</ul>
<h3>Processors</h3>
<figure>
 <img src="{{ site.url }}/documentation/operators/images/MulticastProcessor.png" style="width:100%;" />
 <figcaption><p>
   <em>RxJava 2.x</em> and <em>RxJava 3.x</em> defines backpressure-aware subjects as <em>Processor</em>s with very similar naming to the other Subjects above.
   These behave pretty much the same with the exception that they will not overflow their subscribers if they don't request more items. In general, these subjects
   don't coordinate between their subscribers and may fail them individually if they can not keep up.
 </p>
   <p>
     A special processor, <code>MulticastProcessor</code> pictured above, does coordinate between its subscribers with respect to backpressure.
   </p>
   <p>
     The <code>SingleSubject</code>, <code>MaybeSubject</code> and <code>CompletableSubject</code> subject types do not have processor variants because these do not need to support backpressure and
     can always hold at most one element.
   </p>
   <p>
     Processors also implement the Reactive Streams <a href='https://github.com/reactive-streams/reactive-streams-jvm#4processor-code'>Processor</a> interface and thus
     they are compatible across the Reactive Streams ecosystem in Java.
   </p>
 </figcaption>
</figure>
<h4>See Also</h4>
<ul>
 <li><a href="https://reactivex.io/RxJava/3.x/javadoc/io/reactivex/rxjava3/processors/AsyncProcessor.html"><cite>RxJava 3.x</cite>: AsyncProcessor</a></li>
 <li><a href="https://reactivex.io/RxJava/3.x/javadoc/io/reactivex/rxjava3/processors/BehaviorProcessor.html"><cite>RxJava 3.x</cite>: BehaviorProcessor</a></li>
 <li><a href="https://reactivex.io/RxJava/3.x/javadoc/io/reactivex/rxjava3/processors/PublishProcessor.html"><cite>RxJava 3.x</cite>: PublishProcessor</a></li>
 <li><a href="https://reactivex.io/RxJava/3.x/javadoc/io/reactivex/rxjava3/processors/ReplayProcessor.html"><cite>RxJava 3.x</cite>: ReplayProcessor</a></li>
 <li><a href="https://reactivex.io/RxJava/3.x/javadoc/io/reactivex/rxjava3/processors/MulticastProcessor.html"><cite>RxJava 3.x</cite>: MulticastProcessor</a></li>
 <li><a href="https://reactivex.io/RxJava/3.x/javadoc/io/reactivex/rxjava3/processors/UnicastProcessor.html"><cite>RxJava 3.x</cite>: UnicastProcessor</a></li>
</ul>

<h2>Language-Specific Information:</h2>

<div class="panel-group operators-by-language" id="accordion" role="tablist" aria-multiselectable="true">

  {% lang_operator RxClojure %}
    <p>
     <span style="color:#f00">TBD</span>
    </p>
  {% endlang_operator %}

  {% lang_operator RxCpp %}
    <p>
     <span style="color:#f00">TBD</span>
    </p>
  {% endlang_operator %}

  {% lang_operator RxGroovy %}
   <p>
    If you have a <code>Subject</code> and you want to pass it along to some other agent without exposing its
    <code>Subscriber</code> interface, you can mask it by calling its <code>asObservable</code> method, which
    will return the Subject as a pure <code>Observable</code>.
   </p>
   <h4>See Also</h4>
   <ul>
    <li>Javadoc: <a href="http://reactivex.io/RxJava/javadoc/rx/subjects/AsyncSubject.html"><code>AsyncSubject</code></a></li>
    <li>Javadoc: <a href="http://reactivex.io/RxJava/javadoc/rx/subjects/BehaviorSubject.html"><code>BehaviorSubject</code></a></li>
    <li>Javadoc: <a href="http://reactivex.io/RxJava/javadoc/rx/subjects/PublishSubject.html"><code>PublishSubject</code></a></li>
    <li>Javadoc: <a href="http://reactivex.io/RxJava/javadoc/rx/subjects/ReplaySubject.html"><code>ReplaySubject</code></a></li>
   </ul>
  {% endlang_operator %}

  {% lang_operator RxJava&nbsp;1․x %}
   <p>
    If you have a <code>Subject</code> and you want to pass it along to some other agent without exposing its
    <code>Subscriber</code> interface, you can mask it by calling its <code>asObservable</code> method, which
    will return the Subject as a pure <code>Observable</code>.
   </p>
   <h4>See Also</h4>
   <ul>
    <li>Javadoc: <a href="http://reactivex.io/RxJava/javadoc/rx/subjects/AsyncSubject.html"><code>AsyncSubject</code></a></li>
    <li>Javadoc: <a href="http://reactivex.io/RxJava/javadoc/rx/subjects/BehaviorSubject.html"><code>BehaviorSubject</code></a></li>
    <li>Javadoc: <a href="http://reactivex.io/RxJava/javadoc/rx/subjects/PublishSubject.html"><code>PublishSubject</code></a></li>
    <li>Javadoc: <a href="http://reactivex.io/RxJava/javadoc/rx/subjects/ReplaySubject.html"><code>ReplaySubject</code></a></li>
   </ul>
  {% endlang_operator %}

  {% lang_operator RxJava&nbsp;3․x %}
   <p>
    If you have a <code>Subject</code> and you want to pass it along to some other agent without exposing its
    <code>Observer</code> interface, you can mask it by calling its <code>hide</code> method, which
    will return the Subject as a pure <code>Observable</code>.
   </p>
   <h4>See Also</h4>
   <ul>
    <li>Javadoc: <a href="https://reactivex.io/RxJava/3.x/javadoc/io/reactivex/rxjava3/subjects/AsyncSubject.html"><code>AsyncSubject</code></a></li>
    <li>Javadoc: <a href="https://reactivex.io/RxJava/3.x/javadoc/io/reactivex/rxjava3/subjects/BehaviorSubject.html"><code>BehaviorSubject</code></a></li>
    <li>Javadoc: <a href="https://reactivex.io/RxJava/3.x/javadoc/io/reactivex/rxjava3/subjects/PublishSubject.html"><code>PublishSubject</code></a></li>
    <li>Javadoc: <a href="https://reactivex.io/RxJava/3.x/javadoc/io/reactivex/rxjava3/subjects/ReplaySubject.html"><code>ReplaySubject</code></a></li>
    <li>Javadoc: <a href="https://reactivex.io/RxJava/3.x/javadoc/io/reactivex/rxjava3/subjects/UnicastSubject.html"><code>UnicastSubject</code></a></li>
    <li>Javadoc: <a href="https://reactivex.io/RxJava/3.x/javadoc/io/reactivex/rxjava3/subjects/CompletableSubject.html"><code>CompletableSubject</code></a></li>
    <li>Javadoc: <a href="https://reactivex.io/RxJava/3.x/javadoc/io/reactivex/rxjava3/subjects/MaybeSubject.html"><code>MaybeSubject</code></a></li>
    <li>Javadoc: <a href="https://reactivex.io/RxJava/3.x/javadoc/io/reactivex/rxjava3/subjects/SingleSubject.html"><code>SingleSubject</code></a></li>
   </ul>
  {% endlang_operator %}
  
  {% lang_operator RxJS %}
    <p>
     <span style="color:#f00">TBD</span>
    </p>
    <h4>See Also</h4>
    <ul>
     <li><a href="http://xgrommx.github.io/rx-book/content/subjects/index.html">Subjects</a> by Denis Stoyanov</li>
    </ul>
  {% endlang_operator %}

  {% lang_operator RxKotlin %}
    <p>
     <span style="color:#f00">TBD</span>
    </p>
  {% endlang_operator %}

  {% lang_operator Rx.NET %}
    <p>
     <span style="color:#f00">TBD</span>
    </p>
    <h4>See Also</h4>
    <ul>
     <li>Reactive Extensions: <a href="http://msdn.microsoft.com/en-us/library/hh229363.aspx"><code>AsyncSubject</code></a></li>
     <li>Reactive Extensions: <a href="http://msdn.microsoft.com/en-us/library/hh211949.aspx"><code>BehaviorSubject</code></a></li>
     <li>Reactive Extensions: <a href="http://msdn.microsoft.com/en-us/library/hh211810.aspx"><code>ReplaySubject</code></a></li>
    </ul>
  {% endlang_operator %}

  {% lang_operator RxPY %}
    <p>
     <span style="color:#f00">TBD</span>
    </p>
  {% endlang_operator %}

  {% lang_operator Rx.rb %}
    <p>
     <span style="color:#f00">TBD</span>
    </p>
  {% endlang_operator %}

  {% lang_operator RxScala %}
    <p>
     <span style="color:#f00">TBD</span>
    </p>
  {% endlang_operator %}

</div>
