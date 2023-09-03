/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shopshoes.entidadesdenegocio;

/**
 *
 * @author alexg
 */
public class Client {
    private int Id;
    private int IdUser;
    private String ClientName;
    private String LastName;
    private String TelefoneNumber;
    private String ClientAddress;
    private int top_aux;

    public Client() {
    }

    public Client(int Id, int IdUser, String ClientName, String LastName, String TelefoneNumber, String ClientAddress, int top_aux) {
        this.Id = Id;
        this.IdUser = IdUser;
        this.ClientName = ClientName;
        this.LastName = LastName;
        this.TelefoneNumber = TelefoneNumber;
        this.ClientAddress = ClientAddress;
        this.top_aux = top_aux;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String ClientName) {
        this.ClientName = ClientName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getTelefoneNumber() {
        return TelefoneNumber;
    }

    public void setTelefoneNumber(String TelefoneNumber) {
        this.TelefoneNumber = TelefoneNumber;
    }

    public String getClientAddress() {
        return ClientAddress;
    }

    public void setClientAddress(String ClientAddress) {
        this.ClientAddress = ClientAddress;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    

    
}
