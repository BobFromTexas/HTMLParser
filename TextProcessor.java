import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to access links in URL
 */
public class TextProcessor {
    // member variables
    // URL address to be the source of the operation
    String url;
    final String webPrefix  = "https://www.thelatinlibrary.com/"; // needed to append to links
    ArrayList<String> bookLinks = new ArrayList<>(); // to store individual links for iteration
    ProcessBookLinks processBooks = new ProcessBookLinks(); // access process books class

    /**
     * Uses URL as argument which is passed from GUI action event method
     * @param url
     */
    public TextProcessor(String url){
        this.url = url;
    }

    /**
     * Processes URL to generate operable links
     * Uses a try catch to deal with invalid entries
     */
    public void createLinks() {
            try {
                // grab the full webpage from URL assigned to html
                Document webPage;
                webPage = Jsoup.connect(url).get();
                // removing elements which are not required
                // and adding running them through a Jsoup array
                // finally the links are added to the book links ArrayList
                Elements links = webPage.getElementsByTag("a");
                for (Element link : links) {
                    String linkHref = link.attr("href");
                    bookLinks.add(webPrefix.concat(linkHref));
                    links.empty();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    /**
     * To test createLinks()
     */
    public void displayLinks(){
        System.out.println("Displaying book links");
        for(int i = 0; i < bookLinks.size(); i++)
            System.out.println(bookLinks.get(i));
    }

    /**
     * Processes links generated from createLinks()
     */
    public void processLinks() {
        System.out.println("Processed text from links");
        for (int i = 0; i < bookLinks.size() - 3; i++) {
            processBooks.runBooks(bookLinks.get(i));
        }
    }
}