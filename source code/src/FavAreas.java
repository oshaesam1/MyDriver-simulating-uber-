import java.util.ArrayList;


public class FavAreas {
    private ArrayList<String>favAreas;

    public FavAreas(){
        favAreas= new ArrayList<String>();
    }
    public void addFavArea(String area){
        favAreas.add(area);
    }
    public ArrayList<String> returnAllAreas(){
        System.out.println("********Areas*******");
        for(int i=0;i<favAreas.size();i++)
            System.out.println(favAreas.get(i)+" ");
        return favAreas;
    }
}
