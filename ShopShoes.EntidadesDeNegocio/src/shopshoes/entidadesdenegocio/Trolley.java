/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shopshoes.entidadesdenegocio;

/**
 *
 * @author MINEDUCYT
 */
public class Trolley {
private int Id;
    private int IdClient;
    private int IdProduct;
    
    public Trolley (){
        
    }
    
    public Trolley (int Id, int IdClient, int IdProduct){
        this.Id = Id;
        this.IdClient = IdClient;
        this.IdProduct = IdProduct;
    }
    
     public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
     public int getIdClient() {
        return IdClient;
    }

    public void setIdClient(int IdClient) {
        this.IdClient = IdClient;
    }
    
     public int getProduct() {
        return IdProduct;
    }

    public void setIdProduct(int IdProduct) {
        this.IdProduct = IdProduct;
    }    
    
}
