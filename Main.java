import javax.swing.JOptionPane;
public class Main {
      // initializing classes
        DatabaseManager DatabaseManagerClass = new DatabaseManager();
        Registration registrationClass =new Registration();
      // initializing options 
        String []FirstOption = {"kebele","hospital","education"};
        String []SecondOption = {"register","quit","see File"};
        String [] SearchOptions = {"see all the logged data","search by Id"};

  public static void main(String []args) {
      // initializing self class
        Main self = new Main();

        while(true){
          var whereAt = self.DisplayOption(self.FirstOption,"where are you at ?");
          if(whereAt == 0 || whereAt == 1 || whereAt == 2 ) self.view(whereAt);
          else System.exit(0);
        }
  }

  
  private  String DisplayInput(String s){
    return JOptionPane.showInputDialog(null,s);
  }

  private  int DisplayOption(String[] arr,String s){
    return JOptionPane.showOptionDialog(null,s,"Digital Id",2,3,null,arr,null);
  }
  
  private  void Display(String s){
    JOptionPane.showMessageDialog(null,s);
  }
  private  void EndProgram(){
    Display("Thank you for using our system Good bye 0__0 ");
    System.exit(0);
  }
  private  void view(int whereAt){
        boolean register = false;
        boolean quit = false;
        boolean seeFiles = false;

        boolean seeAll = false;
        boolean searchById = false;

            var Firstinput = DisplayOption(SecondOption,"what do you want to do"); 
            if(Firstinput == 0) register = true;
            else if(Firstinput == 1) quit = true;
            else if(Firstinput == 2) seeFiles = true;
            else System.exit(0);

            if(register && whereAt == 0) registrationClass.register(); 
            else if(register && whereAt == 1) registrationClass.registerAtHospital(); 
            else if(register && whereAt == 2) registrationClass.registerAtSchool(); 
            else if (quit) EndProgram();  
            else if (seeFiles){

              var howDoYouWantToSeeFiles = DisplayOption(SearchOptions,"choose your best"); 
              if(howDoYouWantToSeeFiles == 0) seeAll = true;
              else if(howDoYouWantToSeeFiles == 1) searchById = true;
              else System.exit(0);

              if(seeAll && whereAt ==0) DatabaseManagerClass.read(); 
              else if(seeAll && whereAt ==1) DatabaseManagerClass.readHospitalData(); 
              else if(seeAll && whereAt ==2) DatabaseManagerClass.readSchoolData(); 
              else if(searchById){
                String IdToSearch = DisplayInput("Enter ID you what to search"); 
                if(IdToSearch == null)System.exit(0);
                Display(DatabaseManagerClass.search(IdToSearch, "id", whereAt ));
              }
              else System.exit(0);
              
            }
            else System.exit(0);

  }
}
