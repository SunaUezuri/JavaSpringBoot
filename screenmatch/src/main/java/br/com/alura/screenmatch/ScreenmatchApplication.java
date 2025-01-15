package br.com.alura.screenmatch;

import br.com.alura.screenmatch.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/* CommandLineRunner:
*  Ela permite que executemos alguma ação logo após
*  a inicialização de nossa aplicação. Pode ser muito útil,
*  por exemplo, se quisermos carregar alguns dados em nosso banco
*  de dados logo na inicialização de nossa aplicação.
* */
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        Main main = new Main();
        main.exibeMenu();

	}
}
