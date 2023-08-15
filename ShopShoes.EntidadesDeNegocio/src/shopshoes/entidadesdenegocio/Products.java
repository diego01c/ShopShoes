/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shopshoes.entidadesdenegocio;

import java.awt.Image;

/**
 *
 * @author MINEDUCYT
 */
public class Products {
    private int Id;
    private int IdCategory;
    private String ProductName;
    private double Cost;
    private String ProductDescription;
    private Byte ProductImage;
    private int TopAux;

    public Products() {
    }

    public Products(int Id, int IdCategory, String ProductName, double Cost, String ProductDescription, Byte ProductImage, int TopAux) {
        this.Id = Id;
        this.IdCategory = IdCategory;
        this.ProductName = ProductName;
        this.Cost = Cost;
        this.ProductDescription = ProductDescription;
        this.ProductImage = ProductImage;
        this.TopAux = TopAux;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdCategory() {
        return IdCategory;
    }

    public void setIdCategory(int IdCategory) {
        this.IdCategory = IdCategory;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public double getCost() {
        return Cost;
    }

    public void setCost(double Cost) {
        this.Cost = Cost;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String ProductDescription) {
        this.ProductDescription = ProductDescription;
    }

    public Byte getProductImage() {
        return ProductImage;
    }

    public void setProductImage(Byte ProductImage) {
        this.ProductImage = ProductImage;
    }
    
    public int getTopAux() {
        return TopAux;
    }

    public void setTopAux(byte TopAux) {
        this.TopAux = TopAux;
    }
    
}
