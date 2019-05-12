import com.google.gson.Gson;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 10; i++) {
            Fotka obrazek = downloadObrazek();
            System.out.println("Obrazek " + (i + 1) + ": " + obrazek.url);
            BufferedImage image = ImageIO.read(new URL(obrazek.url));
 //           ImageIO.write("Obrazek","jpeg","C:\\Users\\wooki\\ProjectsSDA\\Obrazki pobrane")
        }
    }

    private static Fotka downloadObrazek() throws IOException {
        String link = "https://random.dog/woof.json";
        URL url = new URL(link);

        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Chrome");
        Scanner scanner = new Scanner(connection.getInputStream());

        String line = scanner.nextLine();

        Gson gson = new Gson();
        return gson.fromJson(line, Fotka.class);
    }
}