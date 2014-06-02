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
public class TZTOrder {

    @Id
    @GeneratedValue
    private Long id;// een entiteit heeft een ID nodig met deze anotatiets @id en @generated value

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        customer._own(this);
        this.customer = customer;
    }

    @ManyToOne
    private Person customer;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Package aPackage;

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    @OneToOne(cascade = CascadeType.ALL)
    private Person receiver;
    
    public long getOrderNumber() {
        return orderNumber;
    }

    // Added by Erwin
    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    private long orderNumber;

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

    @OneToOne
    private Route route;

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }

    public void addRoute(Route route){
        this.route = route;
        route.addOrder(this);
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

    public void setSendDate(String sendDate) {
        try {
            this.sendDate = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH).parse(sendDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);

        }
    }

    public int getStatus(){
        return status;
    }

    public Long getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TZTOrder)) return false;

        TZTOrder tztOrder = (TZTOrder) o;

        if (orderNumber != tztOrder.orderNumber) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (orderNumber ^ (orderNumber >>> 32));
    }
}
