import java.io.File;

public class CaptureTheFlag{

    public static void main(String[] args){
        File   folder = new File("img/flags/");
        File[] images = folder.listFiles();
        for (File image : images){
            System.out.println(image.getName());
        }
        GameGui game = new GameGui();
    }

    // getNames(){
    //     File   folder = new File("images/");
    //     File[] images = folder.listFiles();
    // }
}
