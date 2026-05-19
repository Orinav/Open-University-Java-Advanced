package com.amazori.hw13;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ShoppingCartController
{
    @FXML
    private ListView<String> cartList;

    @FXML
    private ListView<String> productsList;

    private DupCount<String> cart = new DupCount<>();
    private ObservableList<String> observableProducts = FXCollections.observableArrayList();
    private ObservableList<String> observableCart = FXCollections.observableArrayList();

    @FXML
    private void initialize()
    {
        productsList.setItems(observableProducts);
        cartList.setItems(observableCart);
        loadProductsFromFile("products.txt");
    }

    //Helper function
    private void loadProductsFromFile(String fileName)
    {
        try
        {
            Scanner scan = new Scanner(new File(fileName));

            while (scan.hasNext())
            {
                String productName = scan.nextLine();

                if (!productName.isEmpty())
                    observableProducts.add(productName);
            }
            scan.close();
        }
        catch (IOException error)
        {
            System.out.println("Error: " + error);
        }
    }

    //Helper function
    private void updateCart()
    {
        observableCart.clear();
        for (String key: cart.getMap().keySet())
        {
            int count = cart.getMap().get(key);
            observableCart.add(key + " : " + count);
        }
    }

    //Helper function
    private void showDialog(String title, String message)
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    void addToCart(ActionEvent event)
    {
        String selectedProduct = productsList.getSelectionModel().getSelectedItem();

        if (selectedProduct != null)
        {
            cart.add(selectedProduct);
            updateCart();
        }
        else
            showDialog("Error", "Please select a product from the supermarket");
    }

    @FXML
    void removeFromCart(ActionEvent event)
    {
        String selectedProduct = cartList.getSelectionModel().getSelectedItem();

        if (selectedProduct != null)
        {
            String productName = selectedProduct.split(" : ")[0];
            cart.remove(productName);
            updateCart();
        }
        else
            showDialog("Error", "Please select a product from the cart");
    }

    @FXML
    void showCartSummary(ActionEvent event)
    {
        String summary = "Cart summary: \n";

        if (observableCart.isEmpty())
            summary += "The cart is empty";
        else
        {
            for (String product: observableCart)
                summary += product + "\n";
        }

        showDialog("Cart Summary", summary);
    }

    @FXML
    void showMaxDup(ActionEvent event)
    {
        String maxProduct = cart.getMaxDup();

        if (maxProduct != null)
            showDialog("MaxDuplicates", "The most frequent product in the cart is: \n" + maxProduct);
        else
            showDialog("Error", "The cart is empty");
    }
}