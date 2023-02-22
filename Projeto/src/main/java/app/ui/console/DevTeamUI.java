package app.ui.console;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class DevTeamUI implements Runnable {

    public DevTeamUI() {
    }

    public void run() {
        System.out.println();
        System.out.printf("Development Team:\n");
        System.out.printf("\t Gabriel Goncalves - 1191296@isep.ipp.pt \n");
        System.out.printf("\t Tiago Leite - 1191369@isep.ipp.pt \n");
        System.out.printf("\t João Durães - 1211314@isep.ipp.pt \n");
        System.out.printf("\t Francisco Bogalho - 1211304@isep.ipp.pt \n");
        System.out.printf("\t António Bernardo - 1210805@isep.ipp.pt \n");
        System.out.println();
    }
}
