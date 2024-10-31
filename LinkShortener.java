package LinkShortener;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LinkShortener {
    private static final Map<String, String> urlMap = new HashMap<>();
    private static final String BASE_URL = "http://";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Shorten URL 2. Retrieve URL 3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter the long URL:");
                    String longUrl = scanner.nextLine();
                    String shortUrl = shortenUrl(longUrl);
                    System.out.println("Short URL: " + shortUrl);
                    break;
                case 2:
                    System.out.println("Enter the short URL:");
                    String shortUrlInput = scanner.nextLine();
                    String originalUrl = retrieveUrl(shortUrlInput);
                    if (originalUrl != null) {
                        System.out.println("Original URL: " + originalUrl);
                    } else {
                        System.out.println("URL not found.");
                    }
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static String shortenUrl(String longUrl) {
        String shortUrl = BASE_URL + longUrl.hashCode();
        urlMap.put(shortUrl, longUrl);
        return shortUrl;
    }

    private static String retrieveUrl(String shortUrl) {
        return urlMap.get(shortUrl);
    }
}
