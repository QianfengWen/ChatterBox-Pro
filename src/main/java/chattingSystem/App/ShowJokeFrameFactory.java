package chattingSystem.App;

import chattingSystem.frameworks_drivers.ui.views.FunJokeView;
import chattingSystem.frameworks_drivers.ui.views.FunJokeViewManager;
import chattingSystem.interface_adapter.controllers.FunJokeController;
import chattingSystem.interface_adapter.presenter.FunJokePresenter;
import chattingSystem.interface_adapter.view_models.FunJokeViewManagerModel;
import chattingSystem.interface_adapter.view_models.FunJokeViewModel;
import chattingSystem.use_cases.fun_joke.FunJokeDataAccessBoundary;
import chattingSystem.use_cases.fun_joke.FunJokeInputBountry;
import chattingSystem.use_cases.fun_joke.FunJokeInteractor;
import chattingSystem.use_cases.fun_joke.FunJokeOutputBoundry;

import javax.swing.*;
import java.awt.*;

public class ShowJokeFrameFactory {

    public static void createShowJokeFrame(FunJokeViewModel funJokeViewModel, FunJokeDataAccessBoundary funJokeDataAccessObject) {
        JFrame funJokeFrame = new JFrame("Have Fun Joke");
        funJokeFrame.setPreferredSize(new Dimension(430, 360));
        funJokeFrame.setVisible(false);
        FunJokeController funJokeController = createFunJokeUseCase(funJokeDataAccessObject, funJokeViewModel);
        CardLayout funJokeCardLayout = new CardLayout();
        JPanel funJokeViews = new JPanel(funJokeCardLayout);
        funJokeFrame.add(funJokeViews);
        // This keeps track of and manages which view is currently showing.
        FunJokeViewManagerModel funJokeViewManagerModel = new FunJokeViewManagerModel();
        FunJokeViewManager funJokeViewManager = new FunJokeViewManager(funJokeViews, funJokeCardLayout, funJokeViewManagerModel);
        JPanel funJokeView = new FunJokeView(funJokeController, funJokeViewModel, funJokeViewManagerModel, funJokeViewManager);
        funJokeFrame.setContentPane(funJokeView);
        funJokeFrame.pack();
        funJokeFrame.setVisible(true);
        funJokeViewModel.setCurrentFrame(funJokeFrame);
    }
    public static FunJokeController createFunJokeUseCase(
            FunJokeDataAccessBoundary funJokeDataAccessBoundary, FunJokeViewModel funJokeViewModel
    ) {
        // Notice how we pass this method's parameters to the Presenter.
        FunJokeOutputBoundry funJokeOutputBoundry = new FunJokePresenter(funJokeViewModel);

        FunJokeInputBountry funJokeInteractor = new FunJokeInteractor(funJokeDataAccessBoundary, funJokeOutputBoundry);

        return new FunJokeController(funJokeInteractor);
    }
}
