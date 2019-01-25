package android.salesianostriana.com.a12_login.retrofit.services;

public class LoginReponse {

    private String token;

    public LoginReponse() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginReponse that = (LoginReponse) o;

        return token != null ? token.equals(that.token) : that.token == null;
    }

    @Override
    public int hashCode() {
        return token != null ? token.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "LoginReponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
