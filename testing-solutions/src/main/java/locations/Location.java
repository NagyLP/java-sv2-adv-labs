package locations;

public class Location {

    private String name;
    private double latitude;
    private double longitude;

    public Location(String name, double latitude, double longitude) {
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
}
