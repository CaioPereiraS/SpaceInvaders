package Model;

public class UsuarioModelo {
    private String nick;
    private String senha;
    private int ultimaPontuacao;
    private int maximaPontuacao;

    // Construtor
    public UsuarioModelo(String nick, String senha, int ultimaPontuacao, int maximaPontuacao) {
        this.nick = nick;
        this.senha = senha;
        this.ultimaPontuacao = ultimaPontuacao;
        this.maximaPontuacao = maximaPontuacao;
    }

    public UsuarioModelo() {

    }

    // Getters e Setters
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getUltimaPontuacao() {
        return ultimaPontuacao;
    }

    public void setUltimaPontuacao(int ultimaPontuacao) {
        this.ultimaPontuacao = ultimaPontuacao;
    }

    public int getMaximaPontuacao() {
        return maximaPontuacao;
    }

    public void setMaximaPontuacao(int maximaPontuacao) {
        this.maximaPontuacao = maximaPontuacao;
    }
}