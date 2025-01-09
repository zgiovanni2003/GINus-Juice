package Shop;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Ottieni l'istanza del singleton e carica i prodotti nel contesto
        ProdottoSingleton prodottoSingleton = ProdottoSingleton.getInstance();
        prodottoSingleton.salvaProdottiNelContext(sce.getServletContext());

        System.out.println("Prodotti caricati e salvati nel ServletContext.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Eventuali operazioni di cleanup, se necessarie
        System.out.println("Applicazione distrutta.");
    }
}
