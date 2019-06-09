import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class Temp2 {

    private static final String ENDPOINT1 = "";
    private static final String ENDPOINT2 = "";
    private static String gameId;

    public static void main(String[] args) throws IOException {
        System.out.println(findPassword());
    }

    private static String findPassword() throws IOException {
        List<String> board = startGame();
        List<String> posse = findPosse(board);

        JsonObject newResponse = sendPosse(posse);
        while (!newResponse.has("password")) {
            board = getListFromJsonArray(newResponse.get("board").getAsJsonArray());
            posse = findPosse(board);
            newResponse = sendPosse(posse);
        }

        return newResponse.get("password").getAsString();
    }

    private static JsonObject sendPosse(List<String> posse) throws IOException {
        return makePostCall(ENDPOINT2 + gameId, posse);
    }

    private static JsonObject makePostCall(String endpoint, List<String> posse) throws IOException {
        URL url = new URL(endpoint);
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("posse", posse.toString());

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(param.getKey());;
            postData.append('=');
            postData.append(param.getValue());
        }
        byte[] postDataBytes = postData.toString().getBytes();

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        final InputStreamReader reader = new InputStreamReader(conn.getInputStream());
        BufferedReader in = new BufferedReader(reader);

        StringBuilder builder = new StringBuilder();
        String line = in.readLine();
        while (line != null || !line.isEmpty()) {
            builder.append(line);
            line = in.readLine();
        }
        JsonParser parser = new JsonParser();
        return (JsonObject) parser.parse(builder.toString());
    }

    private static List<String> findPosse(List<String> board) {
        List<String> posse = new ArrayList<>();
        for (int i = 0; i < board.size(); i++) {
            for (int j = i+1; j < board.size(); j++) {
                for (int k = j+1; k < board.size(); k++) {
                    String card1= board.get(i);
                    String card2= board.get(j);
                    String card3= board.get(k);
                    if (threeBePosse(card1, card2, card3)) {
                        posse.add(card1);
                        posse.add(card2);
                        posse.add(card3);
                        return posse;
                    }
                }
            }
        }
        return null;
    }

    private static boolean threeBePosse(String card1, String card2, String card3) {
        Integer length1 = card1.length();
        Integer length2 = card2.length();
        Integer length3 = card3.length();

        Character prefix1 = card1.charAt(0);
        Character prefix2 = card2.charAt(0);
        Character prefix3 = card3.charAt(0);

        Integer asciiLetter1 = (int) deAccent(card1.substring(1, 2)).charAt(0) + 32;
        Integer asciiLetter2 = (int) deAccent(card2.substring(1, 2)).charAt(0) + 32;
        Integer asciiLetter3 = (int) deAccent(card3.substring(1, 2)).charAt(0) + 32;

        CASE case1 = getCase(card1);
        CASE case2 = getCase(card2);
        CASE case3 = getCase(card3);

        boolean match1 = findMatches(length1, length2, length3);
        boolean match2 = findMatches(prefix1, prefix2, prefix3);
        boolean match3 = findMatches(asciiLetter1, asciiLetter2, asciiLetter3);
        boolean match4 = findMatches(case1, case2, case3);

        return match1 && match2 && match3 && match4;
    }

    private static boolean findMatches(Object obj1, Object obj2, Object obj3) {
        if ((obj1.equals(obj2) && obj1.equals(obj3)) || (!Objects.equals(obj1, obj2) && !Objects
            .equals(obj1, obj3) && !Objects.equals(obj2, obj3))) {
            return true;
        }
        return false;
    }

    private static CASE getCase(String card) {
        if (deAccent(card.substring(1, 2)).equals(card.substring(1, 2))) {
            int ascii = (int) card.charAt(1);
            if (ascii < 92) {
                return CASE.LOWER;
            }
            return CASE.UPPER;
        }
        return CASE.ACCENT;
    }

    enum CASE {
        LOWER,
        UPPER,
        ACCENT
    }

    private static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    private static List<String> startGame() throws IOException {
        JsonObject jsonObject = makePostCall(ENDPOINT1);
        gameId = jsonObject.get("id").getAsString();
        JsonArray boardArray = jsonObject.get("board").getAsJsonArray();
        return getListFromJsonArray(boardArray);
    }

    private static List<String> getListFromJsonArray(JsonArray boardArray) {
        List<String> list = new ArrayList<String>();
        if (boardArray != null) {
            int len = boardArray.size();
            for (int i = 0; i < len; i++) {
                list.add(boardArray.get(i).toString());
            }
        }

        return list;
    }

    private static JsonObject makePostCall(String endpoint) throws IOException {
        URL url = new URL(endpoint);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        StringBuilder builder = new StringBuilder();
        String line = in.readLine();
        while (line != null && !line.isEmpty()) {
            builder.append(line);
            line = in.readLine();
        }
        JsonParser parser = new JsonParser();
        return (JsonObject) parser.parse(builder.toString());
    }
}
