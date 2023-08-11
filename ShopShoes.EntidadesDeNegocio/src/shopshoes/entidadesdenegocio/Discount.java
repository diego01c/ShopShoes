/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shopshoes.entidadesdenegocio;

/**
 *
 * @author MINEDUCYT
 */
public class Discount {
    private int Id;
    private String DiscountRate;
    private byte DiscountStatus;

    public Discount() {
    }

    public Discount(int Id, String DiscountRate, byte DiscountStatus) {
        this.Id = Id;
        this.DiscountRate = DiscountRate;
        this.DiscountStatus = DiscountStatus;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getDiscountRate() {
        return DiscountRate;
    }

    public void setDiscountRate(String DiscountRate) {
        this.DiscountRate = DiscountRate;
    }

    public byte getDiscountStatus() {
        return DiscountStatus;
    }

    public void setDiscountStatus(byte DiscountStatus) {
        this.DiscountStatus = DiscountStatus;
    }
    
}
