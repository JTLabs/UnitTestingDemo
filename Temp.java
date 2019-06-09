import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Temp {

    public static void main(String[] args) throws Exception {
        System.out.println(findPassword("https://afternoon-headland-69107.herokuapp.com"));
    }

    private static String findPassword(String streamingUrl) throws Exception {
        BufferedReader bufferedReader = makeGetRequest(streamingUrl);

        Map<Integer, Character> passwordBuilder = new HashMap<>();
        Integer passwordIndex = Integer.valueOf(bufferedReader.readLine());
        while (!passwordBuilder.containsKey(passwordIndex)) {
            String passwordPos = bufferedReader.readLine();
            Integer row = findCol(passwordPos);
            Integer col = findRow(passwordPos);
            List<String> chunk = readChunk(bufferedReader);
            char passwordChar = findPasswordChar(chunk, row, col);
            passwordBuilder.put(passwordIndex, passwordChar);
            passwordIndex = Integer.valueOf(bufferedReader.readLine());
        }
        // this means we have found the complete password.
        return buildPassword(passwordBuilder);
    }

    private static char findPasswordChar(List<String> chunk, Integer row, Integer col) {
        int size = chunk.size();
        return chunk.get(size - row - 1)
                    .charAt(col);
    }

    private static List<String> readChunk(BufferedReader bufferedReader) throws IOException {
        List<String> chunk = new ArrayList<>();
        String line = bufferedReader.readLine();
        while (line != null) {
            if (line.isEmpty()) {
                break;
            }
            chunk.add(line);
            line = bufferedReader.readLine();

        }
        return chunk;
    }

    private static Integer findCol(String passwordPos) {
        int commaIndex = passwordPos.indexOf(",");
        return Integer.valueOf(passwordPos.substring(commaIndex + 2, passwordPos.length() - 1));
    }

    private static Integer findRow(String passwordPos) {
        int commaIndex = passwordPos.indexOf(",");
        return Integer.valueOf(passwordPos.substring(1, commaIndex));
    }

    private static String buildPassword(Map<Integer, Character> map) {
        StringBuilder passwordBuilder = new StringBuilder();

        int index = 0;
        for (int i = 0; i < map.size(); i++) {
            passwordBuilder.append(String.valueOf(map.get(index)));
            index++;
        }

        return passwordBuilder.toString();
    }

    private static BufferedReader makeGetRequest(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        return new BufferedReader(new InputStreamReader(conn.getInputStream()));
    }
}
