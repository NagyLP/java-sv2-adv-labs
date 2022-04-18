package locations;

import java.util.Objects;

public class Location {

    private String name;
    private double latitude;
    private double longitude;

    public Location(String name, double latitude, double longitude) {
        valuesValidator(latitude, longitude);
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public boolean isOnEquator() {
        return latitude == 0;
    }

    public boolean isOnPrimeMeridian() {
        return longitude == 0;
    }

    public double distanceFrom(Location otherlocation) {
        final int EARTH_RAD = 6371;
        double latDistance = Math.toRadians(otherlocation.getLatitude() - latitude);
        double lonDistance = Math.toRadians(otherlocation.getLongitude() - longitude);
        double a = Math.pow(Math.sin(latDistance / 2), 2)
                + Math.cos(Math.toRadians(latitude))
                * Math.cos(Math.toRadians(otherlocation.getLatitude()))
                * Math.pow(Math.sin(lonDistance / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (int) (EARTH_RAD * c * 10) / 10.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.latitude, latitude) == 0 && Double.compare(location.longitude, longitude) == 0 && name.equals(location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, latitude, longitude);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private void valuesValidator(double latitude, double longitude) {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("True Latitude [-90;90]: " + latitude);
        }
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("True Longitude [-180;180]: " + longitude);
        }
    }
}
