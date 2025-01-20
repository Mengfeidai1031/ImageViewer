package software.ulpgc.imageviewer.apps.windows;

import software.ulpgc.imageviewer.architecture.control.ImageViewerController;
import software.ulpgc.imageviewer.architecture.model.Image;
import software.ulpgc.imageviewer.architecture.model.ImageLoader;
import software.ulpgc.imageviewer.architecture.swing.MainFrame;

import software.ulpgc.imageviewer.architecture.model.APIImageLoader;

public class Main {
    public static void main(String[] args) {
        ImageLoader loader = new APIImageLoader();
        Image firstImage = loader.load();
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        new ImageViewerController(firstImage, mainFrame);
    }
}
