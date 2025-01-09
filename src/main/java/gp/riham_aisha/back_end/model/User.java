package gp.riham_aisha.back_end.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import gp.riham_aisha.back_end.dto.RegistrationRequest;
import gp.riham_aisha.back_end.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@ToString
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_phone_number", columnNames = "phoneNumber"),
                @UniqueConstraint(name = "unique_username", columnNames = "username"),
                @UniqueConstraint(name = "unique_email", columnNames = "email")
        }
)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "\\w+", message = "invalid username")
    @NotEmpty(message = "empty username")
    @Size(min = 4, max = 20)
    private String username;

    @Pattern(regexp = "\\p{L}+", message = "invalid first name")
    @NotEmpty(message = "empty first name")
    private String firstName;

    @Pattern(regexp = "\\p{L}+", message = "invalid last name")
    @NotEmpty(message = "empty last name")
    private String lastName;

    @NotEmpty(message = "empty email")
    @Email
    private String email;

    @NotEmpty(message = "empty password")
    @Size(min = 8, max = 1024)
    private String password;

    @NotEmpty(message = "empty phone number")
    @Pattern(regexp = "^\\d{10}$", message = "invalid phone number")
    private String phoneNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    private String userImageUrl;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime signUpDate;

    private int numberOfStores = 0;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @JsonIgnore
    private List<CartItem> cart = new ArrayList<>();

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "wishlist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> wishlist = new ArrayList<>();

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //----------------- to make password, email deserializable but not serializable

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonIgnore
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public void setEmail(String email) {
        this.email = email;
    }

    public User(RegistrationRequest request) {
        this.username = request.username();
        this.firstName = request.firstName();
        this.lastName = request.lastName();
        this.email = request.email();
        this.password = request.password();
        this.phoneNumber = request.phoneNumber();
    }

    //----------------- user's store management
    public void addStore() {
        numberOfStores++;
    }

    public void removeStore() {
        if (--numberOfStores == 0)
            removeRole(Role.STORE_MANAGER);
    }

    //----------------- user's roles management
    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public Boolean hasRole(Role role) {
        return roles.contains(role);
    }

    //----------------- user's wishlist management
    public Boolean isProductInWishlist(Long productId) {
        return wishlist.stream().anyMatch(product -> product.getId().equals(productId));
    }

    public void addProductToWishlist(Product product) {
        if (Boolean.TRUE.equals(isProductInWishlist(product.getId())))
            return;
        wishlist.add(product);
    }

    public void removeProductFromWishlist(Long productId) {
        wishlist.removeIf(product -> product.getId().equals(productId));
    }

    public void clearWishlist() {
        wishlist.clear();
    }
}
