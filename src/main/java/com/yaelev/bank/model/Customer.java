package com.yaelev.bank.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity // Hibernate related annotation (required to map this class to DB)
@Table(name = "customer") // DB table same name as class, but lower case
public class Customer {

    // Sequence approach for mysql (instead of e.g. GenerationType.IDENTITY):
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
    @Column(name = "id", updatable = false)
    private long id; // generated by database

    @Column(name = "fName")
    private String fName;

    @Column(name = "lName")
    private String lName;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "ssn")
    private String ssn;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    // "ONE customer can have MANY accounts"
    // And "this column is owned by/mapped by customer column in TransactionAccount"
    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    // @JoinColumn(name ="ct_id", referencedColumnName = "id") // Already uses MappedBy
    private List<TransactionAccount> transactionAccounts = new ArrayList<>();

    @Transient // Column/variable NOT stored in DB; only used in java project
    private int age; // Also remove from constructor

    public Customer() {
    }

    // Parameterized constructor, excluding id, since id is generated by the database
    public Customer(String fName, String lName, LocalDate dateOfBirth, String ssn,
                    String address, String email, List<TransactionAccount> transactionAccounts) {
        this.fName = fName;
        this.lName = lName;
        this.dateOfBirth = dateOfBirth;
        this.ssn = ssn;
        this.address = address;
        this.email = email;
        this.transactionAccounts = transactionAccounts;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @JsonManagedReference // Solves the infinite recursion problem
    public List<TransactionAccount> getTransactionAccounts() {
        return transactionAccounts;
    }

    public void setTransactionAccounts(List<TransactionAccount> transactionAccounts) {
        this.transactionAccounts = transactionAccounts;
    }

    // Customized method, when need to add to account list
    public void addToTransactionAccounts(TransactionAccount transactionAccount) {
        this.transactionAccounts.add(transactionAccount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && age == customer.age && Objects.equals(fName, customer.fName) && Objects.equals(lName, customer.lName) && Objects.equals(dateOfBirth, customer.dateOfBirth) && Objects.equals(ssn, customer.ssn) && Objects.equals(address, customer.address) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fName, lName, dateOfBirth, ssn, address, email);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", ssn='" + ssn + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", transactionAccounts=" + transactionAccounts +
                ", age=" + age +
                '}';
    }
}
