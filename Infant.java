public class Infant extends Person {
  private int    birthDate;
  private int    birthId;
  private String Name;
  private String lastName;
  private String birthPlace;
  private String mother;
  private String father;
  private   int    connectedWith;

  Infant(String babyName, String fatherName, String birthPlace, int birthDate, int birthId, String motherName ,int motherId){
    Name            = babyName;
    father          = fatherName;
    mother          = motherName;
    this.birthPlace = birthPlace;
    this.birthDate  = birthDate;
    this.birthId    = birthId;
    connectedWith  = motherId; 
    
  }
  public String toString(){
  return this.birthId + "," +  Name + "," + father + "," + mother + "," + birthDate + "," + connectedWith ;
  }

}
