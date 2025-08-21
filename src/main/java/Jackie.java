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
        ArrayList<String> history = new ArrayList<>(List.of());
        String input = "";
        System.out.println("Welcome! I am...\n" + logo + "______________________________");
        System.out.println("Ask me anything!\n");
        input = sc.nextLine();
        while(!Objects.equals(input, "bye")) {
            if (Objects.equals(input, "list")) {
                for (int i = 0; i < history.size(); i++) {
                    System.out.println("\t" + (i + 1) + ". " + history.get(i));
                }
                System.out.println();
            } else {
                history.add(input);
                System.out.println("\tadded: " + input + "\n");
            }
            input = sc.nextLine();
        }
        System.out.println("______________________________\nGoodbye!");
    }
}
