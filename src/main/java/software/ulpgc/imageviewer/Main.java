package software.ulpgc.imageviewer;

import software.ulpgc.imageviewer.control.ImageViewerController;
import software.ulpgc.imageviewer.model.Image;
import software.ulpgc.imageviewer.model.ImageLoader;
import software.ulpgc.imageviewer.view.MainFrame;

import software.ulpgc.imageviewer.model.APIImageLoader;

public class Main {
    public static void main(String[] args) {
        ImageLoader loader = new APIImageLoader();
        Image firstImage = loader.load();
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        new ImageViewerController(firstImage, mainFrame);
    }
}
