package locations;

public class LocationParser {

    public Location parse(String text) {
        String[] textPart = text.split(",");
        return new Location(
                textPart[0],
                Double.parseDouble(textPart[1]),
                Double.parseDouble(textPart[2]));
    }
}
