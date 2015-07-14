package ca.uSherbrooke.gegi.opusProjectModel.client.application;

import java.util.Iterator;

import javax.inject.Inject;

import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.CoursInfoResult;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.CoursResult;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.MenuResult;

import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

class ApplicationView extends ViewWithUiHandlers<ApplicationUiHandlers> implements ApplicationPresenter.MyView {
    interface Binder extends UiBinder<Widget, ApplicationView> {
    }

    @UiField VerticalPanel mainVertical;
    @UiField VerticalPanel SelectedItemPanel;
    @UiField MenuBar menuBar;
    @UiField MenuBar SubMenuBar;
    @UiField VerticalPanel SubMenuBarPanel;
    @UiField HTMLPanel SubMenuBarTitle;
    @UiField MenuBar SessionMenu;
    @UiField MenuBar QualityMenu;			   
    @UiField Label CoursCode;
    @UiField Label CoursName;
    @UiField Label CoursTutor;
    @UiField Label CoursDescription;
    @UiField Label CoursQuality;

    public class SessionMenuCommand implements ScheduledCommand 
    {
    	  private final String itemName;

    	  public SessionMenuCommand(String itemName) {
    	    this.itemName = itemName;
    	  }

    	  @Override
    	  public void execute() 
    	  {
    		  SelectedItemPanel.clear();
    		  SubMenuBarTitle.getElement().setInnerHTML("Session : ");
    		  getCoursSessionMenu(itemName);
    		  //CreateSessionSubMenu();
              CreateSessionPanel(SelectedItemPanel, itemName);
    	  }
    }
    
	private void CreateSessionPanel(Panel PanelToInit, String PanelName)
	{
		PanelToInit.add(new HTMLPanel("h3", "Description de la session "+ PanelName));
		PanelToInit.add(new Label("Choisissez un cours a gauche"));
	}

    public class CoursMenuCommand implements ScheduledCommand 
    {
    	  private final String itemName;

    	  public CoursMenuCommand(String itemName) {
    	    this.itemName = itemName;
    	  }

    	  @Override
    	  public void execute() 
    	  {
    		  SelectedItemPanel.clear();
              CreateCoursPanel(SelectedItemPanel, itemName);
    	  }
    }
    
    public void getCoursSessionMenu(String itemName)
    {
    	getUiHandlers().GetCoursItem(itemName);
    }
    public void CreateSessionSubMenu(CoursResult coursresult)
    {
    	SubMenuBar.clearItems();
        SubMenuBarPanel.setVisible(true);
        for(String coursName : coursresult.Cours_session)
    	{
        	SubMenuBar.addItem(new MenuItem(coursName, new CoursMenuCommand(coursName)));
    	}
    	
    }
    
	public static void CreateCoursPanel(Panel PanelToInit, String PanelName)
	{	
		String style = " style=\"padding-left:25px;\"";
		PanelToInit.add(new HTMLPanel("h3" + style, "Code : " + PanelName)); // FUNCTION > FORM
		// GET INFO BD HERE
		PanelToInit.add(new HTMLPanel("label" + style, "Nom du cours :" + "Nom de cours TEMPORAIRE")); // NOM DE COURS AVEC DB

		/* for (String chargeDeCours : ChargeDeCoursList) {
			listChargeDeCours += chargeDeCours;
			if(ChargeDeCoursList.hasNext()){
				listChargeDeCours += ", ";
			}
		
		}*/
		
		PanelToInit.add(new HTMLPanel("label" + style, "Chargé de cours :" + "INSÉRER CHARGÉ DE COURS ICI")); // TEMPORAIRE
		/* if(chargéDeCours != null){  // NOM DE COURS AVEC DB
		  	PanelToInit.add(new HTMLPanel("label", "Chargé de cours :" + "INSÉRER CHARGÉ DE COURS ICI"));
		  } else {
		  	PanelToInit.add(new HTMLPanel("label", "Chargé de cours :" + "Aucun chargé de cours"));
		  }*/

		PanelToInit.add(new HTMLPanel("label" + style, "Description : " + "INSÉRER DESCRIPTION DU COURS ICI BACON IPSUM FOR ATTENTION : Bacon ipsum dolor amet pancetta cow rump, tail picanha bacon boudin porchetta ham hock swine andouille salami tongue. Sirloin hamburger ground round shankle spare ribs chicken salami beef. Pastrami beef pancetta, rump meatball beef ribs leberkas ribeye flank. Cow tenderloin tail beef ribs bresaola chicken chuck kielbasa ham kevin shank pork loin shoulder picanha. Brisket pig fatback chicken biltong spare ribs shankle cupim ball tip pork belly swine salami.")); // TEMPORAIRE
		
		/* for (String quality : QualityList) {
			listQuality += quality;
			if(QualityList.hasNext()){
				listQuality += ", ";
			}
		}*/
		PanelToInit.add(new HTMLPanel("label" + style, "Qualités associées : " + "Qualité 1, Qualité 5, Qualité 9")); // TEMPORAIRE
//		PanelToInit.getElement().setAttribute("style", "padding-left:25px;");
	}
    public class QualiteMenuCommand implements ScheduledCommand 
    {
    	  private final String itemName;

    	  public QualiteMenuCommand(String itemName) {
    	    this.itemName = itemName;
    	  }

    	  @Override
    	  public void execute() 
    	  {
    	      SubMenuBarPanel.setVisible(false);
    		  SubMenuBar.clearItems();
    		  SelectedItemPanel.clear();
              CreateQualitePanel(SelectedItemPanel, itemName);
    	  }
    }
    
	public void CreateQualitePanel(Panel PanelToInit, String PanelName)
	{
		PanelToInit.add(new HTMLPanel("h3", PanelName));
	}
    
    public void CreateMenuItems(MenuResult menuResult)
    {
    	for(String sessionName : menuResult.sessionsName)
    	{
        	SessionMenu.addItem(new MenuItem(sessionName, new SessionMenuCommand(sessionName)));
    	}
    	for(String qualityName : menuResult.QualityName)
    	{
        	QualityMenu.addItem(new MenuItem(qualityName, new QualiteMenuCommand(qualityName)));
    	}
    }
    
    
    
    @Inject
    ApplicationView(Binder uiBinder) 
    {
        initWidget(uiBinder.createAndBindUi(this));
        mainVertical.setWidth("100%");
        SubMenuBarPanel.setVisible(false);
        }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (slot == ApplicationPresenter.SLOT_SetMainContent) {
        } else {
            super.setInSlot(slot, content);
        }
    }
	@Override
	public void CreateCoursItems(CoursInfoResult result) {
	    String listTutor = "";
	    String listQuality = "";
	    
	    
		CoursCode.setText(result.coursCode);
	    CoursName.setText(result.coursName);
	    
	    Iterator<String> it = result.tutorList.iterator();
	    while(it.hasNext())
	    {
	    	listTutor += it.toString();
			if(it.hasNext()){
				listTutor += ", ";
			}
	    }
	    CoursTutor.setText(listTutor);
			
	    CoursDescription.setText(result.coursDescription);
	    it = result.qualityList.iterator();
	    while(it.hasNext())
	    {
	    	listQuality += it.toString();
			if(it.hasNext()){
				listQuality += ", ";
			}
	    }
	    CoursQuality.setText(listQuality);
		
	}
}