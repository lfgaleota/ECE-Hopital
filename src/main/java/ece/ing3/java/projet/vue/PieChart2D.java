package ece.ing3.java.projet.vue;

import static ece.ing3.java.projet.enums.Rotation.JOUR;
import static ece.ing3.java.projet.enums.Rotation.NUIT;
import static ece.ing3.java.projet.enums.Specialite.Anesthesiste;
import static ece.ing3.java.projet.enums.Specialite.Cardiologue;
import static ece.ing3.java.projet.enums.Specialite.Orthopediste;
import static ece.ing3.java.projet.enums.Specialite.Pneumologue;
import static ece.ing3.java.projet.enums.Specialite.Radiologue;
import static ece.ing3.java.projet.enums.Specialite.Traumatologue;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.modele.hopital.Malade;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 * Classe modèle pour les diagrammes circulaires 2D
 * 
 * @author Nicolas
 *
 */
public class PieChart2D {

	private static DefaultPieDataset dataset;
	private static JFreeChart chart;
	private static ChartPanel chartpanel;
        //VALEUR SEVANT AFFICHER LE PIECHART VOULU , IMPOSSIBLDE DE REFAIRE DES CONTRUCTEUR SPECIFIQUE
        private static int type=0;

	/**
	 * Constructeur par défaut -> Complete les informations du diagramme 
	 * -> Rempli et affiche le diagramme circulaire dans un panel
	 */
	public PieChart2D(int type ,List<Employe> malisteemploye,List<Malade>malistemalade,List<Chambre>malistechambre,List<Docteur>malistedocteur,List<Infirmier>malisteinfirmier) {
		dataset = new DefaultPieDataset();
                this.type=type;
                /*
                
                ///A SAVOIR QUE CE BLOC NE SERT UNIQUEMENT POUR DES TEST
                ///VALEUR DU NOMBRE DE PERSONNE DELA POPLUATION
                
                int populationglobale = malisteemploye.size();
                
                
                int populationvoulue=0;
                
                
                System.out.println(malisteemploye.size());
                
                for(int i=0;i<malisteemploye.size();i++)
                {
                        System.out.println(malisteemploye.get(i).getNom());
                        
                        if(malisteemploye.get(i).getNom().charAt(0)=='D')
                        {
                            populationvoulue++;
                        }
                        
                }
   
                
                System.out.println(" "+ populationvoulue);
                
                
                
                
		dataset.setValue("Category 1",(populationvoulue*100/populationglobale));
		dataset.setValue("Category 2", (1-populationvoulue/populationglobale)*100);
		//dataset.setValue("Category 3", 79.5);

                
                System.out.println("PUTAIN "+malisteemploye.get(0).getAdresse()+" "+ malisteemploye.size());
                
                */
                
                ///BLOC DE MUTUELLE MALADE
                if(type==1)
                {
                   int populationglobale = malistemalade.size();
                
                
                int MNH=0;
                int AG2R=0;
                int CNAMTS=0;
                int LMDE=0;
                int MAAF=0;
                int MAF=0;
                int MAS=0;
                int MGEN=0;
                int MGSP=0;
                int MMA=0;
                int MNAM=0;
                int MNFTC=0;
                int CCVRP=0;
                
                
                
               // System.out.println(malisteemploye.size());
                
                for(int i=0;i<malistemalade.size();i++)
                {
                        
         
                        if("AG2R".equals(malistemalade.get(i).getMutuelle()))
                        {
                            AG2R++;
                        }
                        if("CCVRP".equals(malistemalade.get(i).getMutuelle()))
                        {
                            CCVRP++;
                        }
                        if("CNAMTS".equals(malistemalade.get(i).getMutuelle()))
                        {
                            CNAMTS++;
                        }
                        if("LMDE".equals(malistemalade.get(i).getMutuelle()))
                        {
                            LMDE++;
                        }
                        if("MAAF".equals(malistemalade.get(i).getMutuelle()))
                        {
                            MAAF++;
                        }
                        if("MAS".equals(malistemalade.get(i).getMutuelle()))
                        {
                            MAS++;
                        }
                        if("MGEN".equals(malistemalade.get(i).getMutuelle()))
                        {
                            MGEN++;
                        }
                         if("MGSP".equals(malistemalade.get(i).getMutuelle()))
                        {
                            MGSP++;
                        }
                        if("MMA".equals(malistemalade.get(i).getMutuelle()))
                        {
                            MMA++;
                        }
                        if("MNAM".equals(malistemalade.get(i).getMutuelle()))
                        {
                            MNAM++;
                        }
                        if("MNFTC".equals(malistemalade.get(i).getMutuelle()))
                        {
                            MNFTC++;
                        }                  
                        if("MNH".equals(malistemalade.get(i).getMutuelle()))
                        {
                            MNH++;
                        }
                        
                }
                
                dataset.setValue("AG2R",(AG2R*100/populationglobale));
                dataset.setValue("CCVRP",(CCVRP*100/populationglobale));
                dataset.setValue("CNAMTS",(CNAMTS*100/populationglobale));
                dataset.setValue("LMDE",(LMDE*100/populationglobale));
                dataset.setValue("MAAF",(MAAF*100/populationglobale));
                dataset.setValue("MAS",(MAS*100/populationglobale));
                dataset.setValue("MGEN",(MGEN*100/populationglobale));
                dataset.setValue("MGSP",(MGSP*100/populationglobale));
                dataset.setValue("MMA",(MMA*100/populationglobale));
                dataset.setValue("MNAM",(MNAM*100/populationglobale));
                dataset.setValue("MGSP",(MGSP*100/populationglobale));
                dataset.setValue("MNFTC",(MNFTC*100/populationglobale));
                dataset.setValue("MNH",(MNH*100/populationglobale));
                
                System.out.println(MNH +" "+ AG2R);
                
		chart = ChartFactory.createPieChart("Mutuelle des malades", dataset, true, // legend?
				true, // tooltips?
				false // URLs?
		);

		chartpanel = new ChartPanel(chart); 
                }
                
                  if(type==2)
                {
                      int populationglobale = malistechambre.size();
                
                int lit1=0;
                int lit2=0;
                int lit3=0;
                int lit4=0;
         
                
           
                
                for(int i=0;i<malistechambre.size();i++)
                {
                        
         
                        if(malistechambre.get(i).getNombreLits()==1)
                        {
                           lit1++;
                        }
                    if(malistechambre.get(i).getNombreLits()==2)
                        {
                           lit2++;
                        }
                   if(malistechambre.get(i).getNombreLits()==3)
                        {
                           lit3++;
                        }
                    if(malistechambre.get(i).getNombreLits()==4)
                        {
                           lit4++;
                        }
                   
                  
                        
                }
                

                

                
                dataset.setValue("Un lit ",(lit1*100/populationglobale));
                dataset.setValue("Deux lit ",(lit2*100/populationglobale));
                dataset.setValue("Trois lit ",(lit3*100/populationglobale));
                dataset.setValue("Quatre lit ",(lit4*100/populationglobale));

                
                
                
		chart = ChartFactory.createPieChart("Nombre de lit par chambre", dataset, true, // legend?
				true, // tooltips?
				false // URLs?
		);

		chartpanel = new ChartPanel(chart); 
                }
                  
                if(type==3)
                {
                    int radio=0;
                    int cardio=0;
                    int pneumo=0;
                    int anes=0;
                    int ortho=0;
                    int traumato=0;
                    
                      int populationglobale = malistedocteur.size();
                      
                             for(int i=0;i<malistedocteur.size();i++)
                             {
                                 if(malistedocteur.get(i).getSpecialite()==Radiologue)
                                 {
                                     radio++;
                                 }
                                 if(malistedocteur.get(i).getSpecialite()==Cardiologue)
                                 {
                                     cardio++;
                                 }
                                 if(malistedocteur.get(i).getSpecialite()==Pneumologue)
                                 {
                                     pneumo++;
                                 }
                                 if(malistedocteur.get(i).getSpecialite()==Anesthesiste)
                                 {
                                     anes++;
                                 }
                                 if(malistedocteur.get(i).getSpecialite()==Orthopediste)
                                 {
                                     ortho++;
                                 }
                                 if(malistedocteur.get(i).getSpecialite()==Traumatologue)
                                 {
                                     traumato++;
                                 }
                             }
                             
                                       dataset.setValue("Radiologue ",(radio*100/populationglobale));
                                       dataset.setValue("Cardiologue ",(cardio*100/populationglobale));
                                       dataset.setValue("Pneumologue ",(pneumo*100/populationglobale));
                                       dataset.setValue("Anestesiste ",(anes*100/populationglobale));
                                       dataset.setValue("Orthopediste",(ortho*100/populationglobale));
                                       dataset.setValue("Traumatologiste ",(traumato*100/populationglobale));
   
                             		chart = ChartFactory.createPieChart("Type de docteur spécialiste", dataset, true, // legend?
                                                true, // tooltips?
                                                false // URLs?
                                         );

                                        chartpanel = new ChartPanel(chart); 
                }
                
                 if(type==4)
                {
                    
                    int jour=0;
                    int nuit=0;
                    
                      int populationglobale = malisteinfirmier.size();
                      
                           for(int i=0;i<malisteinfirmier.size();i++)
                             {
                                 if(malisteinfirmier.get(i).getRotation()==JOUR)
                                 {
                                     jour++;
                                 }
                                 
                                 if(malisteinfirmier.get(i).getRotation()==NUIT)
                                 {
                                     nuit++;
                                 }
                             }
                                       dataset.setValue("Radiologue ",(jour*100/populationglobale));
                                       dataset.setValue("Cardiologue ",(nuit*100/populationglobale));
                             
   
                             		chart = ChartFactory.createPieChart("Rotation infirmier", dataset, true, // legend?
                                                true, // tooltips?
                                                false // URLs?
                                         );

                                        chartpanel = new ChartPanel(chart); 
                }
                
	}
        
        

	/**
	 * Getter
	 * 
	 * @return un panel contenant le diagramme 2D
	 */
	public ChartPanel getPieChart2D() {
		return chartpanel;
	}
}
