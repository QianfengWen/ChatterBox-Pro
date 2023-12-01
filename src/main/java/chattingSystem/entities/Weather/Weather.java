package chattingSystem.entities.Weather;


public class Weather implements CommonWeather {

    // Refer to the API documentation for the meaning of these fields.
    private String cityLocation;
    private String lastUpdated;
    private int temperature;
    private String condition;
    private int windDegree;
    private String windDirection;
    private int humidity;
    private float pm;

    public Weather(String cityLocation, String lastUpdated, int temperature, String condition, int windDegree,
                   String windDirection, int humidity, float pm) {
        this.cityLocation = cityLocation;
        this.lastUpdated = lastUpdated;
        this.temperature = temperature;
        this.condition = condition;
        this.windDegree = windDegree;
        this.windDirection = windDirection;
        this.humidity = humidity;
        this.pm = pm;
    }

    public static WeatherBuilder builder() {
        return new WeatherBuilder();
    }

    public static class WeatherBuilder {
        private String cityLocation;
        private String lastUpdated;
        private int temperature;
        private String condition;
        private int windDegree;
        private String windDirection;
        private int humidity;
        private float pm;

        WeatherBuilder() {
        }

        public WeatherBuilder cityLocation(String cityLocation) {
            this.cityLocation = cityLocation;
            return this;
        }

        public WeatherBuilder lastUpdated(String lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public WeatherBuilder temperature(int temp) {
            this.temperature = temp;
            return this;
        }

        public WeatherBuilder condition(String condition) {
            this.condition = condition;
            return this;
        }

        public WeatherBuilder windDegree(int windDegree) {
            this.windDegree = windDegree;
            return this;
        }

        public WeatherBuilder windDirection(String windDirection) {
            this.windDirection = windDirection;
            return this;
        }

        public WeatherBuilder humidity(int humidity) {
            this.humidity = humidity;
            return this;
        }

        public WeatherBuilder pm(float pm) {
            this.pm = pm;
            return this;
        }


        public Weather build() {
            return new Weather(cityLocation, lastUpdated, temperature, condition, windDegree, windDirection, humidity, pm);
        }
    }

    @Override
    public String toString() {
        return  "Location: " + cityLocation + "\n" +
                "Last updated time: " + lastUpdated + "\n" +
                "Temperature in Celsius: " + Float.toString(temperature) + "\n" +
                "Weather Condition: " + condition + "\n" +
                "Wind Degree: " + Integer.toString(windDegree) + "\n" +
                "Wind direction: " + windDirection + "\n" +
                "Humidity: " + Integer.toString(humidity) + "\n" +
                "PM 2.5: " + Float.toString(pm) + "\n";
    }
}
