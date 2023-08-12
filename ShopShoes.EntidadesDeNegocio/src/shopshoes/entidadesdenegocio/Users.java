/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shopshoes.entidadesdenegocio;
import java.time.LocalDateTime;


/**
 *
 * @author alexg
 */
public class Users {
    private int Id;
    private int IdRol;
    private String UserName;
    private String Pass;
    private LocalDateTime RegistrationDate;
    private String Mail;

    public Users() {
    }

    public Users(int Id, int IdRol, String UserName, String Pass, LocalDateTime RegistrationDate, String Mail) {
        this.Id = Id;
        this.IdRol = IdRol;
        this.UserName = UserName;
        this.Pass = Pass;
        this.RegistrationDate = RegistrationDate;
        this.Mail = Mail;
    }

    public int getUsersId() {
        return Id;
    }

    public void setUsersId(int Id) {
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

    public LocalDateTime getRegistrationDate() {
        return RegistrationDate;
    }

    public void setRegistrationDate(LocalDateTime RegistrationDate) {
        this.RegistrationDate = RegistrationDate;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String Mail) {
        this.Mail = Mail;
    }
    
    
}
