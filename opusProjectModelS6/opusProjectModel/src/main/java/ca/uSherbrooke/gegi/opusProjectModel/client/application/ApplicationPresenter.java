package ca.uSherbrooke.gegi.opusProjectModel.client.application;

import java.util.logging.Logger;

import javax.inject.Inject;

import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.CoursAction;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.CoursInfoAction;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.CoursInfoResult;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.CoursResult;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.MenuAction;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.MenuResult;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy> implements ApplicationUiHandlers {
    interface MyView extends View, HasUiHandlers<ApplicationUiHandlers> 
    {
    	public void CreateMenuItems(MenuResult result);
    	public void CreateSessionSubMenu(CoursResult result);
    	public void CreateCoursItems(CoursInfoResult result);
    }

	DispatchAsync dispatcher;
	EventBus eventBus;
	ApplicationPresenter me;
	
    @ContentSlot
    public static final Type<RevealContentHandler<?>> SLOT_SetMainContent = new Type<>();

    @ProxyStandard
    interface MyProxy extends Proxy<ApplicationPresenter> {
    }

    @Inject
    ApplicationPresenter(EventBus eventBus,
                         MyView view,
                         MyProxy proxy
                         ,DispatchAsync dispatcher) {
        super(eventBus, view, proxy, RevealType.Root);
		this.dispatcher = dispatcher;
		this.eventBus = eventBus;
		this.me = this;
		getView().setUiHandlers(this);
    }
    
    @Override
    protected void onBind() 
    {
    	super.onBind();
    	GetMenuItems();
    	GetCoursInfo();
    }
    
    @Override
    public void GetMenuItems()
    {
		MenuAction menuAction = new MenuAction();
		dispatcher.execute(menuAction, new AsyncCallback<MenuResult>() {
			@Override
			public void onSuccess(MenuResult result) {
				Logger logger = Logger.getLogger("Log variable");
				logger.log(java.util.logging.Level.SEVERE, "Menu type ");
				getView().CreateMenuItems(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Logger logger = java.util.logging.Logger.getLogger("Error Log variable");
				logger.log(java.util.logging.Level.SEVERE, "Local choisi probleme");
			}
		});
		
    }

	@Override
	public void GetCoursItem(String item) {
		CoursAction coursAction = new CoursAction();
		dispatcher.execute(coursAction, new AsyncCallback<CoursResult>() {
			@Override
			public void onSuccess(CoursResult result) {
				Logger logger = Logger.getLogger("Log variable");
				logger.log(java.util.logging.Level.SEVERE, "Menu type ");
				getView().CreateSessionSubMenu(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Logger logger = java.util.logging.Logger.getLogger("Error Log variable");
				logger.log(java.util.logging.Level.SEVERE, "Local choisi probleme");
			}
		});
	}
    @Override
    public void GetCoursInfo()
    {
    CoursInfoAction coursInfoAction = new CoursInfoAction();
    dispatcher.execute(coursInfoAction, new AsyncCallback<CoursInfoResult>() {
    	@Override
    	public void onSuccess(CoursInfoResult result) {
    		Logger logger = Logger.getLogger("Log variable");
    		logger.log(java.util.logging.Level.SEVERE, "Cours type ");
    		getView().CreateCoursItems(result);
    	}
    
    	@Override
    	public void onFailure(Throwable caught) {
    		Logger logger = java.util.logging.Logger.getLogger("Error Log variable");
    		logger.log(java.util.logging.Level.SEVERE, "Cours choisi probleme");
    	}
    });
             }
}
