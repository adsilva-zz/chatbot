package br.com.chat.chatbot;

import java.util.Comparator;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Meu primeiro chatBot. Esse Projeto foi realizado através de tutoriais para aprender como consumir API do Telegram.
 * 
 * @author Amanda da Silva Ito
 * @version 1.0
 *
 */
public class MeuPrimeiroBot extends TelegramLongPollingBot {

	public void onUpdateReceived(Update update) {

		if (update.hasMessage() && update.getMessage().hasText()) {
			String message = update.getMessage().getText();
			long chatId = update.getMessage().getChatId();

			SendMessage msg = new SendMessage().setChatId(chatId).setText(message);

			try {
				execute(msg);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		} else if (update.hasMessage() && update.getMessage().hasPhoto()) {
			long chatId = update.getMessage().getChatId();
			System.out.println(chatId);
			List<PhotoSize> fotos = update.getMessage().getPhoto();
			String f_id = fotos.stream().sorted(Comparator.comparing(PhotoSize::getFileSize).reversed()).findFirst()
					.orElse(null).getFileId();
			int f_largura = fotos.stream().sorted(Comparator.comparing(PhotoSize::getFileSize).reversed()).findFirst()
					.orElse(null).getWidth();
			int f_altura = fotos.stream().sorted(Comparator.comparing(PhotoSize::getFileSize).reversed()).findFirst()
					.orElse(null).getHeight();
			String caption = "arquivo_id: " + f_id + "\nlargura: " + Integer.toString(f_largura) + "\naltura: "
					+ Integer.toString(f_altura);
			System.out.println(f_id);
			SendPhoto msg1 = new SendPhoto().setChatId(chatId).setPhoto(f_id);
			SendPhoto msg = new SendPhoto().setChatId(chatId).setPhoto(f_id).setCaption(caption);
			try {
				execute(msg); // Call method to send the photo with caption
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getBotToken() {
		return "670362332:AAFFUi9h-tsglux3VqVn9TM2K7xD0Q7V65M";
	}

	public String getBotUsername() {
		return "iniciandoitobot";
	}
}
