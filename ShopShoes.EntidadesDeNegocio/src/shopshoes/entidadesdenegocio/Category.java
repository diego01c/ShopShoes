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

public class Category {
    private int Id;
    private String CategoryName;
    private Image CategoryImage;
    
    public Category(){   
}

public Category(int Id, String CategoryName, Image CategoryImage){
    this.Id = Id;
    this.CategoryName = CategoryName;
    this.CategoryImage =CategoryImage;
} 
     public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    public String CategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }
    
      public Image getCategoryImage() {
        return CategoryImage;
    }

    public void setCategoryImage(Image CategoryImage) {
        this.CategoryImage = CategoryImage;
    }
    
}
 
