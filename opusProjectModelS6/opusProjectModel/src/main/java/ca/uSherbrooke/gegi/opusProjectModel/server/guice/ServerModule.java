package ca.uSherbrooke.gegi.opusProjectModel.server.guice;

import ca.uSherbrooke.gegi.opusProjectModel.server.dispatch.CoursActionHandler;
import ca.uSherbrooke.gegi.opusProjectModel.server.dispatch.CoursInfoActionHandler;
import ca.uSherbrooke.gegi.opusProjectModel.server.dispatch.MenuActionHandler;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.CoursAction;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.CoursInfoAction;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.MenuAction;

import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;

public class ServerModule extends HandlerModule {
    @Override
    protected void configureHandlers() 
    {
        bindHandler(CoursAction.class, CoursActionHandler.class);
        bindHandler(CoursInfoAction.class, CoursInfoActionHandler.class);
        bindHandler(MenuAction.class, MenuActionHandler.class);
    }
}
