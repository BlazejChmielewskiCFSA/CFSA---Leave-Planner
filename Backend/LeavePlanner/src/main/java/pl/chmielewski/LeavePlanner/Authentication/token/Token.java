package pl.chmielewski.LeavePlanner.Authentication.token;

import jakarta.persistence.*;
import pl.chmielewski.LeavePlanner.Authentication.user.User;

@Entity
@Table(name = "tokens", schema = "leave")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tk_id")
    private Long id;

    @Column(name = "tk_token")
    private String token;

    @ManyToOne
    @JoinColumn(name = "tk_us_id")
    private User user;

    @Column(name = "tk_revoked")
    private boolean revoked;

    @Column(name = "tk_expired")
    private boolean expired;

    public Token() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", user=" + user +
                ", revoked=" + revoked +
                ", expired=" + expired +
                '}';
    }
}
