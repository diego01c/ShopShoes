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
    private int Top_aux;
    
    public Trolley (){
        
    }
    
    public Trolley (int Id, int IdClient, int IdProduct, int Top_aux){
        this.Id = Id;
        this.IdClient = IdClient;
        this.IdProduct = IdProduct;
        this.Top_aux = Top_aux;
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
    
     public int getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(int IdProduct) {
        this.IdProduct = IdProduct;
    }    
    
     public int getTop_aux() {
        return Top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.Top_aux = top_aux;
    }
}
