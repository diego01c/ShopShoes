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
    private Byte CategoryImage;
     private int   top_aux;
    
    public Category(){   
}

public Category(int Id, String CategoryName, Byte CategoryImage,int top_aux){
    this.Id = Id;
    this.CategoryName = CategoryName;
    this.CategoryImage = CategoryImage;
     this.top_aux = top_aux;
} 
     public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }
    
      public Byte getCategoryImage() {
        return CategoryImage;
    }

    public void setCategoryImage(Byte CategoryImage) {
        this.CategoryImage = CategoryImage;
    }
    
       public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }  
}
 
