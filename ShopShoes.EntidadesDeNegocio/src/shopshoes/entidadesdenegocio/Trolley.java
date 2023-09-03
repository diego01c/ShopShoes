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
    private int Quantity;
    private byte StatusTrolley;
    private int Top_aux;
    private Products product;

    public Trolley() {
    }

    public Trolley(int Id, int IdClient, int IdProduct, int Quantity, byte StatusTrolley, int Top_aux, Products product) {
        this.Id = Id;
        this.IdClient = IdClient;
        this.IdProduct = IdProduct;
        this.Quantity = Quantity;
        this.StatusTrolley = StatusTrolley;
        this.Top_aux = Top_aux;
        this.product = product;
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

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public byte getStatusTrolley() {
        return StatusTrolley;
    }

    public void setStatusTrolley(byte StatusTrolley) {
        this.StatusTrolley = StatusTrolley;
    }

    public int getTop_aux() {
        return Top_aux;
    }

    public void setTop_aux(int Top_aux) {
        this.Top_aux = Top_aux;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }
    
    
}
