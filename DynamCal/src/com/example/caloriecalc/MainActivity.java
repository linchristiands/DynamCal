package com.example.caloriecalc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.os.Bundle;


public class MainActivity extends ActionBarActivity {
	ExpandableListFood listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

       //set content view AFTER ABOVE sequence (to avoid crash)
        setContentView(R.layout.activity_main); 
        
        expListView = (ExpandableListView) findViewById(R.id.FoodListView);
        prepareListData();
        listAdapter = new ExpandableListFood(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
       
    }
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
 
        // Adding child data
        listDataHeader.add("Apéritifs");
        listDataHeader.add("Boissons");
        listDataHeader.add("Coquillages et crustacés");
        listDataHeader.add("Dessert et sucreries");
        listDataHeader.add("Féculents");
        listDataHeader.add("Fruits");
        listDataHeader.add("Légumes");
        listDataHeader.add("Matières grasses");
        listDataHeader.add("Pains/Céréales");
        listDataHeader.add("Poissons");
        listDataHeader.add("Produits laitiers");
        listDataHeader.add("Viandes");
        listDataHeader.add("Volailles");
 
        // Adding child data
        List<String> Boissons = new ArrayList<String>();
        Boissons.add("1 verre de jus de fruit");
        Boissons.add("Soda gazeux(33cl)");
        Boissons.add("Café");
        Boissons.add("Lait");
        Boissons.add("Vin");
        Boissons.add("Alcool type armagnac");
        Boissons.add("Bière Blonde(33cl)");
        Boissons.add("Bière Brune(15cl)");
        
        List<String> Cereales = new ArrayList<String>();
        Cereales.add("Biscotte");
        Cereales.add("1 bol de cornflakes");
        Cereales.add("Galette au riz soufflé");
        Cereales.add("Gressin");
        Cereales.add("Pain blanc(100g)");
        Cereales.add("Pain (1/4 de baguette)");
        Cereales.add("Pain complet(1 tranche de 40g)");
        Cereales.add("Pain de mie(1 tranchede 35g)");
        Cereales.add("Pain aux raisins");
        
        List<String> Fromages = new ArrayList<String>();
        Fromages.add("Camembert");
        Fromages.add("Cheddar");
        Fromages.add("Chesire");
        Fromages.add("Edam");
        Fromages.add("Feta");
        Fromages.add("Fontine");
        Fromages.add("Fromage américain");
        Fromages.add("Fromage à pate fraîche");
        Fromages.add("Monterey Jack");
        Fromages.add("Mozzarella");
        Fromages.add("Parmigiano Reggiano");
        Fromages.add("Port Salut");
        Fromages.add("Provolone piquant");
        Fromages.add("Roquefort");
        Fromages.add("Swiss cheese");
        Fromages.add("Tilsit");
        
        List<String> Fruits = new ArrayList<String>();
        Fruits.add("Abricot");
        Fruits.add("Ananas");
        Fruits.add("Avocat");
        Fruits.add("Banane");
        Fruits.add("Cerise");
        Fruits.add("Citron");
        Fruits.add("Clémentine");
        Fruits.add("Fraise");
        Fruits.add("Framboise");
        Fruits.add("Fruits de la passion");
        Fruits.add("Melon");
        Fruits.add("Myrtilles");
        Fruits.add("Orange");
        Fruits.add("Pêche");
        Fruits.add("Pastèque");
        Fruits.add("Poire");
        Fruits.add("Pomme");
        Fruits.add("Raisin");
        
