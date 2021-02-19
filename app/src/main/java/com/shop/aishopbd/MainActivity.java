package com.shop.aishopbd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,phone,email,address,upazila,district,paymentmethod,price,discount,deliverycharge;
    TextView discountedprice,totalpayableamount;
    Button send,copy;
    int priced=0,discountd=0,discountedpriced=0,mainpriced=0,deliverycharged=0,totalpayableamountd=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        upazila = findViewById(R.id.upazila);
        district = findViewById(R.id.district);
        paymentmethod = findViewById(R.id.paymentmethod);
        price = findViewById(R.id.price);
        discount = findViewById(R.id.discount);
        discountedprice = findViewById(R.id.discountedprice);
        deliverycharge = findViewById(R.id.deliverycharge);
        totalpayableamount = findViewById(R.id.totalpayableamount);

        send = findViewById(R.id.send);
        copy = findViewById(R.id.copy);


        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    String prices = s.toString();
                    if (prices.equals(""))
                    {
                        priced = 0;
                    }
                    else
                    {
                        priced = Integer.parseInt(prices);
                    }

                    String deliverycharges = deliverycharge.getText().toString();
                    if (deliverycharges.equals(""))
                    {
                        deliverycharged = 0;
                    }
                    else
                    {
                        deliverycharged = Integer.parseInt(deliverycharges);
                    }


                    String discounts = discount.getText().toString();
                    if (discounts.equals(""))
                    {
                        discountd=0;
                    }
                    else
                    {
                        discountd = Integer.parseInt(discounts);
                    }

                    discountedpriced = priced - (priced*discountd/100);

                    discountedprice.setText(String.valueOf(discountedpriced));

                    totalpayableamountd = discountedpriced+deliverycharged;

                    totalpayableamount.setText(String.valueOf(totalpayableamountd));
                }catch (Exception ignored){};

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        discount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                try {
                    String discounts = s.toString();
                    if (discounts.equals(""))
                    {
                        discountd=0;
                    }
                    else
                    {
                        discountd = Integer.parseInt(discounts);
                    }

                    String prices = price.getText().toString();
                    if (prices.equals(""))
                    {
                        priced = 0;
                    }
                    else
                    {
                        priced = Integer.parseInt(prices);
                    }

                    String deliverycharges = deliverycharge.getText().toString();
                    if (deliverycharges.equals(""))
                    {
                        deliverycharged = 0;
                    }
                    else
                    {
                        deliverycharged = Integer.parseInt(deliverycharges);
                    }


                    discountedpriced = priced - (priced*discountd/100);

                    discountedprice.setText(String.valueOf(discountedpriced));

                    totalpayableamountd = discountedpriced+deliverycharged;

                    totalpayableamount.setText(String.valueOf(totalpayableamountd));
                }catch (Exception ignored){};

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        deliverycharge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    String deliverycharges = s.toString();
                    if (deliverycharges.equals(""))
                    {
                        deliverycharged = 0;
                    }
                    else
                    {
                        deliverycharged = Integer.parseInt(deliverycharges);
                    }

                    String discountedprices = discountedprice.getText().toString();
                    if (discountedprices.equals(""))
                    {
                        discountedpriced = 0;
                    }
                    else
                    {
                        discountedpriced = Integer.parseInt(discountedprice.getText().toString());
                    }

                    totalpayableamountd = discountedpriced + deliverycharged;
                    totalpayableamount.setText(String.valueOf(totalpayableamountd));
                }catch (Exception ignored){};
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                "AiShopBD\nOrder information\n\n"+
                "Name: "+name.getText()+"\n"+
                        "Phone no: "+phone.getText()+"\n"+
                        "Email: "+email.getText()+"\n"+
                        "Address: "+address.getText()+"\n"+
                        "Upazila/Thana: "+upazila.getText()+"\n"+
                        "District: "+district.getText()+"\n\n"+
                        "Payment method: "+paymentmethod.getText()+"\n"+
                        "Price: "+price.getText()+"\n"+
                        "Discount: "+discount.getText()+"\n"+
                        "Discounted price: "+discountedprice.getText()+"\n"+
                        "Delivery charge: "+deliverycharge.getText()+"\n"+
                        "Total payable amount: "+totalpayableamount.getText()+
                        "\n\nThanks for buying from us."


                );
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("CopiedText",
                        "AiShopBD\nOrder information\n\n"+
                        "Name: "+name.getText()+"\n"+
                                "Phone no: "+phone.getText()+"\n"+
                                "Email: "+email.getText()+"\n"+
                                "Address: "+address.getText()+"\n"+
                                "Upazila/Thana: "+upazila.getText()+"\n"+
                                "District: "+district.getText()+"\n\n"+
                                "Payment method: "+paymentmethod.getText()+"\n"+
                                "Price: "+price.getText()+"\n"+
                                "Discount: "+discount.getText()+"\n"+
                                "Discounted price: "+discountedprice.getText()+"\n"+
                                "Delivery charge: "+deliverycharge.getText()+"\n"+
                                "Total payable amount: "+totalpayableamount.getText()+
                                "\n\nThanks for buying from us."
                        );
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getApplicationContext(),"Order info copied",Toast.LENGTH_SHORT).show();
            }
        });




    }
}