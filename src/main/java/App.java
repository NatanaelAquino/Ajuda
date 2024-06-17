package main.java;

import Banco.Usuario;
import entidades.Criar;
import entidades.Login;
import entidades.Menu;

public class App {

    public static String CPF;
    public static Login login = new Login();
    public static Criar criar = new Criar();
    public static Menu menu = new Menu();
    public static Usuario usuario = new Usuario();



    public static void main(String[] args) throws Exception {

        login.setVisible(true);

    }
}
