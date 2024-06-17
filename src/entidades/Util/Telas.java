package entidades.Util;

import entidades.Criar;
import entidades.Historico;
import entidades.Login;
import entidades.Menu;

public class Telas {
      Login login = new Login();
      Menu menu = new Menu();
      Criar criar = new Criar();
      Historico historico = new Historico();





      public void volta(){

        login.setVisible(true);
        menu.setVisible(false);

      }
      public void criar(){

        login.setVisible(true);
        criar.setVisible(true);

      }
      public void home(){

        menu.setVisible(true);
        historico.setVisible(false);

      }



        
}
