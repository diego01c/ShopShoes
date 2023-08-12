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
    private int ClientId;
    private int IdUser;
    private String ClientName;
    private String LastName;
    private String TelefoneNumber;
    private String ClientAddress;

    public Client() {
    }

    public Client(int ClientId, int IdUser, String ClientName, String LastName, String TelefoneNumber, String ClientAddress) {
        this.ClientId = ClientId;
        this.IdUser = IdUser;
        this.ClientName = ClientName;
        this.LastName = LastName;
        this.TelefoneNumber = TelefoneNumber;
        this.ClientAddress = ClientAddress;
    }

    public int getClientId() {
        return ClientId;
    }

    public void setClientId(int ClientId) {
        this.ClientId = ClientId;
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
    
    
}
