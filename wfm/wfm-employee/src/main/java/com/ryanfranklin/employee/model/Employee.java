package com.ryanfranklin.employee.model;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "Employee")
public class Employee {

  @Id
  @GeneratedValue
  private long id;

  @Column(nullable = false)
  @NotNull
  @Size(min = 1)
  private String firstName;

  @Column(nullable = false)
  @NotNull
  @Size(min = 1)
  private String lastName;

  @Column(nullable = false)
  @Email
  @NotNull
  @Size(min = 1)
  private String email;

  @Column(nullable = false)
  @NotNull
  private long updatedEpochMilli;

  public Employee() {
    updated();
  }

  public Employee(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    updated();
  }

  private void updated() {
    updatedEpochMilli = Instant.now().toEpochMilli();
  }

  /**
   * Gets id
   *
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets id
   *
   * @param id the of id of the Employee
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets firstName
   *
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets firstName
   *
   * @param firstName the of firstName of the Employee
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets lastName
   *
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets lastName
   *
   * @param lastName the of lastName of the Employee
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets email
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets email
   *
   * @param email the of email of the Employee
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets updatedEpochMilli
   *
   * @return the updatedEpochMilli
   */
  public long getUpdatedEpochMilli() {
    return updatedEpochMilli;
  }

  /**
   * Sets updatedEpochMilli
   *
   * @param updatedEpochMilli the of timestamp in Epoch Milliseconds when the Employee was last
   * updated.
   */
  public void setUpdatedEpochMilli(long updatedEpochMilli) {
    this.updatedEpochMilli = updatedEpochMilli;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }
}
