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
    private int Top_aux;
    
    public PaymentMethod() {
        
    }
    
    public PaymentMethod (int Id, String PaymentMethodName, String PaymentMethodDescription,int Top_aux ){
        this.Id = Id;
        this.PaymentMethodName = PaymentMethodName;
        this.PaymentMethodDescription = PaymentMethodDescription;
        this.Top_aux = Top_aux;
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
    
     public int getTop_aux() {
        return Top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.Top_aux = top_aux;
    }
    
}
