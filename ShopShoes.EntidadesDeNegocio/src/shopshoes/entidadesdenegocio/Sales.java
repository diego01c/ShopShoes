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
    private int IdTrolley;
    private int IdPaymentMethod;
    private int IdDirection;
    private double Total;
    private Trolley trolley;
    private PaymentMethod payment;
    private Direction direction;
    private int top_aux;

    public Sales() {
    }

    public Sales(int Id, int IdTrolley, int IdPaymentMethod, int IdDirection, double Total, Trolley trolley, PaymentMethod payment, Direction direction, int top_aux) {
        this.Id = Id;
        this.IdTrolley = IdTrolley;
        this.IdPaymentMethod = IdPaymentMethod;
        this.IdDirection = IdDirection;
        this.Total = Total;
        this.trolley = trolley;
        this.payment = payment;
        this.direction = direction;
        this.top_aux = top_aux;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdTrolley() {
        return IdTrolley;
    }

    public void setIdTrolley(int IdTrolley) {
        this.IdTrolley = IdTrolley;
    }

    public int getIdPaymentMethod() {
        return IdPaymentMethod;
    }

    public void setIdPaymentMethod(int IdPaymentMethod) {
        this.IdPaymentMethod = IdPaymentMethod;
    }

    public int getIdDirection() {
        return IdDirection;
    }

    public void setIdDirection(int IdDirection) {
        this.IdDirection = IdDirection;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public Trolley getTrolley() {
        return trolley;
    }

    public void setTrolley(Trolley trolley) {
        this.trolley = trolley;
    }

    public PaymentMethod getPayment() {
        return payment;
    }

    public void setPayment(PaymentMethod payment) {
        this.payment = payment;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }
    
    

    
    
}
