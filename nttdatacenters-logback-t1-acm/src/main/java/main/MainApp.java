package main;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase Main
 * @author Adrián Cámara Muñoz
 *
 */
public class MainApp {
	/** LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);
	static Scanner sc = new Scanner(System.in);

	/**
	 * Método principal del programa
	 * @param args
	 * @throws NoSuchAlgorithmException
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		LOG.info("Método: main() | TRAZA INICIO");

		boolean otraVez = false;
		String premiado;
		String boleto;
		double premio;

		do {

			LOG.info("Método: main() | INICIO JUEGO");

			// premio aleatorio 5 digitos
			premiado = generateReward();

			// leer num 5 digitos
			boleto = obtainTicket();

			// comparar numeros
			premio = obtainReward(boleto, premiado);

			// mostrar premio
			showReward(premio);

			// otra vez
			otraVez = repeatQuestion();

			LOG.info("Método: main() | FIN JUEGO");

		} while (otraVez);

		LOG.info("Método: main() | BUCLE PRUEBA INICIO");
		showBucle();
		LOG.info("Método: main() | BUCLE PRUEBA FIN");

		sc.close();

		LOG.info("Método: main() | TRAZA FIN");

	}

	/**
	 * Método bucle para probar lockback
	 */
	private static void showBucle() {
		for (int i = 0; i < 10000; i++) {
			LOG.info("Método: showBucle() | Bucle de prueba!");
		}

	}

	/**
	 * Mostrar premio al usuario
	 * @param premio premio obtenido
	 */
	private static void showReward(double premio) {

		if (premio > 0) {
			LOG.info("Método: showReward() | Obtiene un premio de {} €", premio);
			System.out.println("Tienes premio!, dirígete a tu oficina más cercana para reclamarlo");

		} else {
			LOG.info("Método: showReward() | No obtiene premio.");
			System.out.println("No tienes premio, suerte en la siguiente.");

		}

	}

	/**
	 * Comprobar si el usuario quiere volver a jugar
	 * @return true si quiere seguir jugando, false si no.
	 */
	private static boolean repeatQuestion() {
		String repeat = "";
		boolean otraVez = false;
		do {
			LOG.info("Método: repeatQuestion() | Comprobar si sigue jugando");
			System.out.println("¿Desea jugar de nuevo? (S-N)");
			repeat = sc.nextLine();

			if (repeat.equals("S")) {
				otraVez = true;
			} else if (repeat.equals("N")) {
				LOG.info("Método: repeatQuestion() | Deja de jugar");
				System.out.println("De acuerdo, gracias por jugar.");
				otraVez = false;
			} else {
				LOG.error("Método: repeatQuestion() | Valor no válido (S-N)");
				System.out.println("ERROR: Introducir S para jugar de nuevo o N si no quiere jugar más");
			}

		} while (!repeat.equals("S") && !repeat.equals("N"));

		return otraVez;
	}

	/**
	 * Obtener premio
	 * @param boleto numero del usuario
	 * @param premiado numero premiado
	 * @return premio obtenido
	 */
	private static double obtainReward(String boleto, String premiado) {
		double premio = 0;

		// ultima cifra 1.5€
		if (boleto.charAt(4) == premiado.charAt(4)) {
			premio = 1.5;

			// 2 ultimas 6€

			if (boleto.charAt(3) == premiado.charAt(3)) {
				premio = 6;

				// 3 ultimas 20€

				if (boleto.charAt(2) == premiado.charAt(2)) {
					premio = 20;

					// 4 ultimas 200€

					if (boleto.charAt(1) == premiado.charAt(1)) {
						premio = 200;

						// num entero 35000€

						if (boleto.charAt(0) == premiado.charAt(0)) {
							premio = 35000;
						}
					}
				}
			}

		} else if (boleto.charAt(0) == premiado.charAt(0)) {
			// primera cifra 1.5€
			premio = 1.5;

		}

		return premio;
	}

	/**
	 * introducir numero por el usuario
	 * @return numero introducido
	 */
	private static String obtainTicket() {
		String boleto = "";
		boolean esNumero = true;

		do {
			LOG.info("Método: obtainTicket() | Introducir boleto");
			System.out.println("Introducir numero de 5 cifras:");
			boleto = sc.nextLine();
			esNumero = true;

			if (boleto.length() != 5) {
				LOG.error("Método: obtainTicket() | Numero sin 5 cifras");
				System.out.println("ERROR: El número introducido debe ser de 5 cifras.");
			}

			try {
				Integer.parseInt(boleto);
			} catch (Exception e) {
				esNumero = false;
				LOG.error("Método: obtainTicket() | Formato de numero no reconocible.");
				System.out.println("ERROR: El número introducido debe ser un numero de 5 cifras.");
			}

		} while (!esNumero || boleto.length() != 5);

		return boleto;
	}

	/**
	 * generar numero premiado
	 * @return numero premiado
	 * @throws NoSuchAlgorithmException
	 */
	private static String generateReward() throws NoSuchAlgorithmException {

		StringBuilder bld = new StringBuilder();
		Random r = SecureRandom.getInstanceStrong();

		for (int i = 0; i < 5; i++) {
			int n = r.nextInt(9);
			bld.append(String.valueOf(n));
		}
		LOG.info("Método: generateReward() | Premio generado");
		return bld.toString();
	}

}
