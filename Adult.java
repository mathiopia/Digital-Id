public class Adult extends Person {

  private   String      FirstName;
  private   String      LastName;
  private   String      Address;
  private   String      connectedWith;
  private String      FullName;
  private int         BirthDate;
  private   int         Age;
  private   int         Id;
  boolean   isMarraid = false;


  public Adult(String FirstName, String LastName, int birthDate, String address, boolean status, String other,int Id) {
    this.FirstName = FirstName;
    this.LastName  = LastName;
    this.BirthDate = birthDate;
    this.Address   = address;
    this.isMarraid = status;
    this.Age       = 2015 - this.BirthDate;
    this.Id        = Id;
    connectedWith  = other;
    FullName       = FirstName + " " + LastName;
  }



  public String toString() {
    return this.Id + "," + FullName + "," + Age + "," + Address + "," + isMarraid + "," + connectedWith;
  }

}
