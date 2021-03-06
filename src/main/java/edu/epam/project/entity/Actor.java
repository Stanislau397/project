package edu.epam.project.entity;

/**
 * Class represents movie actor
 *
 * @author Stanislau Kachan
 */
public class Actor extends Entity {

    private long actorId;
    private String firstName;
    private String lastName;
    private String picture;
    private String birthDate;
    private double height;
    private int age;

    /**
     * Constructor for Actor object
     */
    public Actor() {

    }

    /**
     * Constructor for Actor object
     * with given parameters:
     *
     * @param firstName String object of firstName
     * @param lastName  String object of lastName
     */
    public Actor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Constructor for Actor object
     * with given parameters:
     *
     * @param actorId   long value of actorId
     * @param firstName String object of firstName
     * @param lastName  String object of lastName
     */
    public Actor(long actorId, String firstName, String lastName) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Actor(long actorId, String firstName, String lastName, String picture) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
    }

    public Actor(long actorId, String firstName, String lastName, String picture, double height, int age) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.height = height;
        this.age = age;
    }

    /**
     * Getter method of actorId
     *
     * @return long value of actorId
     */
    public long getActorId() {
        return actorId;
    }

    /**
     * Setter method of actorId
     *
     * @param actorId long value of actorId
     */
    public void setActorId(long actorId) {
        this.actorId = actorId;
    }

    /**
     * Getter method of firstName
     *
     * @return String object of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter method of firstName
     *
     * @param firstName String object of firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter method of lastName
     *
     * @return String object of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter method of lastName
     *
     * @param lastName String object of lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        if (actorId != actor.actorId) return false;
        if (Double.compare(actor.height, height) != 0) return false;
        if (age != actor.age) return false;
        if (firstName != null ? !firstName.equals(actor.firstName) : actor.firstName != null) return false;
        if (lastName != null ? !lastName.equals(actor.lastName) : actor.lastName != null) return false;
        if (picture != null ? !picture.equals(actor.picture) : actor.picture != null) return false;
        return birthDate != null ? birthDate.equals(actor.birthDate) : actor.birthDate == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (actorId ^ (actorId >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(actorId).append(" ").append(lastName)
                .append(" ").append(firstName);
        return sb.toString();
    }
}
