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
    private Client client;

    public Users() {
    }

    public Users(int Id, int IdRol, String UserName, String Pass, LocalDate RegistrationDate, String Mail, String confirmPassword_aux, int top_aux, Roles rol, Client client) {
        this.Id = Id;
        this.IdRol = IdRol;
        this.UserName = UserName;
        this.Pass = Pass;
        this.RegistrationDate = RegistrationDate;
        this.Mail = Mail;
        this.confirmPassword_aux = confirmPassword_aux;
        this.top_aux = top_aux;
        this.rol = rol;
        this.client = client;
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

    public String getConfirmPassword_aux() {
        return confirmPassword_aux;
    }

    public void setConfirmPassword_aux(String confirmPassword_aux) {
        this.confirmPassword_aux = confirmPassword_aux;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    
}
