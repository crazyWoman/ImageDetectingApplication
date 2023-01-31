import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTest {
  public static void main(String[] args) throws IOException {
    Path path = Paths.get("C:\\ClientHealth\\living.PNG");
    InputStream targetStream = new FileInputStream(path.toFile());
    System.out.println(targetStream.readAllBytes());
  }
}
