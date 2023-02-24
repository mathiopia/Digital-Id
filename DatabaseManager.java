import java.io.*;
import javax.swing.JOptionPane;
import java.util.Scanner;
public class DatabaseManager {
  //==========================================================================
  //it takes a person and takes all the registered persons and write the on to the Database.txt
  //==========================================================================
  public  void writes(Person x,int y){
    boolean writeOnKebeleDatabase   = false;
    boolean writeOnHospitalDatabase = false;
    boolean writeOnSchoolDatabase   = false;

    if(y == 0) writeOnKebeleDatabase   = true;
    if(y == 1) writeOnHospitalDatabase = true;
    if(y == 2) writeOnSchoolDatabase   = true;
    try {
      // the other argument File Writer takes is to specify append instead of  overwrite
      var kebeleDatabase   =  new FileWriter("./Database.txt",true);
      var hospitalDatabase =  new FileWriter("./baby.txt",true);
      var schoolDatabase   =  new FileWriter("./Student.txt",true);
      
      if( writeOnHospitalDatabase ){
        kebeleDatabase.close();
        schoolDatabase.close();
        this.writeOnDatabase(x,hospitalDatabase);
      }
      else if(writeOnSchoolDatabase){
        kebeleDatabase.close();
        hospitalDatabase.close();
        this.writeOnDatabase(x,schoolDatabase);
      }
      else if(writeOnKebeleDatabase){
        schoolDatabase.close();
        hospitalDatabase.close();
        this.writeOnDatabase(x,kebeleDatabase);
      }

  } 
  catch (Exception ex) {
    DisplayError("there is some error happened");
  }

        }
  private  void writeOnDatabase (Person x,FileWriter file){

    try{
    BufferedWriter fileOut=new BufferedWriter(file); 
    fileOut.write(x.toString());
    fileOut.write("\n");
    fileOut.close();
    System.out.println("The Object  was successfully written to a file");

  } catch (Exception e) {
    DisplayError("there is some error happened");
  }
}

public  void read(){

  String Id = ""; String FullName = ""; String Age = ""; String Address = ""; String status = ""; String connectedWith = "";
  Scanner x;
  File db = new File("./Database.txt");
    try{
      x=new Scanner(db);
      x.useDelimiter("[,\n]");
      String relmsg="<html>";
    while(x.hasNext()){
        Id=x.next();
        FullName=x.next();
        Age=x.next();
        Address=x.next();
        status=x.next();
        connectedWith=x.next();
        String msg="ID: "+Id+" FULL NAME: "+FullName+" AGE: "+Age+" ADDRESS: "+Address+"  MARRIAGE STATUS: "+status+" CONNECTED WITH: "+connectedWith;
        relmsg+=msg;  
        relmsg+="<br>";
        
      }
       relmsg+="</html>";
       Display(relmsg); 

    }
    catch(Exception e){
       Display("there is some kind of error"); 
    }

}
public void readHospitalData(){

  String Id = ""; String firstName = ""; String lastName = ""; String mother = ""; String connectedWith = "";String birthDate ="";
  Scanner x;
  File db = new File("./baby.txt");
    try{
      x=new Scanner(db);
      x.useDelimiter("[,\n]");
      String relmsg="<html>";
    while(x.hasNext()){
        Id           =x.next();
        firstName    =x.next();
        lastName     =x.next();
        mother       =x.next();
        birthDate    =x.next();
        connectedWith=x.next();

        String msg="ID: "+Id+" FULL NAME: "+firstName+" "+lastName+" Birth Date: "+birthDate+" mother's name : "+mother+" relative : "+connectedWith;
        relmsg+=msg;  
        relmsg+="<br>";
      }
       relmsg+="</html>";
       Display(relmsg); 

    }
    catch(Exception e){
       Display("there is some kind of error"); 
    }
}
public void readSchoolData(){

  String Id = ""; String firstName = ""; String lastName = ""; String Age = ""; String Grade = "";String connectedWith =""; 
  Scanner x;
  File db = new File("./Student.txt");
    try{
      x=new Scanner(db);
      x.useDelimiter("[,\n]");
      String relmsg="<html>";
    while(x.hasNext()){
        Id           =x.next();
        firstName    =x.next();
        lastName     =x.next();
        Age          =x.next();
        Grade        =x.next();
        connectedWith=x.next();

        String msg="ID: "+Id+" FULL NAME: "+firstName+" "+lastName+" Age : "+Age+"grade "+Grade+" relative : "+connectedWith;
        relmsg+=msg;  
        relmsg+="<br>";
        
      }
       relmsg+="</html>";
       Display(relmsg); 

    }
    catch(Exception e){
       Display("there is some kind of error"); 
    }

}


public  String search(String searchTerm ,String whatToSearch, int whereToSearch){
  
  String Id="";String second="";String third="";String fourth="";String fiveth="";String connectedWith="";
  File db=new File("./Database.txt");

  Scanner scan;
  boolean hospital   = false;
  boolean kebele   = false;
  boolean school     = false;
  boolean found      = false;
  boolean searchName = false;
  boolean searchId   = false;

  if(whereToSearch == 1)hospital = true;
  if(whereToSearch == 0)kebele = true;
  if(whereToSearch == 2)school = true;

  if(hospital) {
    db=new File("./baby.txt");
  }
  if(school) {
    db=new File("./student.txt");
  }
  if(whatToSearch.equals("name")) searchName = true;
  if(whatToSearch.equals("id"))   searchId   = true;

  try{ 
    scan = new Scanner(db);
    scan.useDelimiter("[,\n]");
    while(scan.hasNext() && !found){
      Id            = scan.next();
      second      = scan.next();
      third           = scan.next();
      fourth       = scan.next();
      fiveth        = scan.next();
      connectedWith = scan.next();
      
      if(Id.equals(searchTerm) && searchId) found=true;
      if(second.equals(searchTerm) && searchName) found=true;

      }

    if(found && kebele){
      scan.close();
      String msg="<html> Id: "+Id +"<br>Full Name:"+second+"<br>Age: "+third+"<br>Address: "+fourth+"<br>MarriageStatus: "+fiveth+"<br>connectedWith: "+connectedWith+"</html>";
      return msg;
    }
    else if(found && school){
      scan.close();
      String msg="ID: "+Id+" FULL NAME: "+second+" "+third+" Age : "+fourth+"grade "+fiveth+" relative : "+connectedWith;
      return msg;
    }
    else if(found && hospital){
      scan.close();
      String msg="ID: "+Id+" FULL NAME: "+second+" "+third+" Birth Date: "+fourth+" mother's name : "+fiveth+" relative : "+connectedWith;
      return  msg;
    }
    else {
      scan.close();
      return ("Id is not registered on the database");
    }
    
  }
  catch(Exception ex){
    return "some error has happened";
  }
}
public String[] searchHospital(String idToSearch){

  String Id = "";
  String firstName = "";String lastName = "";String mother="";String birthDate=""; String connectedWith="";
  File db=new File("./baby.txt");
  Scanner scan;
  boolean found = false;
  
  try{ 
    scan = new Scanner(db);
    scan.useDelimiter("[,\n]");
    while(scan.hasNext() && !found){
      Id            = scan.next();
      firstName      = scan.next();
      lastName      = scan.next();
      mother           = scan.next();
      birthDate       = scan.next();
      connectedWith = scan.next();
      
      if(Id.equals(idToSearch)) found=true;
      }

    scan.close();

    if(!found) return new String[] {"Id is not registered on the database"};
    else 
      return new String[] {firstName,lastName,birthDate,connectedWith};
    
    
  }
  catch(Exception ex){
    return new String[] { "some error has happened" };
  }
}
private void Display(String s){
      JOptionPane.showMessageDialog(null,s);
}
private void DisplayError(String s){
      JOptionPane.showMessageDialog(null, s, "ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE );
}

}

