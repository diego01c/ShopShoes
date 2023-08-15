/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shopshoes.entidadesdenegocio;

/**
 *
 * @author MINEDUCYT
 */
public class Inventory {
    private int Id;
    private int IdProduct;
    private int Stock;
    private int Tickets;
    private int Departures;
    private double Profits;
    private byte InventoryStatus;
    private int TopAux;

    public Inventory() {
    }

    public Inventory(int Id, int IdProduct, int Stock, int Tickets, int Departures, double Profits, byte InventoryStatus, int TopAux) {
        this.Id = Id;
        this.IdProduct = IdProduct;
        this.Stock = Stock;
        this.Tickets = Tickets;
        this.Departures = Departures;
        this.Profits = Profits;
        this.InventoryStatus = InventoryStatus;
        this.TopAux = TopAux;
        
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

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public int getTickets() {
        return Tickets;
    }

    public void setTickets(int Tickets) {
        this.Tickets = Tickets;
    }

    public int getDepartures() {
        return Departures;
    }

    public void setDepartures(int Departures) {
        this.Departures = Departures;
    }

    public double getProfits() {
        return Profits;
    }

    public void setProfits(double Profits) {
        this.Profits = Profits;
    }

    public byte getInventoryStatus() {
        return InventoryStatus;
    }

    public void setInventoryStatus(byte InventoryStatus) {
        this.InventoryStatus = InventoryStatus;
    }
    
    public int getTopAux() {
        return TopAux;
    }

    public void setTopAux(byte TopAux) {
        this.TopAux = TopAux;
    }
}
