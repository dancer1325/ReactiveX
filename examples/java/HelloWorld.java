import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;

public class HelloWorld {
    public static void hello(String... names) {
        Observable.fromArray(names).subscribe(name -> new Action() {

            @Override
            public void run() throws Throwable {
                System.out.println("Hello " + name + "!");
            }

        });
    }

    public static void main(String[] args) {
        hello("Ben", "George");
    }
}

