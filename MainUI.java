/*************************************************
 * FALL 2022
 * EECS 3311 GUI SAMPLE CODE
 * ONLT AS A REFERENCE TO SEE THE USE OF THE jFree FRAMEWORK
 * THE CODE BELOW DOES NOT DEPICT THE DESIGN TO BE FOLLOWED 
 */

package Viewer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;

import com.google.gson.JsonObject;

import model.Analysis.AnalysisDistributor;
import model.Analysis.BuildMortality;
import model.Analysis.BuildMortalityVSExpensesVSHospitalbeds;
import model.Analysis.BuildMortalityVSGDP;
import model.Analysis.BuildMortalityVsExpenses;
import model.Analysis.BuildUnemploymentAnnualChange;
import model.Analysis.BuildUnemploymentVSGDPRatio;
import model.Analysis.BuilderUnemploymentVsExpenses;
import model.Analysis.BuilderUnemploymentVsMortality;
import model.Analysis.Analysis;
import model.Analysis.AnalysisBuilder;
import model.fetcher.CSVToolsFacade;
import model.fetcher.DataFetcher;
import model.system.DataBase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainUI extends JFrame {
	
	private static final long serialVersionUID = 1838673268268596418L;
	private static MainUI instance;
	private static JButton recalculate;
	private Vector<String> countriesNames;
	public JComboBox<String> countriesList;
	private JButton addToList;
	private JButton removeFromList;
	private JTextField input;
	private Map<String, String> countries;
	static DataFetcher sy;
	private JComboBox<String> fromList;
	private JComboBox<String> toList;
	private Vector<String> years;
	private JComboBox<String> methodsList;
	private Vector<String> methodsNames;
	private JButton addView;
    private JButton removeView;
    private JComboBox<String> viewsList;
    private static AddRemoveMachine machine;
    private AddRemoveState addViewer;
    AddRemoveState RemoveViewer;
	
	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();
		machine = new AddRemoveMachine();
		return instance;
	}

	private MainUI() {
		// Set window title
		super("Country Statistics");
		sy = new DataFetcher();

		// Set top bar
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		JLabel updateCountryLabel = new JLabel("add/remove a country: ");
		this.countriesNames = new Vector<String>();
		countriesNames.add("United States of America");
		countriesNames.add("Canada");
		countriesNames.add("France");
		countriesNames.add("China");
		countriesNames.add("Brazil");
		countriesNames.sort(null);
		this.countriesList = new JComboBox<String>(countriesNames);

		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		years = new Vector<String>();
		for (int i = 2021; i >= 2010; i--) {
			years.add("" + i);
		}
		fromList = new JComboBox<String>(years);
		toList = new JComboBox<String>(years);
		this.addToList = new JButton("add");
		this.removeFromList = new JButton("remove");
		this.input = new JTextField(10);
		
		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(this.countriesList);
		north.add(this.removeFromList);
		north.add(from);
		north.add(fromList);
		north.add(to);
		north.add(toList);
		north.add(updateCountryLabel);
		north.add(input);
		north.add(this.addToList);
	
		// Set bottom bar
		MainUI.recalculate = new JButton("Recalculate");

		JLabel viewsLabel = new JLabel("Available Views: ");

		 Vector<String> viewsNames = new Vector<String>();
		viewsNames.add("Pie Chart");
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Scatter Chart");
		viewsNames.add("timeseries chart");
		viewsNames.add("Report");
		this.viewsList = new JComboBox<String>(viewsNames);
		this.addView = new JButton("+");
		this.removeView = new JButton("-");

		JLabel methodLabel = new JLabel("        Choose analysis method: ");

		methodsNames = new Vector<String>();
		methodsNames.add("Mortality");
		methodsNames.add("Mortality vs Expenses");
		methodsNames.add("Mortality vs Expenses & Hospital Beds");
		methodsNames.add("Mortality vs GDP");
		methodsNames.add("Unemployment vs GDP ratio(%)");
		methodsNames.add("Unemployment AnnualChange");
		methodsNames.add("Unemployment vs Expenses");
		methodsNames.add("Unemployment vs Mortality");

		methodsList = new JComboBox<String>(methodsNames);

		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(this.addView);
		south.add(this.removeView);

		south.add(methodLabel);
		south.add(methodsList);
		south.add(MainUI.recalculate);
		  
		addToList(this.input,"NY.GDP.PCAP.CD",this.countriesList);
		removeFromList(this.countriesList);
		

		JPanel east = new JPanel();

		// Set charts region
		JPanel west = new JPanel();
		west.setLayout(new GridLayout(2, 0));
		this.recalculate(west);
		this.removeView(west);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);
		// to get iso list
		countries = new HashMap<>();
		for (String iso : Locale.getISOCountries()) {
			Locale l = new Locale("", iso);
			countries.put(l.getDisplayCountry(), iso);
	     }     
		countries.put("United States of America", "USA");
	}
	
		public Vector<String> getAnalysisMethod() {
			return this.methodsNames;
		}
		public JComboBox<String> getmethodList(){
			return this.methodsList;
			
		}
		public JComboBox<String> getViewList(){
			return this.viewsList;
			
		}
		public Vector<String> getYears() {
			return this.years;
		}
		public JComboBox<String> fromList(){
			return this.fromList;	
		}
		public JComboBox<String> toList(){
			return this.toList;	
		}
	//state pattern for Add/Remove Viewer
	//implementation for the add views
	//we will fetch result from the recalculate method query() change to recalculate()
	private void addView(JPanel west,Analysis data) {
		addViewer = new AddViewer(west, data);
	    ActionListener addListener = new ActionListener() {//add actionlistner to listen for change          
	    @Override
	    public void actionPerformed(ActionEvent e) { 
	    machine.setState(addViewer);
	    machine.performState();
	   }
	  };
	    this.addView.addActionListener(addListener);
	}
	
	//implementation for the remove views
	public void removeView(JPanel west) {
		RemoveViewer = new RemoveViewer(west);
	    ActionListener removeListener = new ActionListener() {//remove actionlistner to listen for change
	        @Override
	        public void actionPerformed(ActionEvent e) {  
	        	machine.setState(RemoveViewer);
	     	    machine.performState();
	           
	           }
	          };
	        this.removeView.addActionListener(removeListener);
	}
	//fetch data based on the users' inputs
	//changed query to recalculate
	private void recalculate(JPanel west) {
	
		 ActionListener recalculate = new ActionListener() {//add actionlistner to listen for change          
		 @Override
		 public void actionPerformed(ActionEvent e) { 
		String country = (String) countriesList.getSelectedItem();
		String analysisMethod = methodsNames.get(methodsList.getSelectedIndex());
		AnalysisDistributor dis = new AnalysisDistributor();
		ArrayList<JsonObject> arrayOfResults = new ArrayList<JsonObject>();
		JsonObject r1;
		JsonObject r2;
		JsonObject r3;
		Analysis analysisResult;
		 
		switch (analysisMethod) {
		       case "Mortality": 
		           Integer start = Integer.valueOf(years.get(fromList.getSelectedIndex()));
		           Integer end = Integer.valueOf(years.get(toList.getSelectedIndex()));
		           r1 = sy.selection(countries.get(country),"SP.DYN.IMRT.IN", start.toString(), end.toString());
		           arrayOfResults.add(r1);
		           AnalysisBuilder Mortality = new BuildMortality();
		           dis.setAnalysisBuilder(Mortality);
		           dis.constructAnalysis(1,arrayOfResults);
		           analysisResult = dis.getAnalysis();   
		           System.out.println(analysisResult.getResult());
		           addView(west, analysisResult);
		           break;

		  
		       case "Mortality vs Expenses":  
		           r1 = sy.selection(countries.get(country),"SP.DYN.IMRT.IN", years.get(fromList.getSelectedIndex()), years.get(toList.getSelectedIndex()));
		           r2 = sy.selection(countries.get(country),"SH.XPD.CHEX.GD.ZS", years.get(fromList.getSelectedIndex()), years.get(toList.getSelectedIndex()));
		           arrayOfResults.add(r1);
		           arrayOfResults.add(r2);
		           AnalysisBuilder mortalityVsExpenses = new BuildMortalityVsExpenses();
		           dis.setAnalysisBuilder(mortalityVsExpenses);
		           dis.constructAnalysis(2, arrayOfResults);
		           analysisResult = dis.getAnalysis();   
		           System.out.println(analysisResult.getResult());
		           addView(west, analysisResult);
		           break;
		          
		       case "Mortality vs Expenses & Hospital Beds": 
		           r1 = sy.selection(countries.get(country),"SP.DYN.IMRT.IN", years.get(fromList.getSelectedIndex()), years.get(toList.getSelectedIndex()));
		           r2 = sy.selection(countries.get(country),"SH.XPD.CHEX.GD.ZS", years.get(fromList.getSelectedIndex()), years.get(toList.getSelectedIndex()));
		           r3 = sy.selection(countries.get(country),"SH.MED.BEDS.ZS", years.get(fromList.getSelectedIndex()), years.get(toList.getSelectedIndex()));
		           arrayOfResults.add(r1);
		           arrayOfResults.add(r2);
		           arrayOfResults.add(r3);
		           AnalysisBuilder MEH = new BuildMortalityVSExpensesVSHospitalbeds();
		           dis.setAnalysisBuilder(MEH);
		           dis.constructAnalysis(3,arrayOfResults);
		           analysisResult = dis.getAnalysis();
		           System.out.println(analysisResult.getResult());
		           addView(west, analysisResult);
		           break;
		       case "Mortality vs GDP": 
		           r1 = sy.selection(countries.get(country),"SP.DYN.IMRT.IN", years.get(fromList.getSelectedIndex()), years.get(toList.getSelectedIndex()));
		           r2 = sy.selection(countries.get(country),"NY.GDP.PCAP.CD", years.get(fromList.getSelectedIndex()), years.get(toList.getSelectedIndex()));
		           arrayOfResults.add(r1);
		           arrayOfResults.add(r2);
		           AnalysisBuilder mortalityVsGDP = new BuildMortalityVSGDP();
		           dis.setAnalysisBuilder(mortalityVsGDP);
		           dis.constructAnalysis(2, arrayOfResults);
		           analysisResult = dis.getAnalysis();   
		           System.out.println(analysisResult.getResult());
		           addView(west, analysisResult);
		           break;
		          
		       case "Unemployment vs GDP ratio(%)":  
		           r1 = sy.selection(countries.get(country),"SL.UEM.TOTL.ZS", years.get(fromList.getSelectedIndex()), years.get(toList.getSelectedIndex()));
		           r2 = sy.selection(countries.get(country),"NY.GDP.PCAP.CD", years.get(fromList.getSelectedIndex()), years.get(toList.getSelectedIndex()));
		           arrayOfResults.add(r1);
		           arrayOfResults.add(r2);
		           AnalysisBuilder unemploymentVSGDP = new BuildUnemploymentVSGDPRatio();
		           dis.setAnalysisBuilder(unemploymentVSGDP);
		           dis.constructAnalysis(2, arrayOfResults);
		           analysisResult = dis.getAnalysis();   
		           System.out.println(analysisResult.getResult());
		           addView(west, analysisResult);
		           break;	
		           
		       case "Unemployment AnnualChange":  
		           r1 = sy.selection(countries.get(country),"SL.UEM.TOTL.ZS", years.get(fromList.getSelectedIndex()), years.get(toList.getSelectedIndex()));
		           arrayOfResults.add(r1); 
		           AnalysisBuilder unemployment = new BuildUnemploymentAnnualChange();
		           dis.setAnalysisBuilder(unemployment);
		           dis.constructAnalysis(1, arrayOfResults);
		           analysisResult = dis.getAnalysis();		          
		           System.out.println(analysisResult.getResult());
		           addView(west, analysisResult);
		           break;
		          
		       case "Unemployment vs Expenses":
		           r1 = sy.selection(countries.get(country),"SL.UEM.TOTL.ZS", years.get(fromList.getSelectedIndex()), years.get(toList.getSelectedIndex()));
		           r2 = sy.selection(countries.get(country),"SH.XPD.CHEX.GD.ZS", years.get(fromList.getSelectedIndex()), years.get(toList.getSelectedIndex()));
		           arrayOfResults.add(r1);
		           arrayOfResults.add(r2);
		           AnalysisBuilder UnemploymentVsExpenses = new BuilderUnemploymentVsExpenses();
		           dis.setAnalysisBuilder(UnemploymentVsExpenses);
		           dis.constructAnalysis(2, arrayOfResults);
		           analysisResult = dis.getAnalysis(); 
		           addView(west, analysisResult);
		           System.out.println(analysisResult.getResult());
		           break;
		           
		       case "Unemployment vs Mortality":
		           r1 = sy.selection(countries.get(country),"SL.UEM.TOTL.ZS", years.get(fromList.getSelectedIndex()), years.get(toList.getSelectedIndex()));
		           r2 = sy.selection(countries.get(country),"SP.DYN.AMRT.MA", years.get(fromList.getSelectedIndex()), years.get(toList.getSelectedIndex()));
		           arrayOfResults.add(r1);
		           arrayOfResults.add(r2);
		           AnalysisBuilder UnemploymentVsMortality = new BuilderUnemploymentVsMortality();
		           dis.setAnalysisBuilder(UnemploymentVsMortality);
		           dis.constructAnalysis(2, arrayOfResults);
		           analysisResult = dis.getAnalysis(); 
		           addView(west, analysisResult);
		           System.out.println(analysisResult.getResult());
		           break;
		           
		           
		       default: System.out.println("No other analysis method to construct");
		                break;
		                
		   		}
		 	

		 	}
		 };		  
    MainUI.recalculate.addActionListener(recalculate);
}
//add to the list of Countries available to fetch data from
public void addToList(JTextField input, String Indicator,JComboBox<String> j) {
	DataBase s = DataFetcher.getDataBase();
	ActionListener addActionListener = new ActionListener() {//add actionlistner to listen for change
		@Override
		public void actionPerformed(ActionEvent e) { 
			if(s.getcountriesNotAllowed() != null) {
				s.removecountriesNotAllowed(input.getText());
				try {
					CSVToolsFacade.updateCSV();;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			 //always pass iso2 country for query purposes
			if(s.check(countries.get(input.getText()),Indicator, years.get(fromList.getSelectedIndex()), years.get(toList.getSelectedIndex())) == true) {
				System.out.println("the country can be fetched");
				j.addItem(input.getText());
			} 
		}
	  };
	  this.addToList.addActionListener(addActionListener);
		
}
//remove a country from the list of available countries to fetch data from
public void removeFromList(JComboBox<String> c) {
	DataBase s = DataFetcher.getDataBase();
	ActionListener removeActionListener = new ActionListener() {//add actionlistner to listen for change
		@Override
		public void actionPerformed(ActionEvent e) {   
			if(s.getcountriesNotAllowed() != null) {
				
			
			 if (c.getSelectedItem() != null) {
				 try {
					 	c.removeItem(c.getSelectedItem());
					 	s.addcountriesNotAllowed((String)c.getSelectedItem());
					 	CSVToolsFacade.updateCSV();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
			 }
			 else {
				 System.out.println("There is no country to remove from the list");
			 }
			}
		else {
			System.out.println("array is empty");
			}
		}
	  };
	  this.removeFromList.addActionListener(removeActionListener);
}
	public static void main(String[] args) {

		JFrame frame = MainUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
		
	}
	// TODO Auto-generated method stub

}