        List<String> Legumes = new ArrayList<String>();
        Legumes.add("Artichaut");
        Legumes.add("Asperges");
        Legumes.add("Aubergine");
        Legumes.add("Blette");
        Legumes.add("Betterave rouge");
        Legumes.add("Brocolis");
        Legumes.add("Carde");
        Legumes.add("Carotte");
        Legumes.add("Céleri");
        Legumes.add("Cêpes");
        Legumes.add("Champignons");
        Legumes.add("Chanterelles");
        Legumes.add("Chou");
        Legumes.add("Chou brocolis");
        Legumes.add("Chou chinois");
        Legumes.add("Chou de Bruxelles");
        Legumes.add("Chou-fleur");
        Legumes.add("Chou-navet");
        Legumes.add("Chou-rave");
        Legumes.add("Choucroute");
        Legumes.add("Citrouille");
        Legumes.add("Coeur de palmier");
        Legumes.add("Concombre");
        Legumes.add("Cornichons");
        Legumes.add("Courge");
        Legumes.add("Courgette");
        Legumes.add("Cresson");
        Legumes.add("Echallotes"); 
        Legumes.add("Endives");
        Legumes.add("Epinards");
        Legumes.add("Fenouil");
        Legumes.add("Fève");
        Legumes.add("Flageolets");
        Legumes.add("Gingembre");
        Legumes.add("Girolles");
        Legumes.add("Haricot de Lima");
        Legumes.add("Haricot sec");
        Legumes.add("Haricot vert");
        Legumes.add("Laitue");
        Legumes.add("Lentilles");
        Legumes.add("Macédoine de légumes");
        Legumes.add("Mâche");
        Legumes.add("Maïs");
        Legumes.add("Manioc");
        Legumes.add("Morilles");
        Legumes.add("Navet");
        Legumes.add("Oignons");
        Legumes.add("Panais");
        Legumes.add("Patate douce");
        Legumes.add("Petits pois");
        Legumes.add("Poireau");
        Legumes.add("Pois chiche");
        Legumes.add("Poivron");
        Legumes.add("Pomme de terre");
        Legumes.add("Pomme de terre(Frites)");
        Legumes.add("Pomme de terre(Purée)");
        Legumes.add("Potiron");
        Legumes.add("Pousses de bambou");
        Legumes.add("Radis");
        Legumes.add("Rutabaga");
        Legumes.add("Salade verte");
        Legumes.add("Salsifis");
        Legumes.add("Scarole");
        Legumes.add("Truffe");
        
        List<String> Viandes = new ArrayList<String>();
        Viandes.add("Abats");
        Viandes.add("Agneau");
        Viandes.add("Aiguillette de boeuf");
        Viandes.add("Andouille");
        Viandes.add("Andouillette");
        Viandes.add("Bacon");
        Viandes.add("Bavette(Boeuf)");
        Viandes.add("Bifteck de Boeuf");
        Viandes.add("Bifteck de Cheval");
        Viandes.add("Boeuf (Steak haché)");
        Viandes.add("Caille");
        Viandes.add("Cerf");
        Viandes.add("Cervelas");
        Viandes.add("Côte de Boeuf");
        Viandes.add("Dinde");
        Viandes.add("Escalope de veau");
        Viandes.add("Foie gras");
        Viandes.add("Grenouille");
        Viandes.add("Hamburger");
        Viandes.add("Hot-dog");
        Viandes.add("Jambon cru");
        Viandes.add("Jambon blanc");
        Viandes.add("Jambon fumé");
        Viandes.add("Jambonneau");
        Viandes.add("Lapin");
        Viandes.add("Lard fumé");
        Viandes.add("Merguez");
        Viandes.add("Mouton");
        Viandes.add("Paté");
        Viandes.add("Porc");
        Viandes.add("Rosbif");
        Viandes.add("Salami");
        Viandes.add("Sanglier");
        Viandes.add("Saucisses");
        Viandes.add("Saucisson");
        Viandes.add("Travers de porc");
        Viandes.add("Veau");
        
        listDataChild.put(listDataHeader.get(1), Boissons); // Header, Child data
        listDataChild.put(listDataHeader.get(4), Cereales);
        listDataChild.put(listDataHeader.get(10), Fromages);
        listDataChild.put(listDataHeader.get(5), Fruits);
        listDataChild.put(listDataHeader.get(6), Legumes);
        listDataChild.put(listDataHeader.get(11), Viandes);
    } 

}
