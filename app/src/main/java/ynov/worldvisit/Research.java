package ynov.worldvisit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Research extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);
    }

    /**
     * Récupère une liste de personnes.
     * @return ArrayList<Personne>: ou autre type de données.
     * @author François http://www.francoiscolin.fr/
     */
    public static ArrayList<Country> getCountries() {

        ArrayList<Country> countries = new ArrayList<Country>();

        try {
            String myurl= "https://restcountries.eu/rest/v2/all";

            URL url = new URL(myurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            /*
             * InputStreamOperations est une classe complémentaire:
             * Elle contient une méthode InputStreamToString.
             */
            String result = InputStreamOperations.InputStreamToString(inputStream);

            // On récupère le JSON complet
            JSONObject jsonObject = new JSONObject(result);
            // On récupère le tableau d'objets qui nous concernent
            JSONArray array = new JSONArray(jsonObject.getString(""));
            // Pour tous les objets on récupère les infos
            for (int i = 0; i < array.length(); i++) {
                // On récupère un objet JSON du tableau
                JSONObject obj = new JSONObject(array.getString(i));
                // On fait le lien Personne - Objet JSON
                Country country = new Country();
                JSONObject names = new JSONObject(obj.getString("translations"));
                country.setName(names.getString("fr"));
                country.setCapital(obj.getString("capital"));
                country.setRegion(obj.getString("region"));
                String countryCode = obj.getString("alpha2Code");
                country.setFlag("http://www.geognos.com/api/en/countries/flag/"+countryCode+".png");

                // On ajoute le pays à la liste
                countries.add(country);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // On retourne la liste des pays
        return countries;

        /*
        *  Cette classe a été réimportée et adaptée afin de fonctionner dans le cas présent
        *  Link : http://tutorielandroid.francoiscolin.fr/recupjson.php
        */
    }
}
