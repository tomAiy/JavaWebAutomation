package stepsdefinitions;

import io.cucumber.java.en.Then;
import java.io.IOException;
import java.util.List;
import io.cucumber.java.en.And;
import stepsdefinitions.general.BaseDefinition;
import utilities.CsvReader;

public class HomePage extends BaseDefinition {
    private static final String csvFilePath = "src/test/resources/products.csv";
    @And("I am on the {string} page")
    public void iAmOnTheProductsPage(String title) {
        WebApp.Home.Header.assertSubTitleEquals(title);
    }

    @And("I select the {string} product")
    public void iSelectTheProduct(String product) {
        WebApp.Home.selectProduct(product);
    }

    @Then("I check that the price of product is price")
    public void iCheckThePricesOfProductIsPrice()
    {
        try {
            List<String[]> csvData = CsvReader.readCsv(csvFilePath);
            for (String[] fields : csvData) {
                try {
                    String productName = fields[0];
                    String expectedPrice = fields[1];

                    WebApp.Home.assertProductAndPriceEquals(productName, expectedPrice);
                } catch (Exception ex) {
                    System.out.println("An error occurred while processing a line: " + ex.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }
    }
}
