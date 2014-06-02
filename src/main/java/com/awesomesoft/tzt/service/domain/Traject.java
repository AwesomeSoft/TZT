package com.awesomesoft.tzt.service.domain;

import javax.persistence.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by student on 5/26/14.
 */

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name = "TRAJECT_TYPE")
@DiscriminatorValue("T")
public class Traject {

    @Id
    @GeneratedValue
    private Long id;

    private double distance;
    private double totalCostPrice;
    private long duration;


    private Date departureDate;
    // de rest laten we leeg want dit ben jij nu niet nodig.


    private Time departureTime;

    public void setDepartureTimeAsTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    @ManyToOne
    private Route route;

    public String getDepartureTime() {
        if(departureTime!=null){
            DateFormat df = new SimpleDateFormat("HH:mm");
            return df.format(departureTime);
        }
        return "";
    }

    public void setDepartureTime(String departureTime) throws RuntimeException{
        try {

            Date date = new SimpleDateFormat("hh:mm", Locale.ENGLISH).parse(departureTime);
            this.departureTime.setTime(date.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public String getDepartureDate() {
        if(departureDate!=null){
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            return df.format(departureDate);
        }
        return "";
    }

    public void setDepartureDate(String departureDate) {
        try {
            this.departureDate = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH).parse(departureDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @OneToOne(cascade = CascadeType.ALL)
    private Location startPoint;

    @OneToOne(cascade = CascadeType.ALL)
    private Location endPoint;

    public Traject(){

    }

    public Location getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Location startPoint) {
        this.startPoint = startPoint;
    }

    public Location getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Location endPoint) {
        this.endPoint = endPoint;
    }

    public Traject(double distance, double totalCostPriceTraject, double fixedPrice, double pricePerKm, long duration, Location startPoint, Location endPoint) {
        this.distance = 1.0;
        this.duration = duration;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public void increaseCostPrice(double price){
        totalCostPrice = totalCostPrice + price;
    }

    public double getTotalCostPrice() {
        return totalCostPrice;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Traject)) return false;

        Traject traject = (Traject) o;

        if (Double.compare(traject.distance, distance) != 0) return false;
        if (duration != traject.duration) return false;
        if (Double.compare(traject.totalCostPrice, totalCostPrice) != 0) return false;
        if (endPoint != null ? !endPoint.equals(traject.endPoint) : traject.endPoint != null) return false;
  //      if (route != null ? !route.equals(traject.route) : traject.route != null) return false;
        if (startPoint != null ? !startPoint.equals(traject.startPoint) : traject.startPoint != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(distance);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(totalCostPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (duration ^ (duration >>> 32));
        result = 31 * result + (startPoint != null ? startPoint.hashCode() : 0);
        result = 31 * result + (endPoint != null ? endPoint.hashCode() : 0);
        result = 31 * result + (route != null ? route.hashCode() : 0);
        return result;
    }
}
