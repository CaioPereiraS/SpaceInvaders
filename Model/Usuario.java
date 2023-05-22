package Model;

public class Usuario {
    private String nick;
    private String senha;
    private int ultimaPontuacao;
    private int maximaPontuacao;

    // Construtor
    public Usuario(String nick, String senha, int ultimaPontuacao, int maximaPontuacao) {
        this.nick = nick;
        this.senha = senha;
        this.ultimaPontuacao = ultimaPontuacao;
        this.maximaPontuacao = maximaPontuacao;
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
