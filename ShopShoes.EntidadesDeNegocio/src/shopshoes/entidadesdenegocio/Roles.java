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
    private int top_aux;
    public Roles(){
    }
    
    public  Roles(int Id,String RolesName, int top_aux){
        this.Id =Id;
        this.RolesName =RolesName;
        this.top_aux = top_aux;
    }
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
     public String getRolesName() {
        return RolesName;
    }

    public void setRolesName(String RolesName) {
        this.RolesName = RolesName;
    }
    
     public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }
   
}
