package biciklisharing;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor

public class BikeRental {

    private String userId;
    private String lastUserID;
    private LocalDateTime deliveryTime;
    private double distance;

    public BikeRental(String id, String lastUserID, LocalDateTime deliveryTime, double distance) {
        this.userId = id;
        this.lastUserID = lastUserID;
        this.deliveryTime = deliveryTime;
        this.distance = distance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastUserID() {
        return lastUserID;
    }

    public void setLastUserID(String lastUserID) {
        this.lastUserID = lastUserID;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
