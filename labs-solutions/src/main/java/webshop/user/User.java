package webshop.user;

import webshop.product.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class User {
    private int ID;
    private String name;
    private String emailAddress;
    private long password;
    private boolean loggedIn;
    private Map<Product, Integer> shoppingCart = new LinkedHashMap<>();

    public Map<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Map<Product, Integer> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void copyUser(User user) {
        this.ID = user.ID;
        this.name = user.name;
        this.emailAddress = user.emailAddress;
        this.password = user.password;
        this.loggedIn = user.loggedIn;
    }

    public User(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password.hashCode();
    }

    public User(int ID, String name, String emailAddress, long password) {
        this.ID = ID;
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public User(String name, String emailAddress, long password) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public boolean login(User user) {
        if (user.emailAddress.equals(this.emailAddress) && user.password == this.password) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", shoppingCart=" + shoppingCart +
                '}';
    }

    public void addToCart(Product product) {
        if (shoppingCart.containsKey(product)) {
            shoppingCart.put(product, shoppingCart.get(product) + 1);
        } else
            shoppingCart.put(product, 1);
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public long getPassword() {
        return password;
    }

    public void setPassword(long password) {
        this.password = password;
    }

}
