package resource;

public class addBook {

    public static String addNewBook(String name, String author, String isbn, String aisle){

        String newBook = "{\n" +
                "\n" +
                "\"name\":\""+name+"\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\""+author+"\"\n" +
                "}";
        return newBook;
    }
}
