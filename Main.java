import Methods.*;
import Window.*;

public class Main {
    public static void main(String [] args) {
        try {
            System.out.println("Program started.");

            int human, ai; // Number of human and AI players

            Window ventana = new Window();
            ventana.setVisible(true);
            
            /* SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new Window().setVisible(true);
                }
            }); */
        } catch(Exception e) {
            System.out.println("An error occurred.");
        } finally {
            System.out.println("Program finished.");
        } // end of try, catch, finally
    } // end of main
} // end of Main