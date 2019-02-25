/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author pc
 */
public class User {
    private int id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private double latitude;
    private double longitude;
    private String phoneNumber;

    
    private String password;
    private int role;
      private int statut;
    private String MagasinName;
    private String image;
    private String associationName;
    private String attestation;
    

    public User(int id, String username, String email, String firstName, String lastName, String gender, double latitude, double longitude, String phoneNumber, String password, int role, String image) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
        this.image = image;
        
    }
    public User( String username, String email, String firstName, String lastName, String phoneNumber, String password, String image) {
      
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
     
        this.phoneNumber = phoneNumber;
        this.password = password;
      
        this.image = image;
    }

    public User(int id, String email, String firstName, String lastName,int role,String username, String gender, String phoneNumber, String password,  String image,int statut) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.username=username;
        this.role = role;
        this.image = image;
        this.statut=statut;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
    public User(int statut) {
        this.statut = statut;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getMagasinName() {
        return MagasinName;
    }

    public void setMagasinName(String MagasinName) {
        this.MagasinName = MagasinName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName;
    }

    public String getAttestation() {
        return attestation;
    }

    public void setAttestation(String attestation) {
        this.attestation = attestation;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", latitude=" + latitude + ", longitude=" + longitude + ", phoneNumber=" + phoneNumber + ", password=" + password + ", role=" + role + ", MagasinName=" + MagasinName + ", image=" + image + ", associationName=" + associationName + ", attestation=" + attestation + '}';
    }
    
    
}
