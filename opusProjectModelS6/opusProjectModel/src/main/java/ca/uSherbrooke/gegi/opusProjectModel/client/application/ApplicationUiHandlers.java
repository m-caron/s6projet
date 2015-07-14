package ca.uSherbrooke.gegi.opusProjectModel.client.application;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ApplicationUiHandlers extends UiHandlers{
	public void GetCoursInfo();
	public void GetMenuItems();
	public void GetCoursItem(String item);
}
