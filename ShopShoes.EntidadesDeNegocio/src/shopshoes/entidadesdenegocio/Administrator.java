/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shopshoes.entidadesdenegocio;

/**
 *
 * @author MINEDUCYT
 */
public class Administrator {
    private int Id;
    private int IdUser;
    private String AdministratorName;
    
    public Administrator(){
    }
    public Administrator(int Id, int IdUser,String AdministratorName){
        this.Id =Id;
        this.IdUser =IdUser;
        this.AdministratorName = AdministratorName;
    }
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
      public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser= IdUser;
    }
    
     public String getAdministratorName() {
        return AdministratorName;
    }
    public void setAdministratortName(String AdministratorName) {
        this.AdministratorName = AdministratorName;
    }   
}