package com.yaelev.bank.customer;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

// Part of "Model"; POJO

@Entity // Hibernate related annotation (required to map this class to DB)
@Table // Specifying DB component
public class Customer {

    @Id // Required to make some form of sequencing for the table
    @GeneratedValue (strategy= GenerationType.AUTO)
    private long id; // generated by database
    private long customerNo;
    private String fName;
    private String lName;
    private LocalDate dateOfBirth;
    private String ssn;
    private String address;
    private String email;

    @Transient // Column/variable NOT stored in DB; only used in java project
    private int age; // Also remove from constructor


    // Default constructor
    public Customer() {
    }

    // Parameterized constructor, excluding id, since id is generated by the database
    public Customer(long customerNo, String fName, String lName, LocalDate dateOfBirth, String ssn, String address, String email) {
        this.customerNo = customerNo;
        this.fName = fName;
        this.lName = lName;
        this.dateOfBirth = dateOfBirth;
        this.ssn = ssn;
        this.address = address;
        this.email = email;
    }

    // Parameterized constructor with all fields, to use before database access is set
    public Customer(long id, long customerNo, String fName, String lName, LocalDate dateOfBirth, String ssn, String address, String email) {
        this.id = id;
        this.customerNo = customerNo;
        this.fName = fName;
        this.lName = lName;
        this.dateOfBirth = dateOfBirth;
        this.ssn = ssn;
        this.address = address;
        this.email = email;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(long customerNo) {
        this.customerNo = customerNo;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && customerNo == customer.customerNo && Objects.equals(fName, customer.fName) && Objects.equals(lName, customer.lName) && Objects.equals(dateOfBirth, customer.dateOfBirth) && Objects.equals(ssn, customer.ssn) && Objects.equals(address, customer.address) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerNo, fName, lName, dateOfBirth, ssn, address, email);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customerNo=" + customerNo +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", ssn='" + ssn + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
