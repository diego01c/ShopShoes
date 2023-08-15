/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shopshoes.entidadesdenegocio;

/**
 *
 * @author alexg
 */
public class Sales {
    private int Id;
    private int IdProduct;
    private int IdPaymentMethod;
    private int IdClient;
    private int Amount;
    private int Total;
    private int top_aux;

    public Sales() {
    }

    public Sales(int Id, int IdProduct, int IdPaymentMethod, int IdClient, int Amount, int Total, int top_aux) {
        this.Id = Id;
        this.IdProduct = IdProduct;
        this.IdPaymentMethod = IdPaymentMethod;
        this.IdClient = IdClient;
        this.Amount = Amount;
        this.Total = Total;
        this.top_aux = top_aux;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(int IdProduct) {
        this.IdProduct = IdProduct;
    }

    public int getIdPaymentMethod() {
        return IdPaymentMethod;
    }

    public void setIdPaymentMethod(int IdPaymentMethod) {
        this.IdPaymentMethod = IdPaymentMethod;
    }

    public int getIdClient() {
        return IdClient;
    }

    public void setIdClient(int IdClient) {
        this.IdClient = IdClient;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }
    
    
}
