package institute.immune.chatapp.Class;

public class User {
    private Integer id;
    private String name;
    private String mail;
    private String category;
    private Boolean online;

    User(Integer id, String name, String mail){
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

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public Boolean getOnline() { return online; }

    public void setOnline(Boolean online) { this.online = online; }
}
