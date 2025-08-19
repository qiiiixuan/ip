import java.util.*;

public class Jackie {
    public static void main(String[] args) throws InterruptedException {
        String logo = """
                 _____           _
                |__ __|__   ___ | | __ *  ___
                  | |/ _  |/ ,_\\| |/ /| |/ _ \\
                ,_| | |_| |  |_ |   < | |  __/
                \\___/\\__,_|\\___/|_|\\_\\|_|\\___/
                """;
        Scanner sc = new Scanner(System.in);
        String input = "";
        System.out.println("Welcome! I am...\n" + logo + "______________________________");
        System.out.println("Ask me anything!\n");
        input = sc.nextLine();
        while(!Objects.equals(input, "bye")) {
            System.out.println("\t" + input + "\n");
            input = sc.nextLine();
        }
        System.out.println("______________________________\nGoodbye!");
    }
}
