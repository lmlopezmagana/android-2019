package android.salesianostriana.com.a07_listview.model;

import java.util.Objects;

public class Owner {

    private String avatar_url;

    public Owner() { }

    public Owner(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Owner owner = (Owner) o;

        return avatar_url != null ? avatar_url.equals(owner.avatar_url) : owner.avatar_url == null;
    }

    @Override
    public int hashCode() {
        return avatar_url != null ? avatar_url.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "avatar_url='" + avatar_url + '\'' +
                '}';
    }
}
