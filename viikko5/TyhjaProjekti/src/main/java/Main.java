import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import ohtu.Palautukset;
import ohtu.Palautus;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtil;
 
public class Main {
 
    public static void main(String[] args) throws IOException, HttpException {
        String studentNr = "13864550";
        if ( args.length>0) {
            studentNr = args[0];
        }
        // Muutos 1
        // Muutos 2
        String url = "http://ohtustats-2013.herokuapp.com/opiskelija/"+studentNr+".json";
 
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        URL urli = new URL(url);
        client.startSession(urli);
        client.executeMethod(method);
 
        InputStream stream =  method.getResponseBodyAsStream();
 
        String bodyText = IOUtil.toString(stream);
 
        System.out.println("json-muotoinen data:");
        System.out.println( bodyText );
 
        Gson mapper = new Gson();
        Palautukset palautukset = mapper.fromJson(bodyText, Palautukset.class);
 
        System.out.println("oliot:");
        for (Palautus palautus : palautukset.getPalautukset()) {
            System.out.println( palautus );
        }
 
    }
}
