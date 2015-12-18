package module9;

@FunctionalInterface
public interface Callback<T> {
	void apply(T type);
}
