public class Student extends Person{
  private int birthDate;
  private String firstName;
  private String lastName;
  private String  grade;
  private int id;
  private int Age;
  private String connectedWith;
  private String Address;

  Student (String firstName, String lastName, String Address,int birthDate, String grade, int id,String connectedWith){
    this.birthDate = birthDate;
    this.firstName = firstName;
    this.lastName  = lastName ;
    this.grade     = grade; 
    this.id        = id;
    this.Address   = Address;
    Age            = 2015-this.birthDate;
    this.connectedWith = connectedWith;

 }
 public String toString(){
    return this.id + "," + this.firstName + "," + this.lastName + "," + this.Age +"," +this.grade+ "," +this.connectedWith;   
 }
}
