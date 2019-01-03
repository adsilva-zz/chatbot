package br.com.chat.chatbot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Hello world first ChatBot Telegram!
 *
 */
public class App {
	public static void main(String[] args) {

		ApiContextInitializer.init();

		TelegramBotsApi botsapi = new TelegramBotsApi();

		try {
			botsapi.registerBot(new MeuPrimeiroBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
