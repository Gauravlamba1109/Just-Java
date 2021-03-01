/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**

 This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     This method is called when the + button is clicked.
     */
    int quantity=0;
    public void increament(View view) {
        if(quantity==100){
            Toast.makeText(this,"Enter a quantity less than 100",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity+1;
        display(quantity);
    }

    /**
     This method is called when the - button is clicked.
     */
    public void decreament(View view) {
        if(quantity==1){
            Toast.makeText(this,"Enter a quantity more than 0",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity-1;
        display(quantity);
    }
    /**
     This method displays the given quantity value on the screen. btw the + and - sign
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     This method is called when the order button is clicked.
     */
    public String nameEntered(){
        EditText username=(EditText) findViewById(R.id.edittext_bar);
        String name= username.getText().toString();
        return name;
    }
    public boolean wantcream(){
        CheckBox whippedClicked = (CheckBox) findViewById(R.id.whippedcream_checkbox);
        boolean hasWhippedCream = whippedClicked.isChecked();
        return hasWhippedCream;
    }
    public boolean wantChocolate(){
        CheckBox hasChocolate = (CheckBox) findViewById(R.id.Chocolate_checkbox);
        boolean hasChocolateClicked = hasChocolate.isChecked();
        return hasChocolateClicked;
    }

    //toping price
    public int priceTopping(){
        int priceoftoppings=0;
        if(wantChocolate()){
            priceoftoppings+=2;
        }
        if(wantcream()){
            priceoftoppings+=1;
        }
        return priceoftoppings;
    }


    public int priceOfCoffee(int quantity){
        int priceOfCoffe=5*quantity;
        return priceOfCoffe;
    }
    /**this is submit order it will call for the name and
     * for the toppings and
     * then it should call for toppings
     * and then the two prices should be added */
    public void submitOrder(View view) {
        String name = nameEntered();
        boolean hasWhippedCream = wantcream();
        boolean hasChocolate = wantChocolate();
               //now the price of coffee
        int price=priceOfCoffee(quantity);
        int priceOfTopping=priceTopping();
        int total = price + (priceOfTopping*quantity);

        String priceMethod="Name : "+name +
                            "\n" + "Price of Coffee : $" +price+
                            "\n" + "Has Wipped Cream : "+ hasWhippedCream+
                            "\n" + "Has Chocolate : "+ hasChocolate+
                            "\n" + "Price of Toppings : "+ quantity*priceOfTopping+
                            "\n" + "Total : "+ total;
        displayOrderSummary(priceMethod);
        displaythankyou(name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL,"gauravlamba1109@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT,"My Order");
        intent.putExtra(Intent.EXTRA_TEXT,priceMethod);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    /**
     * this method displays given text on screen
     */
    private void displayOrderSummary(String message){
        TextView priceTextView = (TextView) findViewById(R.id.orderSummary_text_view);
        priceTextView.setText(message);
    }
    private void displaythankyou(String name){
        TextView mess = (TextView) findViewById(R.id.thankyou);
        String namee= "\nThank you "+nameEntered() + ".";
        mess.setText(namee);
    }


}