package kata6;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.joining;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Kata6 {

    public static void main(String[] args) throws MalformedURLException, IOException, JAXBException {
        
        URL url = new URL("https://dinosaur-facts-api.shultzlab.com/dinosaurs");
        String json = read(url);
        Gson gson = new Gson();
        JsonElement element = gson.fromJson(json, JsonElement.class);
        JsonArray jsonArray = element.getAsJsonArray();
        List<Dinosaur> dinosaurs = new ArrayList<>();
         
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObj = jsonArray.get(i).getAsJsonObject();
            deserializarObjeto(jsonObj, dinosaurs);
        }
        
        System.out.println(dinosaurs.get(0).toString());
        
        System.out.println("---------XML---------");
        
        for (Dinosaur dinosaur: dinosaurs) {
            serializarObjeto(dinosaur);
        }
    }

    private static String read(URL url) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            return reader.lines().collect(joining());
        }
    }

    private static void serializarObjeto(Dinosaur dinosaur) throws JAXBException {
        try {
            JAXBContext context = JAXBContext.newInstance(Dinosaur.class);
            Marshaller marshall = context.createMarshaller();
            marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshall.marshal(dinosaur, System.out);
        }
        catch (JAXBException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deserializarObjeto(JsonObject jsonObj, List<Dinosaur> dinosaurs) {
        String name = jsonObj.get("Name").getAsString();
        String description = jsonObj.get("Description").getAsString();
        
        Dinosaur dinosaur = new Dinosaur(name, description);
        dinosaurs.add(dinosaur);
    }
}
