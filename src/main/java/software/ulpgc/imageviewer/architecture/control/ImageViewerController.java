package software.ulpgc.imageviewer.architecture.control;

import software.ulpgc.imageviewer.architecture.model.Image;
import software.ulpgc.imageviewer.architecture.view.ImageViewerDisplay;

public class ImageViewerController {
    private final ImageViewerDisplay display;
    private Image currentImage;

    public ImageViewerController(Image initialImage, ImageViewerDisplay display) {
        this.display = display;
        this.currentImage = initialImage;
        setupHandlers();
        display.display(currentImage);
    }

    private void setupHandlers() {
        display.onPrevious(event -> showPrevious());
        display.onNext(event -> showNext());
    }

    private void showPrevious() {
        currentImage = currentImage.previous();
        display.display(currentImage);
    }

    private void showNext() {
        currentImage = currentImage.next();
        display.display(currentImage);
    }
}
