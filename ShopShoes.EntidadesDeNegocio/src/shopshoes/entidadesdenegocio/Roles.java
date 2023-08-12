/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shopshoes.entidadesdenegocio;

/**
 *
 * @author MINEDUCYT
 */
public class Roles {
    
    private int Id;
    private String RolesName;
    
    public Roles(){
    }
    
    public  Roles(int Id,String RolesName){
        this.Id =Id;
        this.RolesName =RolesName;
    }
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
     public String getRolestName() {
        return RolesName;
    }

    public void setRolesName(String RolesName) {
        this.RolesName = RolesName;
    }
    
   
}
