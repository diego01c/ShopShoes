/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shopshoes.entidadesdenegocio;

/**
 *
 * @author MINEDUCYT
 */
public class PaymentMethod {
    private int Id;
    private String PaymentMethodName;
    private String PaymentMethodDescription;
    
    public PaymentMethod() {
        
    }
    
    public PaymentMethod (int Id, String PaymentMethodName, String PaymentMethodDescription){
        this.Id = Id;
        this.PaymentMethodName = PaymentMethodName;
        this.PaymentMethodDescription = PaymentMethodDescription;
        
    }
    
    public int getId(){
        return Id;
    }
    
    public void setId(int Id) {
        this.Id = Id;
    }
    
    public String getPaymentMethodName(){
        return PaymentMethodName;
    }
    
    public void setPaymentMethodName(String PaymentMethodName) {
        this.PaymentMethodName = PaymentMethodName;
    }
    
     public String getPaymentMethodDescription(){
        return PaymentMethodDescription;
    }
    
    public void setPaymentMethodDescription(String PaymentMethodDescription) {
        this.PaymentMethodDescription = PaymentMethodDescription;
    }
    
}
