package application;

import application.pages.CheckoutPage;
import application.pages.HomePage;
import application.pages.LoginPage;
import application.pages.ProductPage;

public class WebApp {
    public HomePage Home;
    public LoginPage Login;
    public ProductPage Product;
    public CheckoutPage Checkout;

    public WebApp() {
       Home = new HomePage();
       Login = new LoginPage();
       Product = new ProductPage();
       Checkout = new CheckoutPage();
    }

}
