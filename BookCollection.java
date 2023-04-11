import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class BookCollection extends Book {
    public ArrayList<Book> books = new ArrayList<Book>();
   public List<Book> booksPopularity;

    //2, complete constructor that takes a string path (the BookList file name) load the books from BookList into the books arrayList
    //When complete books should have 100 items. Make sure you don't include the header row!

    BookCollection(String path) throws IOException {
        super();
        new File(path);

        String lineReading;

        BufferedReader br = new BufferedReader(new FileReader(path));

        int n = 0;

        try {
            while ((lineReading = br.readLine()) != null) {

                if (n > 0) {
                    lineReading = br.readLine();
                    String[] p_data = lineReading.split(",");

                    Book object = new Book(p_data[0], p_data[1], Long.parseLong(p_data[2]), Integer.parseInt(p_data[3]), Integer.parseInt(p_data[4]), Integer.parseInt(p_data[5]));
                    books.add(object);
                }
                n++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //3, Return a HashSet of all the authors in the book list//// It works
    public HashSet<String> getAuthors(){
        HashSet<String> booksAuthor = new HashSet<>();
        for(int i=0; i<books.size(); i++){
            booksAuthor.add(books.get(i).getAuthor());
        }
        return booksAuthor;
    }

    //4, return an arrayList of books with more than 750 pages
    public ArrayList<Book> getLongBooks(){
        ArrayList<Book> listOfBookPages= new ArrayList<>();
        for(int i=0; i<books.size(); i++){
            if(books.get(i).getPages() > 750){
                listOfBookPages.add(books.get(i));
            }
        }
        return listOfBookPages;
    }

    //5, return the book if the given title is in the list.
    public Book getBookByTitle(String title){
        for(int i=0; i<books.size(); i++){
            if(title == books.get(i).getTitle()){
                return books.get(i);
            }
        }
        return null;
    }

    //6, return an array of the 10 most popular books (That is those that currently have most copies on loan)
    public Stream<Book> mostPopular(){
        ArrayList<Book> booksPopularity = new ArrayList<>();

        for(int i=0;i<books.size(); i++){
            if(books.get(i).getCopiesOnLoan() != 0){
                booksPopularity.add(books.get(i));
            }
        }

        for(int i=0;i<booksPopularity.size(); i++){
            if((booksPopularity.get(i).getCopiesOnLoan()/booksPopularity.get(i).getCopiesInCollection()) >= 1){
                booksPopularity.add(books.get(i));
            }else{
                booksPopularity.remove(i);
            }
        }
        sort(booksPopularity);

        for(int i=0; i< 10;i++){
            System.out.println(booksPopularity.get(i).getTitle());
        }
        return booksPopularity.stream().limit(10);
    }

    public static void sort(ArrayList<Book> list){
        list.sort(BookCollection::compareTo);
    }
    public static int compareTo(Book o1, Book o2) {
        if(o1.getCopiesInCollection() > o2.getCopiesInCollection()){
            return 1;
        }else if(o1.getCopiesInCollection() < o2.getCopiesInCollection()){
            return -1;
        }else{
            return 0;
        }
    }
    
}
