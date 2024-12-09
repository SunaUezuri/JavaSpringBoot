package br.com.alura.screenmatch;

import br.com.alura.screenmatch.service.ConsumoApi;
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
		ConsumoApi consumoApi = new ConsumoApi();

		//Requisitando a API
		var json = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&Season=1&apikey=961ef0ac");
		System.out.println(json);

	}
}
