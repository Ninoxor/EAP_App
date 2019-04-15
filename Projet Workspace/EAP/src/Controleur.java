import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controleur implements Initializable {
	
	
   @FXML private Button button;
   private Stage primaryStage;
   private static int idEntConnected;
   ObservableList<Salle> listSalles = FXCollections.observableArrayList(Modele.getAllSalle());
   ObservableList<EntrepriseCliente> listClients = FXCollections.observableArrayList(Modele.getAllClt());
   ObservableList<Location> listReservs = null;
   ObservableList<String> lesSalles = FXCollections.observableArrayList(this.SalleArraytoString());
   ObservableList<String> lesClients = FXCollections.observableArrayList(this.ClientArraytoString());
   ObservableList<String> etatsSalle = FXCollections.observableArrayList("Libre","R�serv�e");
   ObservableList<String> typesSalle = FXCollections.observableArrayList("Bureau","Salle de r�union");
   @FXML private ComboBox<String> boxSalles= new ComboBox<String>();
   @FXML private ComboBox<String> boxClients= new ComboBox<String>();
   @FXML private ComboBox<String> boxtypeSalle= new ComboBox<String>();
   @FXML private ComboBox<String> boxetatSalle= new ComboBox<String>();
   @FXML TextField fieldDateDebut;
   private static String dateDebut;
   @FXML TextField fieldDateFin;
   private static String dateFin;
   @FXML TextField FieldNomEnt;
   @FXML TextField fieldAdr;
   @FXML TextField fieldMail ;
   @FXML TextField fieldNomSalle;
   @FXML TextField fieldTel= new NumberField("test");
   @FXML Label labelTest = new Label();
   @FXML Label labelTotal;
   @FXML TableView listSalle = new TableView<>();
   @FXML TableColumn<Salle,String> col_name = new TableColumn<>("Salle");
  // @FXML TableColumn<Salle,String> col_type = new TableColumn<>("Type");
   @FXML TableColumn<Salle,Button> col_updt = new TableColumn<>("R�server");
   @FXML TableView listPlusClt;
   @FXML TableView listMoinsClt;
   @FXML TableView listPlusSalle;
   @FXML TableView listMoinsSalle;
   @FXML TableView listFacture;
   
   //Charge
   //@FXML Menu deco;
   @FXML private javafx.scene.control.Button closeButton;
	  
   public Controleur(){
	   primaryStage=Main.getPrimaryStage();  		   
   }
   @Override
   public void initialize(URL location, ResourceBundle resources) {
	   boxSalles.setItems(lesSalles);
	   boxClients.setItems(lesClients);
	   boxtypeSalle.setItems(typesSalle);
	   boxetatSalle.setItems(etatsSalle);
	   Controleur.NumberField(fieldTel);
	   
   }
   
   public void initTable(TableView table){
	   initCols(table);
	   
	   
   }
   public void initCols(TableView table){
	   //switch/if
	   col_name.setCellValueFactory(new PropertyValueFactory<Salle,String>("nom"));
	 //  col_type.setCellValueFactory(new PropertyValueFactory<Salle,String>("type"));
	   col_updt.setCellValueFactory(new PropertyValueFactory<Salle,Button>("reserver"));
	   setTable(table);
   }
   
   public void setTable(TableView table){
	   //switch/if
	   if(boxtypeSalle.getValue()=="Bureau"){
		   table.getColumns().clear();
		   table.setItems(FXCollections.observableArrayList(Modele.getAllBureauDispo()));
		   table.getColumns().addAll(col_name,col_updt);
	   }
	   else{
		   table.getColumns().clear();
		   table.setItems(FXCollections.observableArrayList(Modele.getAllSalleRDispo()));
		   table.getColumns().addAll(col_name,col_updt);
	   }
	   
   }

   public  ArrayList<String> SalleArraytoString(){
	   ArrayList<Salle> lesSalle = Modele.getAllSalle();
	   ArrayList<String> lesSalles = new ArrayList<String>();
	   for (int i=0; i<lesSalle.size();i++){
		   if(lesSalle.get(i).getNom()!=null){
			   lesSalles.add(lesSalle.get(i).getNom());
		   }
		  
	   }
	   if(lesSalles.isEmpty()){
		   System.out.println("Liste Salle vide");
	   }
	   return lesSalles;   
   }
   
   public ArrayList<String> ClientArraytoString(){
	   ArrayList<EntrepriseCliente> lesClient = Modele.getAllClt();
	   ArrayList<String> lesClients = new ArrayList<String>();
	   for (int i=0; i<lesClient.size();i++){
		   if(lesClient.get(i)!=null){
			   lesClients.add(lesClient.get(i).getNom());
		   }
	   }
	   if(lesClients.isEmpty()){
		   System.out.println("Liste Client vide");
	   }
	   return lesClients;   
   }


	// TODO Auto-generated method stub
   	public  void changeToAdministrateur() {
   		try {
   			// Read file fxml and draw interface.
   			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/Scene/BaseAdmin.fxml"));
	        Parent content = loader.load(); 
	         
	        primaryStage.setTitle("Espace Administrateur");
	        primaryStage.setScene(new Scene(content));
	        primaryStage.show();
	            
	    } catch(Exception err) {
	        err.printStackTrace();
	    }	      
   	}
   	
	// TODO Auto-generated method stub
   	public  void changeToClient(int idEnt) {
   		this.idEntConnected=idEnt;
   		System.out.println(this.idEntConnected);
   		try {
   			// Read file fxml and draw interface.
   			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/Scene/BaseClient.fxml"));
	        Parent content = loader.load(); 
	         
	        primaryStage.setTitle("Espace Client");
	        primaryStage.setScene(new Scene(content));
	        primaryStage.show();
	            
	    } catch(Exception err) {
	        err.printStackTrace();
	    }	      
   	}
   	
   	// TODO Auto-generated method stub
   	public  void changeToAjoutSalle() {
   		try {
   			// Read file fxml and draw interface.
   			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/Scene/AjouterUneSalle.fxml"));
	        Parent content = loader.load(); 
	         
	        primaryStage.setTitle("Espace Client");
	        primaryStage.setScene(new Scene(content));
	        primaryStage.show();
	            
	    } catch(Exception err) {
	        err.printStackTrace();
	    }	      
   	}
   	
   	// TODO Auto-generated method stub
   	public  void changeToAjoutClient() {
   		try {
   			// Read file fxml and draw interface.
   			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/Scene/AjouterUnClient.fxml"));
	        Parent content = loader.load(); 
	         
	        primaryStage.setTitle("Espace Client");
	        primaryStage.setScene(new Scene(content));
	        primaryStage.show();
	            
	    } catch(Exception err) {
	        err.printStackTrace();
	    }	      
   	}
   	
   	// TODO Auto-generated method stub
   	public  void changeToSuppSalle() {
   		try {
   			// Read file fxml and draw interface.
   			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/Scene/SupprimerUneSalle.fxml"));
	        Parent content = loader.load(); 
	         
	        primaryStage.setTitle("Espace Client");
	        primaryStage.setScene(new Scene(content));
	        primaryStage.show();
	            
	    } catch(Exception err) {
	        err.printStackTrace();
	    }	      
   	}
   	
   	// TODO Auto-generated method stub
   	public  void changeToSuppEClient() {
   		try {
   			// Read file fxml and draw interface.
   			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/Scene/SupprimerUnClient.fxml"));
	        Parent content = loader.load(); 
	         
	        primaryStage.setTitle("Espace Client");
	        primaryStage.setScene(new Scene(content));
	        primaryStage.show();
	            
	    } catch(Exception err) {
	        err.printStackTrace();
	    }	      
   	}
   	
   	// TODO Auto-generated method stub
   	public  void changeToModifSalle() {
   		try {
   			// Read file fxml and draw interface.
   			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/Scene/ModifierUneSalle.fxml"));
	        Parent content = loader.load(); 
	         
	        primaryStage.setTitle("Espace Client");
	        primaryStage.setScene(new Scene(content));
	        primaryStage.show();
	            
	    } catch(Exception err) {
	        err.printStackTrace();
	    }	      
   	}
   	
   	// TODO Auto-generated method stub
   	public  void changeToAjoutLocation() {
   		try {
   			// Read file fxml and draw interface.
   			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/Scene/Rechercher&Reserver.fxml"));
	        Parent content = loader.load(); 
	         
	        primaryStage.setTitle("Espace Client");
	        primaryStage.setScene(new Scene(content));
	        primaryStage.show();
	            
	    } catch(Exception err) {
	        err.printStackTrace();
	    }	      
   	}
   	
   	// TODO Auto-generated method stub
   	public  void changeToViewLocation() {
   		try {
   			// Read file fxml and draw interface.
   			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/Scene/RecapitulatifReservations.fxml"));
	        Parent content = loader.load(); 
	         
	        primaryStage.setTitle("Espace Client");
	        primaryStage.setScene(new Scene(content));
	        primaryStage.show();
	            
	    } catch(Exception err) {
	        err.printStackTrace();
	    }	      
   	}
   	
   	// TODO Auto-generated method stub
   	public  void changeToViewFacture() {
   		try {
   			// Read file fxml and draw interface.
   			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/Scene/Facture&Paiement.fxml"));
	        Parent content = loader.load(); 
	         
	        primaryStage.setTitle("Espace Client");
	        primaryStage.setScene(new Scene(content));
	        primaryStage.show();
	            
	    } catch(Exception err) {
	        err.printStackTrace();
	    }	      
   	}
   	
   	// TODO Auto-generated method stub
   	public  void changeToSuiviSalles() {
   		try {
   			// Read file fxml and draw interface.
   			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/Scene/SuiviDesSalles.fxml"));
	        Parent content = loader.load(); 
	         
	        primaryStage.setTitle("Espace Client");
	        primaryStage.setScene(new Scene(content));
	        primaryStage.show();
	            
	    } catch(Exception err) {
	        err.printStackTrace();
	    }	      
   	}
   	
   	// TODO Auto-generated method stub
   	public  void changeToSuiviClients() {
   		try {
   			// Read file fxml and draw interface.
   			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/Scene/SuiviDesClients.fxml"));
	        Parent content = loader.load(); 
	         
	        primaryStage.setTitle("Espace Client");
	        primaryStage.setScene(new Scene(content));
	        primaryStage.show();
	            
	    } catch(Exception err) {
	        err.printStackTrace();
	    }	      
   	}
   	
	// TODO Auto-generated method stub
   	public  void ajoutClient() {}
   	
   	// TODO Auto-generated method stub
   	public  void ajoutSalle() {}
   	
   	// TODO Auto-generated method stub
   	public  void updateEtatFacture() {}
   	
   	// TODO Auto-generated method stub
   	public  void updateSalle() {}
   	
   	// TODO Auto-generated method stub
   	public  void getFacture() {}
   	
   	// TODO Auto-generated method stub
   	public  void searchSalle() {initTable(listSalle); dateDebut=this.fieldDateDebut.getText(); dateFin=this.fieldDateFin.getText();}
   	
   	// TODO Auto-generated method stub
   	public  void suppClient() {}
   	
   	// TODO Auto-generated method stub
   	public  void suppSalle() {}
   	
   	// TODO Auto-generated method stub
   	public  void setSuiviSalles() {}
   	
	public static void NumberField(TextField field){
		//NumberField test= this;
		field.textProperty().addListener(new ChangeListener<String>() {
			   @Override
			   public void changed(ObservableValue<? extends String> observable, String oldValue, 
			       String newValue) {
			       if (!newValue.matches("\\d*")) {
			    	   field.setText(newValue.replaceAll("[^\\d]", ""));
			       }
			   }
		});
	}
	
	public void reservation(String nameSalle){
		Label labelTest=new Label();
		int idSalle = Modele.getIdSalle(nameSalle);
		
		if(dateDebut==null || dateFin==null || dateDebut.equals("") || dateFin.equals("")){
			try {
	   			// Read file fxml and draw interface.
	   			FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(getClass().getResource("/Scene/Rechercher&ReserverFailed.fxml"));
		        Parent content = loader.load(); 
		         
		        primaryStage.setTitle("Espace Client");
		        primaryStage.setScene(new Scene(content));
		        primaryStage.show();
		            
		    } catch(Exception err) {
		        err.printStackTrace();
		    }	   
		}else{
			System.out.println(idSalle+" "+idEntConnected+" "+
					dateDebut+" "+
					dateFin);
			Location loc = new Location(dateDebut, dateFin, idSalle, idEntConnected);
			Modele.addLocation(loc);
			Modele.updateSalle(nameSalle, "R�serv�");
			changeToAjoutLocation();
			System.out.println("SUCCESS ADDED");
		}
		
		
	}

   	
   	public void retour(ActionEvent event) {
		   String valeur=Main.getValue();
		   try {
		        switch (valeur)
	             	{
	           	  case "administrateur":
	           		 this.changeToAdministrateur();
	           	  break;
	           	  case "client":
	           		 this.changeToClient(idEntConnected);
	           	  break;
	           	  default:
	             }
	           
	        } catch(Exception err) {
	            err.printStackTrace();
	        }	     
		      
	   }
   	
   	public void disconnect(ActionEvent event) {
		   try {
	            // Read file fxml and draw interface.
	            primaryStage.setTitle("Connexion");
	            primaryStage.setScene(Main.getScene());
	            primaryStage.show();
	           
	        } catch(Exception err) {
	            err.printStackTrace();
	        }	   
	   }
   	
   	public void exit(ActionEvent event) {
		   try {
	           primaryStage.close();
	           
	        } catch(Exception err) {
	            err.printStackTrace();
	        }	    
   	}
   	
   	
   	
   	
}

