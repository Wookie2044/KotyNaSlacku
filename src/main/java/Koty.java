
import com.google.gson.Gson;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

class App {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; i++) {
            FotkaKotka fotkaKotka = pobierzFotke();
            System.out.println(fotkaKotka.file);

            int lastDot = fotkaKotka.file.lastIndexOf('.');
            String extension = fotkaKotka.file.substring(lastDot+1);

            BufferedImage image = ImageIO.read(new URL(fotkaKotka.file));
            System.out.println("Rozdzielczość: " + image.getWidth() + "x" + image.getHeight());
            File file = new File("kotek" + i + "." + extension);
            ImageIO.write(image, extension, file);
            System.out.println("Rozmiar: " + file.length()/1024 + " KB");
        }
    }

    private static FotkaKotka pobierzFotke() throws IOException {
        String link = "https://aws.random.cat/meow";
        URL url = new URL(link);

        URLConnection connection = url.openConnection();
        Scanner scanner = new Scanner(connection.getInputStream());

        String jsonText = scanner.nextLine();

        Gson gson = new Gson();
        return gson.fromJson(jsonText, FotkaKotka.class);
    }
}

