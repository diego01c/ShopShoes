/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shopshoes.entidadesdenegocio;
import java.time.LocalDate;


/**
 *
 * @author alexg
 */
public class Users {
    private int Id;
    private int IdRol;
    private String UserName;
    private String Pass;
    private LocalDate RegistrationDate;
    private String Mail;
    private String confirmPassword_aux;
   private int top_aux;
   private Roles rol;

    public Users() {
    }

    public Users(int Id, int IdRol, String UserName, String Pass, LocalDate RegistrationDate, String Mail, int top_aux, String confirmPassword_aux, Roles rol) {
        this.Id = Id;
        this.IdRol = IdRol;
        this.UserName = UserName;
        this.Pass = Pass;
        this.RegistrationDate = RegistrationDate;
        this.Mail = Mail;
        this.top_aux = top_aux;
        this.confirmPassword_aux = confirmPassword_aux;
        this.rol = rol;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int IdRol) {
        this.IdRol = IdRol;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public LocalDate getRegistrationDate() {
        return RegistrationDate;
    }

    public void setRegistrationDate(LocalDate RegistrationDate) {
        this.RegistrationDate = RegistrationDate;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String Mail) {
        this.Mail = Mail;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public int getTop_aux() {
        return top_aux;
    }
    
    public String getConfirmPassword_aux() {
        return confirmPassword_aux;
    }

    public void setConfirmPassword_aux(String confirmPassword_aux) {
        this.confirmPassword_aux = confirmPassword_aux;
    }    
    
    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }
    
}
