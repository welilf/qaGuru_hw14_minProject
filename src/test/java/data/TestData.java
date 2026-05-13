package data;

import com.github.javafaker.Faker;

public class TestData {
    Faker faker = new Faker();

    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String userEmail = faker.internet().emailAddress();
    public String gender = faker.options().option("Male", "Female", "Other");
    public String userNumber = faker.number().digits(10);
    public String dayOfBirth = String.format("%02d", faker.number().numberBetween(1, 28));
    public String monthOfBirth = faker.options().option(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    );
    public String yearOfBirth = String.valueOf(faker.number().numberBetween(1950, 2008));
    public String subject = faker.options().option("Maths", "English", "Physics", "Chemistry");
    public String hobbie = faker.options().option("Sports", "Reading", "Music");
    public String picture = "cat.png";
    public String currentAddress = faker.address().fullAddress();
    public String state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    public String selectCity = city(state);
    public String city(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> null;
        };
    }
}
