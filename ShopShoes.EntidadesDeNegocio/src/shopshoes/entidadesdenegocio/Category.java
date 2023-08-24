/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shopshoes.entidadesdenegocio;
/**
 *
 * @author MINEDUCYT
 */

public class Category {
    private int Id;
    private String CategoryName;
    private String CategoryImage;
    private int Top_aux;

    public Category() {
    }

    public Category(int Id, String CategoryName, String CategoryImage, int Top_aux) {
        this.Id = Id;
        this.CategoryName = CategoryName;
        this.CategoryImage = CategoryImage;
        this.Top_aux = Top_aux;
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

    public String getCategoryImage() {
        return CategoryImage;
    }

    public void setCategoryImage(String CategoryImage) {
        this.CategoryImage = CategoryImage;
    }

    public int getTop_aux() {
        return Top_aux;
    }

    public void setTop_aux(int Top_aux) {
        this.Top_aux = Top_aux;
    }
    
 
}
 
