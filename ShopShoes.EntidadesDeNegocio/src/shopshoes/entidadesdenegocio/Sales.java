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
    private int SalesId;
    private int IdProduct;
    private int IdPaymentMethod;
    private int IdClient;
    private int Amount;
    private int Total;

    public Sales() {
    }

    public Sales(int SalesId, int IdProduct, int IdPaymentMethod, int IdClient, int Amount, int Total) {
        this.SalesId = SalesId;
        this.IdProduct = IdProduct;
        this.IdPaymentMethod = IdPaymentMethod;
        this.IdClient = IdClient;
        this.Amount = Amount;
        this.Total = Total;
    }

    public int getSalesId() {
        return SalesId;
    }

    public void setSalesId(int SalesId) {
        this.SalesId = SalesId;
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
    
    
}
