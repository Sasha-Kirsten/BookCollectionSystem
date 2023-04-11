import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BookMain {
    public static void main(String[] args) throws IOException {
        BookCollection bCollection = new BookCollection("BookList.csv");

    //    for(int i=0; i<bCollection.getLongBooks().size(); i++){
    //        System.out.println(bCollection.getLongBooks().get(i).getTitle());
    //    }
        bCollection.mostPopular();
    }
}
