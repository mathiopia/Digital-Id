import java.security.SecureRandom;
import javax.swing.JOptionPane;
public class  Registration{ 

    DatabaseManager databaseManager = new DatabaseManager();

  public void register(){

    String []MarrageOptions = {"Married","single"};
    int    BirthDate         = 0; 
    boolean correctBirthDate = false;
    boolean correctBirthID  = false;
    String FirstName = null;
    String LastName  = null;
    String relative  = null;
    int BabyId =1;

    
    String birthIdString = this.DisplayInput("Enter your birth Id");
        if(birthIdString == null)System.exit(0);
    try{ BabyId = Integer.parseInt(birthIdString);}
    catch(Exception e){
      DisplayError("hay you made a big mistake computer doesn't make one");
    }
    int Id = BabyId; 

    
    while (BabyId != 0  && !correctBirthID) {
      String [] allDataAtBirth = databaseManager.searchHospital(Integer.toString(BabyId));
      if (allDataAtBirth.length == 1) {
          DisplayError(allDataAtBirth[0]);
          birthIdString = this.DisplayInput("Enter your birth Id");
            if(birthIdString == null)System.exit(0);
          try{
            BabyId = Integer.parseInt(birthIdString);
          }
          catch(Exception e){
            DisplayError("hay you made a big mistake computer doesn't make one");
            continue;
          }
          if (BabyId == 0) break;
          continue;
      }
      else{
        FirstName = allDataAtBirth[0];
        LastName  = allDataAtBirth[1];
        BirthDate  = Integer.parseInt(allDataAtBirth[2]);
        relative  = allDataAtBirth[3];
        Display("you data have been received " + FirstName+LastName);
        break;
      }

    } 
    if(BabyId == 0) {
       FirstName = this.DisplayInput("Enter your First Name");
        if (FirstName == null)System.exit(0);
       LastName = this.DisplayInput("Enter your Last Name");
        if (LastName == null) System.exit(0);
       relative = this.DisplayInput("Enter the person you are connected with");
        if(relative == null)System.exit(0);
      String validation = databaseManager.search(relative,"id",0);
      while(validation == "Id is not registered on the database" && false == relative.equals("0")){
        relative   =   this.DisplayInput("the person you are connected with");
          if(relative == null)System.exit(0);
          validation = databaseManager.search(relative, "id", 0);
        }
      Id = generateId();
      while(!correctBirthDate){ 
        try{
          String BirthDateString = this.DisplayInput("Enter your Birth Date (E.c)");
            if(BirthDateString == null)System.exit(0);
          BirthDate = Integer.parseInt(BirthDateString);
          correctBirthDate = true;
        }
        catch(Exception e){
          DisplayError("there is an error");
        }
      }
    }
    
    String Address = this.DisplayInput("Enter your Address");
      if(Address == null) System.exit(0);
    int status = this.DisplayOption(MarrageOptions ,"Marriage Status");

    Person newPerson;
    newPerson = new Adult(FirstName,LastName,BirthDate,Address,status==0,relative,Id);
    databaseManager.writes(newPerson,0);
    Display("you are successfully registered  Id = "+ Id);
  }


  public void registerAtHospital() {
    String FirstName = this.DisplayInput("Enter  baby's new name");
      if (FirstName == null)
        System.exit(0);
    String LastName = this.DisplayInput("Enter father's name");
      if (LastName == null)
        System.exit(0);
    String motherName = this.DisplayInput("Enter the mother's name");
    String connectedWith = this.DisplayInput("Enter the mother's Id");
    String Address    = this.DisplayInput("Enter your birth place");
    int birthId       = this.generateId();
    int birthDate =0 ;
    //try{ birthDate     =   Integer.parseInt(this.DisplayInput("Enter Birth Date"));}
    //catch(Exception e){DisplayError("there is a problem");}
    boolean correctBirthDate=false;
    while(!correctBirthDate){ 
      try{
        birthDate = Integer.parseInt(this.DisplayInput("Enter your Birth Date (E.c)"));
        if(birthDate == -1)System.exit(0);
        correctBirthDate = true;
      }
      catch(Exception e){
        DisplayError("there is an error");
      }
    }
    
    Person newPerson = new Infant(FirstName, LastName, Address, birthDate, birthId, motherName,
                Integer.parseInt(connectedWith));
    databaseManager.writes(newPerson, 1);
    Display("you are successfully registered  Id = "+ birthId);
  }

public void registerAtSchool() {
  int BabyId =0; 
  String birthIdString;
  try{
   birthIdString =this.DisplayInput("Enter your birth Id ");
   BabyId = Integer.parseInt(birthIdString);
  }
  catch(Exception e){
    BabyId =1;
  }
  boolean correctBirthID=false;
  int Id = BabyId;
  int BirthDate = 0;
  String FirstName =null;
  String LastName =null;
  String relative =null;
    while (BabyId != 0  && !correctBirthID) {
      String [] allDataAtBirth = databaseManager.searchHospital(Integer.toString(BabyId));
      if (allDataAtBirth.length == 1) {
        DisplayError("there is some error try again!! ");
        try{
        birthIdString = this.DisplayInput("Enter your birth Id");
          if(birthIdString == null)System.exit(0);
        BabyId = Integer.parseInt(birthIdString);
        if (BabyId == 0) break;
        continue;

        }
        catch(Exception e){
          DisplayError("your input have a error please try again");
          continue;
        }
      }
      else{
        FirstName = allDataAtBirth[0];
        LastName  = allDataAtBirth[1];
        BirthDate  = Integer.parseInt(allDataAtBirth[2]);
        relative  = allDataAtBirth[3];
        Display("you data have been received " + FirstName+LastName);
        break;
      }

    } 
    if(BabyId == 0){
      Id = generateId();
      FirstName = this.DisplayInput("Enter your First Name");
        if (FirstName == null) System.exit(0);
      LastName = this.DisplayInput("Enter your Last Name");
        if (LastName == null) System.exit(0);
      int birthDate     =   0;
      boolean correctBirthDate=true;
      while(!correctBirthDate){ 
        try{
          birthDate = Integer.parseInt(this.DisplayInput("Enter your Birth Date (E.c)"));
          if(birthDate == -1)System.exit(0);
          correctBirthDate = true;
        }
        catch(Exception e){
          DisplayError("there is an error");
        }
      }


    }
    String Address = this.DisplayInput("Enter your Address");
    String Grade = this.DisplayInput("what grade are you in ?");
    Person newPerson = new Student(FirstName,LastName,Address,BirthDate,Grade,Id,relative);
    databaseManager.writes(newPerson, 2);
    Display("you are successfully registered  Id = "+ Id);



}

private int generateId() {
  int Id=0;
  boolean sameIdGenerated = true;
    while (sameIdGenerated ){
      SecureRandom rand = new SecureRandom();
      Id = rand.nextInt(10000000);
      String idString = Integer.toString(Id);
      String CheckDepulicate =  databaseManager.search(idString,"id",0);
      if(CheckDepulicate.equals("Id is not registered on the database")) sameIdGenerated = false;
    }
    return Id;
  
} 
private  String DisplayInput(String s){
  return JOptionPane.showInputDialog(null,s);
}
private  void DisplayError(String s){
    JOptionPane.showMessageDialog(null,s,"Error",JOptionPane.ERROR_MESSAGE);
}
private  int DisplayOption(String [] arr ,String s){
  return  JOptionPane.showOptionDialog(null,s,"Digital Id",2,3,null,arr,null);
}
private  void Display(String s){
  JOptionPane.showMessageDialog(null,s);
}
}

