package Banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.java.App;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Usuario {

    private String nome;
    private String senha;
    private String cpf;
    private String dataDeNascimento;
    private String altura;
    private String peso;
    private String numero;
    private String email;
    

    
    public Usuario() {
    }
    

    public Usuario(String senha, String cpf) {
        this.senha = senha;
        this.cpf = cpf;
    }


    public Usuario(String nome, String senha, String cpf, String dataDeNascimento, String altura, String peso,
            String numero, String email) {
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.altura = altura;
        this.peso = peso;
        this.numero = numero;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDatadenascString() {
        return dataDeNascimento;
    }

    public void setDatadenascString(String datadenascString) {
        this.dataDeNascimento = datadenascString;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPeço() {
        return peso;
    }

    public void setPeço(String peço) {
        this.peso = peso;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void CadastroUsuario(){


        String sql = "INSERT INTO usuario (nome, cpf, data_de_nascimento, altura, peso, numero_de_emergencia_pessoal, email, senha) VALUES (?, ?, ?, ?, ?, ?, ?,?)";


        System.err.println(nome);
        System.err.println(cpf);
        System.err.println(dataDeNascimento);
        System.err.println(altura);
        System.err.println(peso);
        System.err.println(numero);
        System.err.println(email);



        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setString(2, cpf);
            ps.setString(3, dataDeNascimento);
            ps.setString(4, altura);
            ps.setString(5, peso);
            ps.setString(6, numero);
            ps.setString(7, email);
            ps.setString(8, senha);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Usuario criado");

        } catch (Exception e) {
            System.out.println(e);

        }

    }



    public void Login(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = Conexao.getConnection();

            String sql = "SELECT * FROM usuario WHERE cpf = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cpf);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String senhaBanco = rs.getString("senha");
                if (senhaBanco.equals(this.senha)) {
                    App.CPF = rs.getString("cpf");
                    System.out.println(App.CPF);
                    App.menu.setVisible(true);
                    App.login.setVisible(false);

                } else {
                    System.out.println("Senha incorreta");
                }
            } else {
                System.out.println("Usuário não encontrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }



   public void preencherTabela(javax.swing.JTable jTable) {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0);

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = Conexao.getConnection();
            String id = App.CPF;

            String sql = "SELECT glicemia, data_registro FROM glicemia WHERE cpf = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String valor = rs.getString("glicemia");
                Date data = rs.getDate("data_registro");

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dataFormatada = sdf.format(data);

                model.addRow(new Object[] { valor, dataFormatada});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        }












}
