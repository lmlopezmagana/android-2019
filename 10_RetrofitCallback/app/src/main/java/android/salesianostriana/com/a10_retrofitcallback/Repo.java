package android.salesianostriana.com.a10_retrofitcallback;

public class Repo {

    private String name;

    public Repo() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
