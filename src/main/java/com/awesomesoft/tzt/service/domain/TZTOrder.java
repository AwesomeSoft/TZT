package com.awesomesoft.tzt.service.domain;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Erwin on 21-5-2014.
 */
@Entity
@SequenceGenerator(name="seq", initialValue=1000, allocationSize=100)
public class TZTOrder {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private Long id;// een entiteit heeft een ID nodig met deze anotatiets @id en @generated value

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
        customer._own(this);
    }

    @ManyToOne
    private Person customer;


    @OneToOne
    private Package aPackage;

    public long getOrderNumber() {
        return orderNumber;
    }

    private long orderNumber;

    @OneToOne
    private Person receiver;

    private double totalCostprice;

    private double custumerPrice;

    private int status;

    private Date creationDate;

    private Date sendDate;

    public TZTOrder(){
         if(this.aPackage == null){
             this.aPackage = new Package();
         }
        this.creationDate = new Date();
        this.orderNumber = generateOrderNumber();
    }

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }

    public long generateOrderNumber(){
        long seed = new Date().getTime();
        Random rand = new Random(seed);
        String randomNumber = ""+rand.nextInt(9999);
        DateFormat df = new SimpleDateFormat("ddmmyyyy");
        String date = df.format(creationDate);
        return Long.parseLong(date+randomNumber);
    }

    public String getSendDate() {
        if(sendDate!=null){
            DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
            return df.format(sendDate);
        }
        return "";
    }

    public void setSendDate(String dateofBirth) {
        try {
            this.sendDate = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH).parse(dateofBirth);
        } catch (ParseException e) {
            throw new RuntimeException(e);

        }
    }

    public Long getId() {
        return id;
    }
}
