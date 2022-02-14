package institute.immune.chatapp.Class;

public class Usuario {
    private Integer id;
    private String name;
    private String mail;
    private Boolean online;

    Usuario(Integer id, String name, String mail){
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.online = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
