package app.ui;

import app.ui.console.MainMenuUI;

/**
 *
 * @author Paulo Maio pam@isep.ipp.pt
 */

//Teste
public class Main {

    public static void main(String[] args)
    {
        try
        {
            MainMenuUI menu = new MainMenuUI();

            menu.run();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
