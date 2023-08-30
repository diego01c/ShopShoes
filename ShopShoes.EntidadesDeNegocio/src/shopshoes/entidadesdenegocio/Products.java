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
    private String ProductImage;
    private String DetailImageOne;
    private String DetailImageTwo;
    private String DetailImageThree;
    private String Brand;
    private String Specifications;
    private String Status;
    private int TopAux;

    public Products() {
    }

    public Products(int Id, int IdCategory, String ProductName, double Cost, String ProductDescription, String ProductImage, String DetailImageOne, String DetailImageTwo, String DetailImageThree, String Brand, String Specifications, String Status, int TopAux) {
        this.Id = Id;
        this.IdCategory = IdCategory;
        this.ProductName = ProductName;
        this.Cost = Cost;
        this.ProductDescription = ProductDescription;
        this.ProductImage = ProductImage;
        this.DetailImageOne = DetailImageOne;
        this.DetailImageTwo = DetailImageTwo;
        this.DetailImageThree = DetailImageThree;
        this.Brand = Brand;
        this.Specifications = Specifications;
        this.Status = Status;
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

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String ProductImage) {
        this.ProductImage = ProductImage;
    }

    public String getDetailImageOne() {
        return DetailImageOne;
    }

    public void setDetailImageOne(String DetailImageOne) {
        this.DetailImageOne = DetailImageOne;
    }

    public String getDetailImageTwo() {
        return DetailImageTwo;
    }

    public void setDetailImageTwo(String DetailImageTwo) {
        this.DetailImageTwo = DetailImageTwo;
    }

    public String getDetailImageThree() {
        return DetailImageThree;
    }

    public void setDetailImageThree(String DetailImageThree) {
        this.DetailImageThree = DetailImageThree;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public String getSpecifications() {
        return Specifications;
    }

    public void setSpecifications(String Specifications) {
        this.Specifications = Specifications;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getTopAux() {
        return TopAux;
    }

    public void setTopAux(int TopAux) {
        this.TopAux = TopAux;
    }
    
    
}
