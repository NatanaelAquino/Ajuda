package entidades.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Banco.Conexao;
import main.java.App;

public class Calcular {

    private String valor;

    public Calcular(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void calcula() {

        String input = valor;
        
        try {
            int valor = Integer.parseInt(input);

            if (valor < 50) {
                String mensagem = "<html>"
                        + "O valor é baixo.<br>"
                        + "Sua glicemia está baixa. Considere fazer o seguinte:<br>"
                        + "- Consuma 15-20 gramas de carboidratos de ação rápida<br>"
                        + "- Meça sua glicemia novamente após 15 minutos<br>"
                        + "- Repita o consumo de carboidratos se necessário.<br>"
                        + "- Consulte seu médico se sua glicemia não aumentar."
                        + "</html>";
                JOptionPane.showMessageDialog(null, mensagem);
            } else if (valor >= 50 && valor <= 89) {
                String mensagem = "<html>"
                        + "O valor é normal.<br>"
                        + "Sua glicemia está normal. Continue com sua dieta e rotina de cuidados conforme recomendado pelo seu médico."
                        + "</html>";
                JOptionPane.showMessageDialog(null, mensagem);
            } else if (valor >= 90) {
                String mensagem = "<html>"
                        + "O valor é alto.<br>"
                        + "Sua glicemia está alta. Considere fazer o seguinte:<br>"
                        + "- Tome insulina de ação rápida conforme prescrito pelo seu médico.<br>"
                        + "- Faça exercícios físicos leves, como caminhar.<br>"
                        + "- Beba bastante água.<br>"
                        + "- Evite consumir alimentos ricos em carboidratos até sua glicemia baixar."
                        + "</html>";
                JOptionPane.showMessageDialog(null, mensagem);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "A quantidade não é um número válido.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
 public void InserirGlicemia() {
        String sql = "INSERT INTO glicemia (Glicemia, cpf) VALUES (?, ?)";

        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
    
            // Define o valor da glicemia e o CPF
            ps.setString(1, valor);
            ps.setString(2, App.CPF);
    
            int rowsAffected = ps.executeUpdate();
    
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Dados de glicemia inseridos com sucesso");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao inserir dados de glicemia");
            }
    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados de glicemia: " + e.getMessage());
            e.printStackTrace();
        }
    }



}
