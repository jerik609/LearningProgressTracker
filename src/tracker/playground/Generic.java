package tracker.playground;
public class Generic<T> {
    private final Class<T> type;

    Generic(Class<T> type) {
        this.type = type;
    }

    public void sayHello() {
        System.out.println(type.toString());
        System.out.println(this.getClass().toString());
    }

    public static void main(String[] args) {
        Generic<Double> dd = new Generic<>(Double.class);
        dd.sayHello();
    }
}
