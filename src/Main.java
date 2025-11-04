//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Database database = null;

        if(database.insert("Udon",10,1))
            System.out.printf("Piatto inserito con successo");

        if(database.Update("Udon",11,1))
            System.out.printf("Piatto aggiornato con successo");

        if(database.delete("Udon"))
            System.out.printf("Piatto cancellato con successo");

        System.out.printf(database.selectAll());
    }
}